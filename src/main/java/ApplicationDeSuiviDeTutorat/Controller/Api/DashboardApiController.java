package ApplicationDeSuiviDeTutorat.Controller.Api;

import ApplicationDeSuiviDeTutorat.Models.DTO.DashboardDto;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository;
import ApplicationDeSuiviDeTutorat.Service.UtilisateurService;
import ApplicationDeSuiviDeTutorat.Repository.TuteurEntrepriseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@Tag(name = "Dashboard", description = "Données du tableau de bord du tuteur connecté")
public class DashboardApiController {

    private final UtilisateurService utilisateurService;
    private final EntrepriseRepository entrepriseRepository;
    private final TuteurEntrepriseRepository tuteurEntrepriseRepository;

    public DashboardApiController(UtilisateurService utilisateurService,
                                  EntrepriseRepository entrepriseRepository,
                                  TuteurEntrepriseRepository tuteurEntrepriseRepository) {
        this.utilisateurService = utilisateurService;
        this.entrepriseRepository = entrepriseRepository;
        this.tuteurEntrepriseRepository = tuteurEntrepriseRepository;
    }

    @GetMapping("/api/dashboard")
    @Operation(summary = "Récupérer les données du dashboard pour le tuteur courant")
    public DashboardDto getDashboard(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String username = principal.getName();
        Utilisateur utilisateur = utilisateurService.trouverParUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return new DashboardDto(
                utilisateur,
                utilisateurService.trouverApprentisParTuteurId(utilisateur.getId()),
                Programme.values(),
                entrepriseRepository.findAll()
        );
    }
}
