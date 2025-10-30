// package ApplicationDeSuiviDeTutorat.Models.Dto;

package ApplicationDeSuiviDeTutorat.Models.DTO;

import ApplicationDeSuiviDeTutorat.Models.Entities.*;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;

public record ApprentiAnneeAlternanceDTO(
        Long id,
        String nom,
        String prenom,
        String adresseElectronique,
        String telephone,
        AnneeAcademique anneeAcademique,
        Programme programme,
        Entreprise entreprise,
        TuteurEntreprise tuteurEntreprise,
        Utilisateur tuteurPedagogique,
        Mission mission
) {
}
