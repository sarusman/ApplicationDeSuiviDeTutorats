package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface VisiteRepository extends JpaRepository<Visite, Long> {
    // Dernière visite STRICTEMENT avant 'today' pour un apprenti (SQL natif, tri DESC + LIMIT 1)
    @Query(
            value = "select * from visite v where v.apprenti_id = :apprentiId and v.date < :today order by v.date desc limit 1",
            nativeQuery = true
    )
    Optional<Visite> findDerniereNative(@Param("apprentiId") Long apprentiId, @Param("today") LocalDate today);

    // Prochaine visite STRICTEMENT après 'today' pour un apprenti (SQL natif, tri ASC + LIMIT 1)
    @Query(
            value = "select * from visite v where v.apprenti_id = :apprentiId and v.date > :today order by v.date asc limit 1",
            nativeQuery = true
    )
    Optional<Visite> findProchaineNative(@Param("apprentiId") Long apprentiId, @Param("today") LocalDate today);
}
