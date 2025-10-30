package ApplicationDeSuiviDeTutorat.Controller;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiAnneeAlternanceDTO;
import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDetailDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/apprenti")
public class ApprentiController {

    @Autowired
    private ApprentiService apprentiService;

    public ApprentiController(ApprentiService apprentiService) {
        this.apprentiService = apprentiService;
    }

    @GetMapping("/{id}")
    public String getTraineeById(@PathVariable("id") Long id, Model model) {
        Optional<Apprenti> traineeOpt = apprentiService.getApprentiById(id);

        if (traineeOpt.isPresent()) {
            Apprenti apprenti = traineeOpt.get();
            Optional<Visite> derniereVisite = apprentiService.findDerniereVisite(apprenti);
            Optional<Visite> prochaineVisite = apprentiService.findProchaineVisite(apprenti);

            model.addAttribute("apprenti", apprenti);
            model.addAttribute("derniereVisite", derniereVisite);
            model.addAttribute("prochaineVisite", prochaineVisite);
        } else {
            model.addAttribute("apprenti", null);
        }

        return "traineeDetails";
    }

    @PutMapping("/{id}")
    public String updateTraineeById(@PathVariable Long id,@ModelAttribute ApprentiDetailDTO updatedTrainee ){
        apprentiService.updateApprentiById(id, updatedTrainee);
        return STR."redirect:/apprenti/\{id}";
    }

    /**
     * Traite la soumission du formulaire d'ajout d'apprenti depuis la modale.
     */
    @PostMapping("/ajouter")
    public String ajouterApprenti(@ModelAttribute ApprentiAnneeAlternanceDTO anneeAlternance, HttpServletRequest request) {
        if(anneeAlternance == null || request == null) throw new IllegalArgumentException(); // Change exception

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
        Long tuteurId = utilisateur.getId();

        apprentiService.createApprenti(anneeAlternance, tuteurId);

        return "redirect:/dashboard";
    }
}