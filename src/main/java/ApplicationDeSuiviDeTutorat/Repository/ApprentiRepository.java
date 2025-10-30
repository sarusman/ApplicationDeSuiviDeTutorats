package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprentiRepository extends JpaRepository<Apprenti, Long> {

    boolean existsByAdresseElectronique(String adresseElectronique);

    boolean existsByTelephone(String telephone);

    List<Apprenti> findByTuteurPedagogique_Id(Long tuteurId);
}
