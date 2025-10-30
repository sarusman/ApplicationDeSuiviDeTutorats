package ApplicationDeSuiviDeTutorat.Models.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150,  nullable = false, unique = true)
    private String raisonSociale;

    @Column(length = 255,  nullable = false, unique = true)
    private String adresse;

    @Column(length = 255)
    private String infosAcces;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRaisonSociale() { return raisonSociale; }
    public void setRaisonSociale(String raisonSociale) { this.raisonSociale = raisonSociale; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getInfosAcces() { return infosAcces; }
    public void setInfosAcces(String infosAcces) { this.infosAcces = infosAcces; }
}
