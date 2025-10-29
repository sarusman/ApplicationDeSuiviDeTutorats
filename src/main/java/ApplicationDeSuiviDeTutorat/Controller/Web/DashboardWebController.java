package ApplicationDeSuiviDeTutorat.Controller.web;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository;
import ApplicationDeSuiviDeTutorat.repository.TuteurEntrepriseRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardWebController {

    private final UtilisateurService utilisateurService;
    private final ApprentiService apprentiService;
    private final EntrepriseRepository entrepriseRepository;
    private final TuteurEntrepriseRepository tuteurEntrepriseRepository;

    public DashboardWebController(UtilisateurService utilisateurService,
                                  ApprentiService apprentiService,
                                  EntrepriseRepository entrepriseRepository,
                                  TuteurEntrepriseRepository tuteurEntrepriseRepository) {
        this.utilisateurService = utilisateurService;
        this.apprentiService = apprentiService;
        this.entrepriseRepository = entrepriseRepository;
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

        model.addAttribute("apprentis", utilisateurService.trouverApprentisParTuteurId(utilisateur.getId()));
        model.addAttribute("nouvelApprenti", new Apprenti());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("programmes", Programme.values());
        model.addAttribute("entreprises", entrepriseRepository.findAll());

        return "dashboard";
    }
}
