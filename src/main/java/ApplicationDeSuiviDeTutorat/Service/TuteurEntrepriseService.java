package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import ApplicationDeSuiviDeTutorat.repository.TuteurEntrepriseRepository;
import ApplicationDeSuiviDeTutorat.repository.TuteurEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TuteurEntrepriseService {

    private final TuteurEntrepriseRepository tuteurEntrepriseRepository;

    @Autowired
    public TuteurEntrepriseService(TuteurEntrepriseRepository tuteurEntrepriseRepository) {
        this.tuteurEntrepriseRepository = tuteurEntrepriseRepository;
    }

    public List<TuteurEntreprise> findByEntrepriseId(Long entrepriseId) {
        return (List<TuteurEntreprise>) tuteurEntrepriseRepository.findByEntreprise_Id((entrepriseId));
    }
}