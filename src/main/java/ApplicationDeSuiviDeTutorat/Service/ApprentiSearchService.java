package ApplicationDeSuiviDeTutorat.Service;

import ApplicationDeSuiviDeTutorat.Models.DTO.ApprentiDto;
import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import ApplicationDeSuiviDeTutorat.Repository.AnneeAlternanceRepository;
import ApplicationDeSuiviDeTutorat.Repository.ApprentiRepository;
import org.springframework.stereotype.Service;
import ApplicationDeSuiviDeTutorat.Service.ApprentiService;

import java.util.List;

@Service
public class ApprentiSearchService {

    private final ApprentiRepository apprentiRepository;
    private final AnneeAlternanceRepository anneeAlternanceRepository;
    private final AnneeAlternanceService anneeAlternanceService;

    public ApprentiSearchService(ApprentiRepository apprentiRepository, AnneeAlternanceRepository anneeAlternanceRepository, AnneeAlternanceService anneeAlternanceService) {
        this.apprentiRepository = apprentiRepository;
        this.anneeAlternanceRepository = anneeAlternanceRepository;
        this.anneeAlternanceService = anneeAlternanceService;
    }

    public List<ApprentiDto> search(String q,
                                    Long entrepriseId,
                                    String mission,
                                    String annee,
                                    Programme programme,
                                    Boolean archived) {
        List<Apprenti> res = anneeAlternanceService.search(q, entrepriseId, mission, annee, programme, archived);
        return res.stream().map(this::toDto).toList();
    }

    ApprentiDto toDto(Apprenti a) {
        if (a == null) return null;
        AnneeAlternance anneeAlternance = anneeAlternanceRepository.findLastById(a.getId());
        String entrepriseNom = (anneeAlternance.getEntreprise() != null) ? anneeAlternance.getEntreprise().getRaisonSociale() : null;
        String tuteurNom = (anneeAlternance.getTuteurPedagogique() != null) ? anneeAlternance.getTuteurPedagogique().getUsername() : null;
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
