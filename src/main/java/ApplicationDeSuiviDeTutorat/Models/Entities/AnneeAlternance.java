package ApplicationDeSuiviDeTutorat.Models.Entities;

import ApplicationDeSuiviDeTutorat.App;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "annee_alternance")
@IdClass(AnneeAlternanceId.class)
public class AnneeAlternance {

    @Id
    @ManyToOne
    @JoinColumn(name = "apprenti_id", nullable = false) // foreign key column
    private Apprenti apprenti;

    @Id
    @ManyToOne
    @JoinColumn(name = "annee_academique", nullable = false)
    private AnneeAcademique anneeAcademique;

    @Enumerated(EnumType.STRING)
    private Programme programme;

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

    @OneToMany(mappedBy = "AnneeAlternance")
    private List<Visite> visites;

    @OneToMany(mappedBy = "AnneeAlternance")
    private List<EvaluationEcole> evaluations;

    public Apprenti getApprenti() {
        return apprenti;
    }

    public void setApprenti(Apprenti apprenti) {
        this.apprenti = apprenti;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public Programme getProgramme() { return programme; }

    public void setProgramme(Programme programme) { this.programme = programme; }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public TuteurEntreprise getTuteurEntreprise() {
        return tuteurEntreprise;
    }

    public void setTuteurEntreprise(TuteurEntreprise tuteurEntreprise) {
        this.tuteurEntreprise = tuteurEntreprise;
    }

    public Utilisateur getTuteurPedagogique() {
        return tuteurPedagogique;
    }

    public void setTuteurPedagogique(Utilisateur tuteurPedagogique) {
        this.tuteurPedagogique = tuteurPedagogique;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public List<Visite> getVisites() {
        return visites;
    }

    public void setVisites(List<Visite> visites) {
        this.visites = visites;
    }

    public List<EvaluationEcole> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationEcole> evaluations) {
        this.evaluations = evaluations;
    }
}

