package ApplicationDeSuiviDeTutorat.Controller;

import ApplicationDeSuiviDeTutorat.Service.ApprentiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DashboardController {
    private final ApprentiService apprentiService;

    public DashboardController(ApprentiService apprentiService) {
        this.apprentiService = apprentiService;
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model) {
        model.addAttribute("apprentis", apprentiService.getAllApprentis());
        return "dashboard";
    }
}
