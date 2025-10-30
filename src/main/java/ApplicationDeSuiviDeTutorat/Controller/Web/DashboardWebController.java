package ApplicationDeSuiviDeTutorat.Controller.Web;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAcademiqueRepository;
import ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository;
import ApplicationDeSuiviDeTutorat.Repository.TuteurEntrepriseRepository;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository;
import ApplicationDeSuiviDeTutorat.Repository.TuteurEntrepriseRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardWebController {

    private final ApprentiService apprentiService;
    private final UtilisateurService utilisateurService;
    private final UtilisateurService utilisateurService;
    private final EntrepriseRepository entrepriseRepository;
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;
    private final TuteurEntrepriseRepository tuteurEntrepriseRepository;

    public DashboardWebController(UtilisateurService utilisateurService,
                                  ApprentiService apprentiService,
                                  EntrepriseRepository entrepriseRepository,
                                  TuteurEntrepriseRepository tuteurEntrepriseRepository,
                                  AnneeAcademiqueRepository anneeAcademiqueRepository) {

        this.utilisateurService = utilisateurService;
        this.apprentiService = apprentiService;
        this.entrepriseRepository = entrepriseRepository;
        this.anneeAcademiqueRepository = anneeAcademiqueRepository;
        this.tuteurEntrepriseRepository = tuteurEntrepriseRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        Utilisateur utilisateur = utilisateurService.trouverParUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        model.addAttribute("apprentiDetailDTO", apprentiService.toTabDTO(utilisateur.getId()));
        model.addAttribute("ApprentiAlternanceDTO", new ApprentiAnneeAlternanceDTO());
        model.addAttribute("programmes", Programme.values());
        model.addAttribute("entreprises", entrepriseRepository.findAll());
        model.addAttribute("anneesAcademique", anneeAcademiqueRepository.findAll());
        model.addAttribute("tuteurEntreprise", tuteurEntrepriseRepository.findAll());

        return "dashboard";
    }
}
