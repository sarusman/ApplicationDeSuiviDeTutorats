package ApplicationDeSuiviDeTutorat.Models.DTO;

import ApplicationDeSuiviDeTutorat.Models.Entities.AnneeAlternance;
import ApplicationDeSuiviDeTutorat.Models.Entities.Apprenti;

public class ApprentiDetailDTO {
    public Apprenti apprenti;
    public AnneeAlternance anneeAlternance;

    // Constructeurs
    public ApprentiDetailDTO() {}

    public ApprentiDetailDTO(Apprenti apprenti, AnneeAlternance anneeAlternance) {
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
