
package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Enums.Role;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean login(Utilisateur utilisateur) {
        Optional<Utilisateur> existingUser = utilisateurRepository.findByUsername(utilisateur.getUsername());
        System.out.println("fdfds");
        if (existingUser.isPresent()) {
            Utilisateur user = existingUser.get();
            if (passwordEncoder.matches(utilisateur.getPassword(), user.getPassword())) {
                return true;
            }
        }
        return false;
    }
    public boolean register(Utilisateur utilisateur) {
        if (utilisateurRepository.findByUsername(utilisateur.getUsername()).isPresent()) {
            return false;
        }
        utilisateur.setRole(Role.TUTEUR);

        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurRepository.save(utilisateur);
        return true;
    }

    public Utilisateur getUtilisateurByUsername(String username) {
        return utilisateurRepository.findByUsername(username).orElse(null);
    }
}