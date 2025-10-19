package ApplicationDeSuiviDeTutorat.Models.Entities;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import jakarta.persistence.*;

@Entity
@Table(name = "apprenti")
public class Apprenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Programme programme;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(length = 120)
    private String adresseElectronique;

    @Column(length = 20)
    private String telephone;

    @OneToMany(mappedBy = "apprenti")
    private java.util.List<Visite> visites;

    @OneToMany(mappedBy = "apprenti")
    private java.util.List<EvaluationEcole> evaluations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Programme getProgramme() { return programme; }
    public void setProgramme(Programme programme) { this.programme = programme; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresseElectronique() { return adresseElectronique; }
    public void setAdresseElectronique(String adresseElectronique) { this.adresseElectronique = adresseElectronique; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public java.util.List<Visite> getVisites() { return visites; }
    public void setVisites(java.util.List<Visite> visites) { this.visites = visites; }

    public java.util.List<EvaluationEcole> getEvaluations() { return evaluations; }
    public void setEvaluations(java.util.List<EvaluationEcole> evaluations) { this.evaluations = evaluations; }
}