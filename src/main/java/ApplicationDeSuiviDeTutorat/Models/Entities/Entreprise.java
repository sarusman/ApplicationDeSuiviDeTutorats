package ApplicationDeSuiviDeTutorat.Models.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String raisonSociale;

    @Column(length = 300)
    private String adresse;

    @Column(length = 500)
    private String informationsAcces;

    @OneToMany(mappedBy = "entreprise")
    private java.util.List<Mission> missions;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRaisonSociale() { return raisonSociale; }
    public void setRaisonSociale(String raisonSociale) { this.raisonSociale = raisonSociale; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getInformationsAcces() { return informationsAcces; }
    public void setInformationsAcces(String informationsAcces) { this.informationsAcces = informationsAcces; }

    public java.util.List<Mission> getMissions() { return missions; }
    public void setMissions(java.util.List<Mission> missions) { this.missions = missions; }
}