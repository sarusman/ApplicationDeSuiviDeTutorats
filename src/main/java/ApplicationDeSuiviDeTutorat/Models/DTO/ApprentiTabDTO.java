package ApplicationDeSuiviDeTutorat.Models.DTO;


public record ApprentiTabDTO(
        Long id,
        String nom,
        String prenom,
        String adresseElectronique,
        String telephone,
        String programme,
        String entrepriseRaisonSociale,
        String missionMotsCles,
        String anneeAcademiqueDisplay
) {

    public ApprentiTabDTO(Long id, String nom, String prenom, String adresseElectronique,
                          String telephone, String programme, String entrepriseRaisonSociale,
                          String missionMotsCles, String anneeAcademiqueDisplay) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseElectronique = adresseElectronique;
        this.telephone = telephone;
        this.programme = programme;
        this.entrepriseRaisonSociale = entrepriseRaisonSociale;
        this.missionMotsCles = missionMotsCles;
        this.anneeAcademiqueDisplay = anneeAcademiqueDisplay;
    }
}