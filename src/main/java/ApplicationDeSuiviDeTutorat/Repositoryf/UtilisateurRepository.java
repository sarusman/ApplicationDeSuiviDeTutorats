package ApplicationDeSuiviDeTutorat.Repositoryf;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ApplicationDeSuiviDeTutorat.Models.Entities.Utilisateur;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsById(Long id);

    @Query("SELECT a FROM Apprenti a WHERE a.tuteurPedagogique.id = :tuteurId")
    List<Apprenti> findApprentisByTuteurId(Long tuteurId);

}
