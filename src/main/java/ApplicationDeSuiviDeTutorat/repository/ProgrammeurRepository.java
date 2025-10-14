package ApplicationDeSuiviDeTutorat.repository;

import ApplicationDeSuiviDeTutorat.model.Mission;
import ApplicationDeSuiviDeTutorat.model.Programmeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammeurRepository extends JpaRepository<Programmeur, Integer> {
}
