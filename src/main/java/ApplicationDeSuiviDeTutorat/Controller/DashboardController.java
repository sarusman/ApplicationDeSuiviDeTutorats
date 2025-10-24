package ApplicationDeSuiviDeTutorat.Controller;

import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UtilisateurService utilisateurService;

    public DashboardController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
        if (utilisateur == null) {
            return "redirect:/login";
        }
        model.addAttribute("apprentis", utilisateurService.trouverApprentisParTuteurId(utilisateur.getId()));
        model.addAttribute("utilisateur", utilisateur);
        return "dashboard";
    }
}


