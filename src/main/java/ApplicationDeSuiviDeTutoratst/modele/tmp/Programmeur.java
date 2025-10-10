package ApplicationDeSuiviDeTutoratst.modele.tmp;

public class Programmeur {

    private Integer idProgrammeur;
    private String nom;
    private String prenom;
    private String adresse;
    private String langageExpertise;
    private String livrePrefere;
    private Double salaire;

    public Integer getIdProgrammeur() {
        return idProgrammeur;
    }

    public void setIdProgrammeur(Integer idProgrammeur) {
        this.idProgrammeur = idProgrammeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLangageExpertise() {
        return langageExpertise;
    }

    public void setLangageExpertise(String langageExpertise) {
        this.langageExpertise = langageExpertise;
    }

    public String getLivrePrefere() {
        return livrePrefere;
    }

    public void setLivrePrefere(String livrePrefere) {
        this.livrePrefere = livrePrefere;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Programmeur(
            Integer idProgrammeur,
            String nom,
            String prenom,
            String adresse,
            String langageExpertise,
            String livrePrefere,
            Double salaire) {
        this.idProgrammeur = idProgrammeur;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.langageExpertise = langageExpertise;
        this.livrePrefere = livrePrefere;
        this.salaire = salaire;
    }

    public Programmeur() {
    }

    @Override
    public String toString() {
        return "Programmeur{" +
                "idProgrammeur=" + idProgrammeur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", langageExpertise='" + langageExpertise + '\'' +
                ", livrePrefere='" + livrePrefere + '\'' +
                ", salaire=" + salaire +
                '}';
    }
}