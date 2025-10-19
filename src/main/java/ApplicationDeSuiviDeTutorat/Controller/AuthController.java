package ApplicationDeSuiviDeTutorat.Controller;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ApplicationDeSuiviDeTutorat.Service.AuthService;

import java.util.List;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Utilisateur utilisateur = new Utilisateur(username, password);

        if (authService.login(utilisateur)) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getUsername(),
                            null
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/dashboard";
        }

        return "redirect:/login?error";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        Utilisateur utilisateur = new Utilisateur(username, password);
        if (authService.register(utilisateur)) {
            return "redirect:/login";
        }
        return "redirect:/register?error";
    }
}