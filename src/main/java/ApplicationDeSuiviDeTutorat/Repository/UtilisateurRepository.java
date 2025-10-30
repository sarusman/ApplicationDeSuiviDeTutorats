package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsById(Long id);

    @Query("SELECT DISTINCT aa.apprenti FROM AnneeAlternance aa WHERE aa.tuteurPedagogique.id = :tuteurId")
    List<Apprenti> findApprentisByTuteurId(Long tuteurId);
}
