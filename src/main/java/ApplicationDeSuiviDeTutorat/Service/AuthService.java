package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Models.Enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UtilisateurService utilisateurService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(Utilisateur utilisateur) {
        Optional<Utilisateur> existingUser = utilisateurService.trouverParUsername(utilisateur.getUsername());
        if (existingUser.isPresent()) {
            Utilisateur user = existingUser.get();
            return passwordEncoder.matches(utilisateur.getPassword(), user.getPassword());
        }
        return false;
    }

    public boolean register(Utilisateur utilisateur) {
        if (utilisateurService.trouverParUsername(utilisateur.getUsername()).isPresent()) {
            return false;
        }
        utilisateur.setRole(Role.TUTEUR);
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurService.enregistrer(utilisateur);
        return true;
    }

    public Utilisateur getUtilisateurByUsername(String username) {
        return utilisateurService.trouverParUsername(username).orElse(null);
    }
}
