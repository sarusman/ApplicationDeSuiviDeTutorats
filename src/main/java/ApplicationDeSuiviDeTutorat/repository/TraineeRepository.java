package ApplicationDeSuiviDeTutorat.repository;

import ApplicationDeSuiviDeTutorat.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
}
