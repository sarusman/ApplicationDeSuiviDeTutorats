package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDto;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiBilanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprentiSearchService {

    private final ApprentiBilanRepository repo;

    public ApprentiSearchService(ApprentiBilanRepository repo) {
        this.repo = repo;
    }

    public List<ApprentiDto> search(String q,
                                    Long entrepriseId,
                                    String mission,
                                    String annee,
                                    Programme programme,
                                    Boolean archived) {
        List<Apprenti> res = repo.search(q, entrepriseId, mission, annee, programme, archived);
        return res.stream().map(this::toDto).toList();
    }

    private ApprentiDto toDto(Apprenti a) {
        String entrepriseNom = (a.getEntreprise() != null) ? a.getEntreprise().getNom() : null;
        String tuteurNom = (a.getTuteurPedagogique() != null) ? a.getTuteurPedagogique().getUsername() : null;
        return new ApprentiDto(
                a.getId(),
                a.getNom(),
                a.getPrenom(),
                a.getAdresseElectronique(),
                a.getTelephone(),
                entrepriseNom,
                tuteurNom
        );
    }
}
