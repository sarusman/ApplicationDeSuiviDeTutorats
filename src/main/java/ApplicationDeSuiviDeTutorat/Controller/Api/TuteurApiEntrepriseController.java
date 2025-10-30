package ApplicationDeSuiviDeTutorat.Controller.Api;

import ApplicationDeSuiviDeTutorat.Models.DTO.TuteurEntrepriseDTO;
import ApplicationDeSuiviDeTutorat.Service.TuteurEntrepriseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tuteurs")
@Tag(name = "Tuteurs API", description = "API pour la gestion des tuteurs d'entreprise")
public class TuteurApiEntrepriseController {

    private final TuteurEntrepriseService tuteurEntrepriseService;

    public TuteurApiEntrepriseController(TuteurEntrepriseService tuteurEntrepriseService) {
        this.tuteurEntrepriseService = tuteurEntrepriseService;
    }

    @GetMapping("/by-entreprise/{entrepriseId}")
    @Operation(summary = "Lister les tuteurs d'une entreprise par son identifiant")
    public List<TuteurEntrepriseDTO> getTuteursParEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return tuteurEntrepriseService.findByEntrepriseId(entrepriseId);
    }
}
