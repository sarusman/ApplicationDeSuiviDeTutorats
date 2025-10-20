package ApplicationDeSuiviDeTutorat.Controller;

import ApplicationDeSuiviDeTutorat.Service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model) {
        model.addAttribute("apprentis", dashboardService.getTrainees());
        return "dashboard";
    }
}
