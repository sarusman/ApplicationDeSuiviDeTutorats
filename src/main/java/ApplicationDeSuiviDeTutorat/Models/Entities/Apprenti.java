package ApplicationDeSuiviDeTutorat.Models.Entities;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "apprenti")
public class Apprenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Programme programme;

    @Column(length = 50)
    private String anneeAcademique;

    @Column(length = 100)
    private String nom;

    @Column(length = 100)
    private String prenom;

    @Column(length = 120)
    private String adresseElectronique;

    @Column(length = 20)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "tuteur_entreprise_id")
    private TuteurEntreprise tuteurEntreprise;

    @ManyToOne
    @JoinColumn(name = "tuteur_pedagogique_id")
    private Utilisateur tuteurPedagogique;

    @OneToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @OneToMany(mappedBy = "apprenti")
    private List<Visite> visites;

    @OneToMany(mappedBy = "apprenti")
    private List<EvaluationEcole> evaluations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Programme getProgramme() { return programme; }
    public void setProgramme(Programme programme) { this.programme = programme; }
    public String getAnneeAcademique() { return anneeAcademique; }
    public void setAnneeAcademique(String anneeAcademique) { this.anneeAcademique = anneeAcademique; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getAdresseElectronique() { return adresseElectronique; }
    public void setAdresseElectronique(String adresseElectronique) { this.adresseElectronique = adresseElectronique; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Entreprise getEntreprise() { return entreprise; }
    public void setEntreprise(Entreprise entreprise) { this.entreprise = entreprise; }
    public TuteurEntreprise getTuteurEntreprise() { return tuteurEntreprise; }
    public void setTuteurEntreprise(TuteurEntreprise tuteurEntreprise) { this.tuteurEntreprise = tuteurEntreprise; }
    public Utilisateur getTuteurPedagogique() { return tuteurPedagogique; }
    public void setTuteurPedagogique(Utilisateur tuteurPedagogique) { this.tuteurPedagogique = tuteurPedagogique; }
    public Mission getMission() { return mission; }
    public void setMission(Mission mission) { this.mission = mission; }
    public List<Visite> getVisites() { return visites; }
    public void setVisites(List<Visite> visites) { this.visites = visites; }
    public List<EvaluationEcole> getEvaluations() { return evaluations; }
    public void setEvaluations(List<EvaluationEcole> evaluations) { this.evaluations = evaluations; }

    /**
     * Retourne la dernière visite qui a eu lieu avant la date d'aujourd'hui.
     * @return un Optional contenant la visite la plus récente, ou un Optional vide si aucune visite passée n'est trouvée.
     */
    @Transient
    public Optional<Visite> getDerniereVisite() {
        if (visites == null) {
            return Optional.empty();
        }
        LocalDate aujourdhui = LocalDate    .now();
        return visites.stream()
                .filter(v -> v.getDate() != null && v.getDate().isBefore(aujourdhui))
                .max(Comparator.comparing(Visite::getDate));
    }

    /**
     * Retourne la prochaine visite prévue à partir de la date d'aujourd'hui.
     * @return un Optional contenant la visite future la plus proche, ou un Optional vide si aucune visite future n'est trouvée.
     */
    @Transient
    public Optional<Visite> getProchaineVisite() {
        if (visites == null) {
            return Optional.empty();
        }
        LocalDate aujourdhui = LocalDate.now();
        return visites.stream()
                .filter(v -> v.getDate() != null && v.getDate().isAfter(aujourdhui))
                .min(Comparator.comparing(Visite::getDate));
    }
}
