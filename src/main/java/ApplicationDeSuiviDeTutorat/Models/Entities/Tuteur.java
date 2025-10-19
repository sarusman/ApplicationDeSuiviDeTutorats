package ApplicationDeSuiviDeTutorat.Models.Entities;
import jakarta.persistence.*;


@Entity
@Table(name = "tuteur")
public class Tuteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(length = 120)
    private String poste;

    @Column(length = 120)
    private String mail;

    @Column(length = 20)
    private String telephone;

    @Column(length = 500)
    private String remarques;

    @OneToMany(mappedBy = "tuteur")
    private java.util.List<Mission> missions;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getRemarques() { return remarques; }
    public void setRemarques(String remarques) { this.remarques = remarques; }

    public java.util.List<Mission> getMissions() { return missions; }
    public void setMissions(java.util.List<Mission> missions) { this.missions = missions; }
}