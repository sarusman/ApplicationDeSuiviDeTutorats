package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import ApplicationDeSuiviDeTutorat.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> trouverParUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public List<Apprenti> trouverApprentisParTuteurId(Long tuteurId) {
        return utilisateurRepository.findApprentisByTuteurId(tuteurId);
    }

    public List<Utilisateur> trouverTous() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur enregistrer(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void supprimer(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
