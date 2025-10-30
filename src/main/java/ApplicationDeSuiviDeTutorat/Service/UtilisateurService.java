package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Exceptions.DuplicateUsernameException;
import ApplicationDeSuiviDeTutorat.Exceptions.UtilisateurNotFoundException;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> trouverParUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public Utilisateur getByUsernameOrThrow(String username) {
        return utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable: " + username));
    }

    public List<Apprenti> trouverApprentisParTuteurId(Long tuteurId) {
        return utilisateurRepository.findApprentisByTuteurId(tuteurId);
    }

    public List<Utilisateur> trouverTous() {
        return utilisateurRepository.findAll();
    }

    @Transactional
    public Utilisateur enregistrer(Utilisateur utilisateur) {
        String username = utilisateur.getUsername();
        if (username != null && utilisateurRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("Ce nom d'utilisateur est déjà pris.");
        }
        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public void supprimer(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new UtilisateurNotFoundException("Utilisateur non trouvé avec l'id: " + id);
        }
        utilisateurRepository.deleteById(id);
    }
}
