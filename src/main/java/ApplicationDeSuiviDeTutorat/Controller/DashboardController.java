package ApplicationDeSuiviDeTutorat.controller;

import ApplicationDeSuiviDeTutorat.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model) {
        model.addAttribute("trainees", dashboardService.getTrainees());
        model.addAttribute("mentors", dashboardService.getMentors());
        return "dashboard";
    }
}
