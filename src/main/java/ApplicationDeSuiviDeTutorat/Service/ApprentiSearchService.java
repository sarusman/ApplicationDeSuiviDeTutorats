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
    private final ApprentiService apprentiService;

    public ApprentiSearchService(ApprentiBilanRepository repo, ApprentiService apprentiService) {
        this.repo = repo;
        this.apprentiService = apprentiService;
    }

    public List<ApprentiDto> search(String q,
                                    Long entrepriseId,
                                    String mission,
                                    String annee,
                                    Programme programme,
                                    Boolean archived) {
        List<Apprenti> res = repo.search(q, entrepriseId, mission, annee, programme, archived);
        return res.stream().map(a -> apprentiService
                .getApprentiDtoById(a.getId())
                .orElseThrow()).toList();
    }
}
