package ApplicationDeSuiviDeTutorat.Controller;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/trainee")
public class ApprentiController {

    @Autowired
    private ApprentiService traineeService;

    public ApprentiController(ApprentiService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/{id}")
    public String getTraineeById(@PathVariable("id") Long id, Model model) {
         Optional<Apprenti> trainee = traineeService.getApprentiBilanById(id);
         model.addAttribute("trainee", trainee.orElse(null));
        return "traineeDetails";
    }

    @PutMapping("/{id}")
    public String updateTraineeById(@PathVariable Long id,@ModelAttribute Apprenti updatedTrainee ){
        traineeService.updateApprentiBilanById(id, updatedTrainee);
        return "redirect:/traineeDetails/" + id;
    }
}