package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteRepository extends JpaRepository<Visite, Long> {
}
