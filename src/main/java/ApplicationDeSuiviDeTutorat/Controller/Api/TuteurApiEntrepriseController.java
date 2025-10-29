package ApplicationDeSuiviDeTutorat.Controller.Api;

import ApplicationDeSuiviDeTutorat.Models.DTO.TuteurEntrepriseDTO;
import ApplicationDeSuiviDeTutorat.Service.TuteurEntrepriseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Tag(name = "Tuteurs API", description = "API pour la gestion des tuteurs d'entreprise")
@RequestMapping("/api/tuteurs")
public class TuteurApiEntrepriseController {

    private final TuteurEntrepriseService tuteurEntrepriseService;

    @Autowired
    public TuteurApiEntrepriseController(TuteurEntrepriseService tuteurEntrepriseService) {
        this.tuteurEntrepriseService = tuteurEntrepriseService;
    }

    @GetMapping("/by-entreprise/{entrepriseId}")
    public List<TuteurEntrepriseDTO> getTuteursParEntreprise(@PathVariable Long entrepriseId) {
        return tuteurEntrepriseService.findByEntrepriseId(entrepriseId);
    }
}