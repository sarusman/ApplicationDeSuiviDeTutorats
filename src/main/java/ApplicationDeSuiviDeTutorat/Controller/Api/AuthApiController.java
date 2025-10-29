package ApplicationDeSuiviDeTutorat.Controller.Api;

import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Role;
import ApplicationDeSuiviDeTutorat.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import ApplicationDeSuiviDeTutorat.Models.Records.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentification (équivalent du contrôleur MVC /login et /register)")
public class AuthApiController {

    private final AuthService authService;
    private final SecurityContextRepository securityContextRepository;

    public AuthApiController(AuthService authService,
                             SecurityContextRepository securityContextRepository) {
        this.authService = authService;
        this.securityContextRepository = securityContextRepository;
    }

    @PostMapping("/login")
    @Operation(summary = "Connexion (équivalent POST /login)")
    public AuthResponse login(@RequestBody AuthRequest body,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        Utilisateur tryUser = new Utilisateur();
        tryUser.setUsername(body.username());
        tryUser.setPassword(body.password());

        // même vérif que dans  AuthController
        if (!authService.login(tryUser)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
        }

        Utilisateur dbUser = authService.getUtilisateurByUsername(body.username());
        Role role = dbUser.getRole();

        request.getSession(true).setAttribute("user", dbUser);

        var authorities = List.of(new SimpleGrantedAuthority(role.name()));

        var authentication = new UsernamePasswordAuthenticationToken(
                dbUser.getUsername(),
                null,
                authorities
        );


        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, request, response);

        return new AuthResponse(
                dbUser.getId(),
                dbUser.getUsername(),
                dbUser.getRole().name()
        );
    }

    @PostMapping("/register")
    @Operation(summary = "Inscription (équivalent POST /register)")
    public RegisterResponse register(@RequestBody RegisterRequest body) {

        Utilisateur utilisateur = new Utilisateur(body.username(), body.password());

        boolean ok = authService.register(utilisateur);
        if (!ok) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inscription impossible");
        }

        return new RegisterResponse(
                utilisateur.getUsername(),
                "created"
        );
    }
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Déconnexion")
    public void logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }
    }
}
