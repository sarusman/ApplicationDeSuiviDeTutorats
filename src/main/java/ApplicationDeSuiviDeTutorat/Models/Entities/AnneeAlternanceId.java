package ApplicationDeSuiviDeTutorat.Models.Entities;

import java.io.Serializable;
import java.util.Objects;


public class AnneeAlternanceId implements Serializable {
    private Apprenti apprenti;

    private AnneeAcademique anneeAcademique;

    public AnneeAlternanceId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnneeAlternanceId anneeAlternanceId)) return false;
        AnneeAlternanceId that = (AnneeAlternanceId) o;
        return Objects.equals(apprenti, that.apprenti) &&
                Objects.equals(anneeAcademique, that.anneeAcademique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenti, anneeAcademique);
    }
}
