package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique, Long> {
}
