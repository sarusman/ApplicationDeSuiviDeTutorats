package ApplicationDeSuiviDeTutoratst.controller;
import ApplicationDeSuiviDeTutoratst.service.ProgrammeurService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ApplicationDeSuiviDeTutoratst.model.Programmeur;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/tpfilrouge")
public class ProgrammeurControleur {
    private final ProgrammeurService programmeurService;

    public ProgrammeurControleur(ProgrammeurService programmeurService) {
        this.programmeurService = programmeurService;
    }

    @GetMapping("/")
    public String afficherProgrammeurs(Model model) {
        List<Programmeur> allProgramers = programmeurService.getProgrammeurs();
        model.addAttribute("programmeurs", allProgramers);
//        model.addAttribute("newProgrammer", new Programmeur());
        return "index";
    }

    @GetMapping("/unProgrammeur/{idProgrammeur}")
    public Optional<Programmeur> afficherUnProgrammeur(@PathVariable("idProgrammeur") Integer idProg) {
        return programmeurService.getUnProgrammeur(idProg);
    }

    @DeleteMapping("/supprimerProgrammeur/{idProgrammeur}")
//    @GetMapping("/supprimerProgrammeur/{idProgrammeur}")    @GetMapping fonctionne aussi :-)
    public void deleteProgrammeur(@PathVariable("idProgrammeur") Integer idProg) {
        programmeurService.supprimerProgrammeur(idProg);
    }

    @PostMapping("/ajouterProgrammeur")
    public void creerProgrammeur(@RequestBody Programmeur programmeur){
        programmeurService.ajouterProgrammeur(programmeur);
    }

    @PutMapping("modifier/{idProgrammeur}")
    public void modifierProgrammeur(@PathVariable Integer idProgrammeur,@RequestBody Programmeur programmeurModified ){
        programmeurService.modifierProgrammeur(idProgrammeur,programmeurModified);
    }
}