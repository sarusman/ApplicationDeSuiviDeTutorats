package ApplicationDeSuiviDeTutorat.repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuteurEntrepriseRepository extends JpaRepository<TuteurEntreprise, Long> {

    List<TuteurEntreprise> findByEntreprise_Id(Long entrepriseId);
}
