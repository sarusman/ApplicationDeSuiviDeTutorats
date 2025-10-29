// package ApplicationDeSuiviDeTutorat.Models.Dto;

package ApplicationDeSuiviDeTutorat.Models.DTO;

public record ApprentiDto(
        Long id,
        String nom,
        String prenom,
        String adresseElectronique,
        String telephone,
        String entrepriseNom,
        String tuteurNom
) {}
