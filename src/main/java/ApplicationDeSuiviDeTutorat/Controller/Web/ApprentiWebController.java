package ApplicationDeSuiviDeTutorat.Controller.Web;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAcademiqueRepository;
import ApplicationDeSuiviDeTutorat.Repository.TuteurEntrepriseRepository;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import ApplicationDeSuiviDeTutorat.Service.EntrepriseService;
import ApplicationDeSuiviDeTutorat.Service.ProgrammeService;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/api/apprenti")
public class ApprentiWebController {

    private final ApprentiService apprentiService;
    private final UtilisateurService utilisateurService;
    private final ProgrammeService programmeService;
    private final EntrepriseService entrepriseService;
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;
    private final TuteurEntrepriseRepository tuteurEntrepriseRepository;

    public ApprentiWebController(UtilisateurRepository utilisateurRepository, ApprentiService apprentiService,
                                 UtilisateurService utilisateurService,
                                 ProgrammeService programmeService,
                                 EntrepriseService entrepriseService, AnneeAcademiqueRepository anneeAcademiqueRepository, TuteurEntrepriseRepository tuteurEntrepriseRepository) {
        this.apprentiService = apprentiService;
        this.utilisateurService = utilisateurService;
        this.programmeService = programmeService;
        this.entrepriseService = entrepriseService;
        this.anneeAcademiqueRepository = anneeAcademiqueRepository;
        this.tuteurEntrepriseRepository = tuteurEntrepriseRepository;
    }

    @GetMapping("/{id}")
    public String getTraineeById(@PathVariable("id") Long id,
                                 Principal principal,
                                 Model model) {

        if (principal == null) {
            return "redirect:/login";
        }

        Optional<Apprenti> traineeOpt = apprentiService.getApprentiById(id);

        Apprenti apprenti = traineeOpt.get();

        Optional<Visite> derniereVisite = apprentiService.findDerniereVisite(apprenti);
        Optional<Visite> prochaineVisite = apprentiService.findProchaineVisite(apprenti);

        model.addAttribute("apprentieDetailDTO", apprentiService.toDetailDTO(apprenti));
        model.addAttribute("derniereVisite", derniereVisite);
        model.addAttribute("prochaineVisite", prochaineVisite);

        model.addAttribute("programmes", Programme.values());
        model.addAttribute("entreprises", entrepriseService.findAll());
        model.addAttribute("anneesAcademique", anneeAcademiqueRepository.findAll());
        model.addAttribute("tuteurEntreprise", tuteurEntrepriseRepository.findAll());

        return "traineeDetails";
    }

    @PostMapping("/update/{id}")
    public String updateTraineeById(@PathVariable Long id,
                                    @ModelAttribute Apprenti updatedTrainee,
                                    Principal principal,
                                    RedirectAttributes redirectAttributes) {

        if (principal == null) {
            return "redirect:/login";
        }

        try {
            Apprenti actuel = apprentiService.getApprentiById(id)
                    .orElseThrow(() -> new IllegalStateException("Apprenti introuvable"));

            String nouvelEmail = updatedTrainee.getAdresseElectronique();
            String ancienEmail = actuel.getAdresseElectronique();

            String nouveauTel = updatedTrainee.getTelephone();
            String ancienTel = actuel.getTelephone();

            boolean emailChange = (nouvelEmail != null && !nouvelEmail.equalsIgnoreCase(ancienEmail));
            boolean telChange =
                    (nouveauTel != null && !nouveauTel.isBlank() && (ancienTel == null || !nouveauTel.equals(ancienTel)));

            if (emailChange && apprentiService.existeAdresse(nouvelEmail)) {
                redirectAttributes.addFlashAttribute(
                        "updateError",
                        "Impossible de mettre à jour : cet e-mail est déjà utilisé."
                );
                return "redirect:/apprenti/" + id;
            }

            if (telChange && apprentiService.existeTelephone(nouveauTel)) {
                redirectAttributes.addFlashAttribute(
                        "updateError",
                        "Impossible de mettre à jour : ce numéro est déjà utilisé."
                );
                return "redirect:/apprenti/" + id;
            }

            apprentiService.updateApprentiBilanById(id, updatedTrainee);

            redirectAttributes.addFlashAttribute(
                    "updateSuccess",
                    "Mise à jour de l'apprenti réussie."
            );

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "updateError",
                    "Une erreur est survenue lors de la mise à jour."
            );
        }

        return "redirect:/apprenti/" + id;
    }

    @PostMapping("/ajouter")
    public String ajouterApprenti(@ModelAttribute ApprentiAnneeAlternanceDTO anneeAlternance, HttpServletRequest request) {
        if(anneeAlternance == null || request == null) throw new IllegalArgumentException(); // Change exception

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
        Long tuteurId = utilisateur.getId();

        apprentiService.createApprenti(anneeAlternance, tuteurId);

        return "redirect:/dashboard";
    }

    @PostMapping("/supprimer/{id}")
    public String supprimerApprenti(@PathVariable("id") Long id,
                                    Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        apprentiService.deleteApprentiById(id);
        return "redirect:/dashboard";
    }
}
