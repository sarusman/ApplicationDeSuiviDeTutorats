package ApplicationDeSuiviDeTutorat.Models.Entities;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import jakarta.persistence.*;

@Entity
@Table(name = "apprenti")
public class Apprenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nom;

    @Column(length = 100)
    private String prenom;

    @Column(length = 120)
    private String adresseElectronique;

    @Column(length = 20)
    private String telephone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getAdresseElectronique() { return adresseElectronique; }
    public void setAdresseElectronique(String adresseElectronique) { this.adresseElectronique = adresseElectronique; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

}
