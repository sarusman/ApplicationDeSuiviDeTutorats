package ApplicationDeSuiviDeTutorat.Models.Entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "annee_academique")
public class AnneeAcademique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date_debut;

    @Column
    private Date date_fin;

    @Column(length = 100)
    private String commentaire;

    public String name() {
        return date_debut + " : " + date_fin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
