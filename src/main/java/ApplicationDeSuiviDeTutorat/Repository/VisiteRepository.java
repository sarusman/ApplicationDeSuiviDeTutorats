package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VisiteRepository extends JpaRepository<Visite, Long> {
    // Dernière visite STRICTEMENT avant 'today' pour un apprenti (SQL natif, tri DESC + LIMIT 1)
    @Query(
            value = "SELECT v.* FROM visite v JOIN annee_alternance aal ON v.annee_alternance_id = aal.id WHERE aal.apprenti_id = :apprentiId AND v.`date` < :today order by v.`date` limit 1",
            nativeQuery = true
    )
    Optional<Visite> findDerniereNative(@Param("apprentiId") Long apprentiId, @Param("today") LocalDate today);

    // Prochaine visite STRICTEMENT après 'today' pour un apprenti (SQL natif, tri ASC + LIMIT 1)
    @Query(
            value = "SELECT v.* FROM visite v JOIN annee_alternance aal ON v.annee_alternance_id = aal.id WHERE aal.apprenti_id = :apprentiId AND v.`date` > :today order by v.`date` limit 1",
            nativeQuery = true
    )
    Optional<Visite> findProchaineNative(@Param("apprentiId") Long apprentiId, @Param("today") LocalDate today);
}
