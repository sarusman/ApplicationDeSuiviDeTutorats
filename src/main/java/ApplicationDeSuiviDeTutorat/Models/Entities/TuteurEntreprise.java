package ApplicationDeSuiviDeTutorat.Models.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tuteur_entreprise")
public class TuteurEntreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nom;

    @Column(length = 100)
    private String prenom;

    @Column(length = 120)
    private String poste;

    @Column(length = 180)
    private String adresseElectronique;

    @Column(length = 20)
    private String telephone;

    @Column(length = 500)
    private String remarques;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_id", nullable = false)
    private Entreprise entreprise;

    @Override
    public String toString() {
        return prenom + " " + nom;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
    public String getAdresseElectronique() { return adresseElectronique; }
    public void setAdresseElectronique(String adresseElectronique) { this.adresseElectronique = adresseElectronique; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getRemarques() { return remarques; }
    public void setRemarques(String remarques) { this.remarques = remarques; }
    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
}
