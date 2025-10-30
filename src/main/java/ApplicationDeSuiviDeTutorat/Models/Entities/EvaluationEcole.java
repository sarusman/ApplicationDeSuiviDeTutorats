package ApplicationDeSuiviDeTutorat.Models.Entities;
import jakarta.persistence.*;

@Entity
@Table(name = "evaluation_ecole")
public class EvaluationEcole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String theme;

    @Column
    private Integer noteFinale;

    @Column(length = 500)
    private String commentaires;

    @Column
    private java.time.LocalDate dateSoutenance;

    @ManyToOne
    private AnneeAlternance AnneeAlternance;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public Integer getNoteFinale() { return noteFinale; }
    public void setNoteFinale(Integer noteFinale) { this.noteFinale = noteFinale; }

    public String getCommentaires() { return commentaires; }
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }

    public java.time.LocalDate getDateSoutenance() { return dateSoutenance; }
    public void setDateSoutenance(java.time.LocalDate dateSoutenance) { this.dateSoutenance = dateSoutenance; }

    public AnneeAlternance getApprenti() { return AnneeAlternance; }
    public void setApprenti(AnneeAlternance apprenti) { this.AnneeAlternance = apprenti; }
}