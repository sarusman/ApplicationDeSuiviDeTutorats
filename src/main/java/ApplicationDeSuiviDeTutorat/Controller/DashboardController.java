package ApplicationDeSuiviDeTutorat.Controller;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiFormDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAcademique;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAcademiqueRepository;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAlternanceRepository;
import ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository;
import ApplicationDeSuiviDeTutorat.Repository.TuteurEntrepriseRepository;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UtilisateurService utilisateurService;
    private final EntrepriseRepository entrepriseRepository;
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;
    private final TuteurEntrepriseRepository tuteurEntrepriseRepository;

    public DashboardController(
            UtilisateurService utilisateurService,
            ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository entrepriseRepository, AnneeAcademiqueRepository anneeAcademiqueRepository, TuteurEntrepriseRepository tuteurEntrepriseRepository
    ){
        this.utilisateurService = utilisateurService;
        this.entrepriseRepository = entrepriseRepository;
        this.anneeAcademiqueRepository = anneeAcademiqueRepository;
        this.tuteurEntrepriseRepository = tuteurEntrepriseRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
        if (utilisateur == null) {
            return "redirect:/login";
        }
        model.addAttribute("apprentis", utilisateurService.trouverApprentisParTuteurId(utilisateur.getId()));
        model.addAttribute("nouvelApprenti", new Apprenti());
        model.addAttribute("nouvelleAnneeAlternance", new AnneeAlternance());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("programmes", Programme.values());
        model.addAttribute("entreprises", entrepriseRepository.findAll());
        model.addAttribute("anneesAcademique", anneeAcademiqueRepository.findAll());
        model.addAttribute("tuteurEntreprise", tuteurEntrepriseRepository.findAll());
        model.addAttribute("ApprentiFormDTO", new ApprentiFormDTO());

        return "dashboard";
    }
}


