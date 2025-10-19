package ApplicationDeSuiviDeTutorat.Controller;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ApplicationDeSuiviDeTutorat.Service.AuthService;

import java.util.List;

@Controller
public class AuthController {
    private final AuthService authService;
    private final SecurityContextRepository securityContextRepository;

    public AuthController(AuthService authService, SecurityContextRepository securityContextRepository) {
        this.authService = authService;
        this.securityContextRepository = securityContextRepository;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {

        Utilisateur tryUser = new Utilisateur();
        tryUser.setUsername(username);
        tryUser.setPassword(password);

        if (!authService.login(tryUser)) {
            return "redirect:/login?error";
        }

        Utilisateur dbUser = authService.getUtilisateurByUsername(username);
        Role role = dbUser.getRole(); // ex: TUTEUR
        var authorities = List.of(new SimpleGrantedAuthority(role.name()));

        var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, request, response);
        return "redirect:/dashboard";
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