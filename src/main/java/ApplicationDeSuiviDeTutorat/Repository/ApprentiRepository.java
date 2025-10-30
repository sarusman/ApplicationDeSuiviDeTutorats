package ApplicationDeSuiviDeTutorat.Repository;

import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprentiRepository extends JpaRepository<Apprenti, Long> {

    boolean existsByAdresseElectronique(String adresseElectronique);

    boolean existsByTelephone(String telephone);
}
