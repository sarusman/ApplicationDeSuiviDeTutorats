package ApplicationDeSuiviDeTutorat.repository;

import ApplicationDeSuiviDeTutorat.model.MentorFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorFeedbakcRepository extends JpaRepository<MentorFeedback, Integer> {
}
