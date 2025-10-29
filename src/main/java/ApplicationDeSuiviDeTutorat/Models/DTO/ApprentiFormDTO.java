package ApplicationDeSuiviDeTutorat.Models.DTO;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;

public class ApprentiFormDTO{
    private Apprenti apprenti;
    private AnneeAlternance anneeAlternance;

    // Constructeurs
    public ApprentiFormDTO() {}

    public ApprentiFormDTO(Apprenti apprenti,  AnneeAlternance anneeAlternance) {
        this.apprenti = apprenti;
        this.anneeAlternance = anneeAlternance;
    }

    public Apprenti getApprenti() {
        return apprenti;
    }

    public void setApprenti(Apprenti apprenti) {
        this.apprenti = apprenti;
    }

    public AnneeAlternance getAnneeAlternance() {
        return anneeAlternance;
    }

    public void setAnneeAlternance(AnneeAlternance anneeAlternance) {
        this.anneeAlternance = anneeAlternance;
    }
}
