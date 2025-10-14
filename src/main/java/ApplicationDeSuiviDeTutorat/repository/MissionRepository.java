package ApplicationDeSuiviDeTutorat.repository;

import ApplicationDeSuiviDeTutorat.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
}
