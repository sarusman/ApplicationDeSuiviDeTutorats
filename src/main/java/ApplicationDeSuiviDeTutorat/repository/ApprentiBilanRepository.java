package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprentiBilanRepository extends JpaRepository<Apprenti, Long> {

    boolean existsByAdresseElectronique(String adresseElectronique);

    boolean existsByTelephone(String telephone);

    List<Apprenti> findByTuteurPedagogique_Id(Long tuteurId);
}
