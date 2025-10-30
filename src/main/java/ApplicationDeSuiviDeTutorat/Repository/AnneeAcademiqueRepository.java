package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAcademique;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique, Long> {
    @Query("SELECT a FROM AnneeAcademique a ORDER BY a.id")
    List<AnneeAcademique> findAnneeAcademiqueInOrder();
}
