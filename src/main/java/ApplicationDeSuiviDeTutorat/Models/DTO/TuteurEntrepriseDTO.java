package ApplicationDeSuiviDeTutorat.Models.DTO;

public class TuteurEntrepriseDTO {

    private Long id;
    private String nom;
    private String prenom;

    // Constructeurs
    public TuteurEntrepriseDTO() {}

    public TuteurEntrepriseDTO(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}