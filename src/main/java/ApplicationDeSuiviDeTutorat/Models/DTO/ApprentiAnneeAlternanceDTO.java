// package ApplicationDeSuiviDeTutorat.Models.Dto;

package ApplicationDeSuiviDeTutorat.Models.DTO;

import ApplicationDeSuiviDeTutorat.Models.Entities.*;
import ApplicationDeSuiviDeTutorat.Models.Enums.Programme;

public class ApprentiAnneeAlternanceDTO{
    public Long id;
    public String nom;
    public String prenom;
    public String adresseElectronique;
    public String telephone;
    public AnneeAcademique anneeAcademique;
    public Programme programme;
    public Entreprise entreprise;
    public TuteurEntreprise tuteurEntreprise;
    public Utilisateur tuteurPedagogique;
    public Mission mission;

    public ApprentiAnneeAlternanceDTO() {}

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

    public String getAdresseElectronique() {
        return adresseElectronique;
    }

    public void setAdresseElectronique(String adresseElectronique) {
        this.adresseElectronique = adresseElectronique;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AnneeAcademique getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

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
}
