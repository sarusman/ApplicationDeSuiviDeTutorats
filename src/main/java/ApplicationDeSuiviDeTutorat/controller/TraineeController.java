package ApplicationDeSuiviDeTutorat.controller;
import ApplicationDeSuiviDeTutorat.model.Trainee;
import ApplicationDeSuiviDeTutorat.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/{id}")
    public String getTraineeById(@PathVariable("id") Integer id, Model model) {
         Optional<Trainee> trainee = traineeService.getTraineeById(id);
         model.addAttribute("trainee", trainee.orElse(null));
        return "traineeDetails";
    }

    @PutMapping("/{id}")
    public String updateTraineeById(@PathVariable Integer id,@ModelAttribute Trainee updatedTrainee ){
        traineeService.updateTraineeById(id, updatedTrainee);
        return "redirect:/traineeDetails/" + id;
    }
}