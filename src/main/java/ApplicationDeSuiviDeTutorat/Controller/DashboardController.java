package ApplicationDeSuiviDeTutorat.Controller;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ApprentiService apprentiService;
    private final UtilisateurService utilisateurService;
    private final ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository entrepriseRepository;
    private final ApplicationDeSuiviDeTutorat.repository.TuteurEntrepriseRepository tuteurEntrepriseRepository;

    public DashboardController(
            UtilisateurService utilisateurService,
            ApprentiService apprentiService,
            ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository entrepriseRepository,
            ApplicationDeSuiviDeTutorat.repository.TuteurEntrepriseRepository tuteurEntrepriseRepository
            ){
        this.utilisateurService = utilisateurService;
        this.apprentiService = apprentiService;
        this.entrepriseRepository = entrepriseRepository;
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
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("programmes", Programme.values());
        model.addAttribute("entreprises", entrepriseRepository.findAll());

        return "dashboard";
    }
}


