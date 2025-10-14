package ApplicationDeSuiviDeTutorat.controller;
import ApplicationDeSuiviDeTutorat.model.Trainee;
import ApplicationDeSuiviDeTutorat.service.TraineeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ApplicationDeSuiviDeTutorat.model.Programmeur;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/trainee")
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/")
    public String afficherProgrammeurs(Model model) {
        List<Trainee> trainees = traineeService.getAllTrainess();
        model.addAttribute("trainees", trainees);
//        model.addAttribute("newProgrammer", new Programmeur());
        return "index";
    }
}