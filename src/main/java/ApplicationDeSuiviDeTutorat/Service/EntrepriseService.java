package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.Entreprise;
import ApplicationDeSuiviDeTutorat.Repository.EntrepriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }
}
