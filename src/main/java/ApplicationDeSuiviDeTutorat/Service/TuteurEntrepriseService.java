package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.TuteurEntrepriseDTO;
import ApplicationDeSuiviDeTutorat.Models.Entities.TuteurEntreprise;
import ApplicationDeSuiviDeTutorat.Repository.TuteurEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TuteurEntrepriseService {

    private final TuteurEntrepriseRepository tuteurEntrepriseRepository;

    @Autowired
    public TuteurEntrepriseService(TuteurEntrepriseRepository tuteurEntrepriseRepository) {
        this.tuteurEntrepriseRepository = tuteurEntrepriseRepository;
    }

    public List<TuteurEntrepriseDTO> findByEntrepriseId(Long entrepriseId) {
        return tuteurEntrepriseRepository.findByEntrepriseId(entrepriseId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public List<TuteurEntreprise> findAll() {
        return tuteurEntrepriseRepository.findAll();
    }

    private TuteurEntrepriseDTO convertToDto(TuteurEntreprise tuteur) {
        return new TuteurEntrepriseDTO(
                tuteur.getId(),
                tuteur.getNom(),
                tuteur.getPrenom()
        );
    }
}