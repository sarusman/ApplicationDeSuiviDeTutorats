package ApplicationDeSuiviDeTutorat.Controller;

import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import ApplicationDeSuiviDeTutorat.Service.TuteurEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// Choix de JM : utiliser un rest controller + préfixe pour distinguer des controller renvoyant des vues thymeleaf
@RestController
@RequestMapping("/api/tuteurs")
public class TuteurEntrepriseController {

    private final TuteurEntrepriseService tuteurEntrepriseService;

    @Autowired
    public TuteurEntrepriseController(TuteurEntrepriseService tuteurEntrepriseService) {
        this.tuteurEntrepriseService = tuteurEntrepriseService;
    }

    @GetMapping("/by-entreprise/{entrepriseId}")
    public List<TuteurEntreprise> getTuteursParEntreprise(@PathVariable Long entrepriseId) {
        return tuteurEntrepriseService.findByEntrepriseId(entrepriseId);
    }
}