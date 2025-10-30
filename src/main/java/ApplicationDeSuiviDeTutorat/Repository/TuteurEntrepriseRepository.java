package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuteurEntrepriseRepository extends JpaRepository<TuteurEntreprise, Long> {
    boolean existsByAdresseElectroniqueIgnoreCase(String adresseElectronique);
    boolean existsByTelephone(String telephone);
    List<TuteurEntreprise> findByEntrepriseId(Long entrepriseId);
}
