package ApplicationDeSuiviDeTutorat.Controller.Web;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import ApplicationDeSuiviDeTutorat.Service.EntrepriseService;
import ApplicationDeSuiviDeTutorat.Service.ProgrammeService;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/apprenti")
public class ApprentiWebController {

    private final UtilisateurRepository utilisateurRepository;
    private final ApprentiService apprentiService;
    private final UtilisateurService utilisateurService;
    private final ProgrammeService programmeService;
    private final EntrepriseService entrepriseService;

    public ApprentiWebController(UtilisateurRepository utilisateurRepository,   ApprentiService apprentiService,
                                 UtilisateurService utilisateurService,
                                 ProgrammeService programmeService,
                                 EntrepriseService entrepriseService) {
        this.apprentiService = apprentiService;
        this.utilisateurService = utilisateurService;
        this.programmeService = programmeService;
        this.entrepriseService = entrepriseService;
    }

    @GetMapping("/{id}")
    public String getTraineeById(@PathVariable("id") Long id,
                                 Principal principal,
                                 Model model) {

        if (principal == null) {
            return "redirect:/login";
        }

        Optional<Apprenti> traineeOpt = apprentiService.getApprentiById(id);

        if (traineeOpt.isPresent()) {
            Apprenti apprenti = traineeOpt.get();

            Optional<Visite> derniereVisite = apprentiService.findDerniereVisite(apprenti);
            Optional<Visite> prochaineVisite = apprentiService.findProchaineVisite(apprenti);

            model.addAttribute("apprenti", apprenti);
            model.addAttribute("derniereVisite", derniereVisite);
            model.addAttribute("prochaineVisite", prochaineVisite);

            model.addAttribute("programmes", programmeService.getProgrammesArray());
            model.addAttribute("entreprises", entrepriseService.findAll());
        } else {
            model.addAttribute("apprenti", null);
        }

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
    public String ajouterApprenti(@ModelAttribute ApprentiAnneeAlternanceDTO dto,
                                  Principal principal,
                                  RedirectAttributes redirectAttributes) {

        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();

        Utilisateur tuteurConnecte = utilisateurService.trouverParUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        Long tuteurId = tuteurConnecte.getId();

        boolean mailExiste = apprentiService.existeAdresse(apprenti.getAdresseElectronique());
        boolean telExiste = (dto.telephone() != null && !dto.telephone().isBlank())
                && apprentiService.existeTelephone(dto.telephone());

        if (mailExiste || telExiste) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Impossible d'ajouter l'apprenti : cet e-mail ou ce téléphone est déjà utilisé."
            );
            return "redirect:/dashboard";
        }

        Utilisateur tuteur = utilisateurRepository.findById(tuteurId)
                .orElseThrow(() -> new IllegalStateException("Tuteur introuvable"));

        apprentiService.createApprenti(dto);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "L'apprenti a été ajouté avec succès."
        );

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
