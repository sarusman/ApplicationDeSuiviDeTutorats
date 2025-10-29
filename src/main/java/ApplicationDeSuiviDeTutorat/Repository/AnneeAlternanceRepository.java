package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnneeAlternanceRepository extends JpaRepository<AnneeAlternance, AnneeAlternanceId> {
    @Query("select a FROM AnneeAlternance a WHERE a.apprenti = :apprentiId ORDER BY a.anneeAcademique.date_debut DESC")
    List<AnneeAlternance>  findAllByApprenti(@Param("apprentiId") Long apprentiId);
}
