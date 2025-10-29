package ApplicationDeSuiviDeTutorat.Models.Entities;
import ApplicationDeSuiviDeTutorat.Models.Enums.FormatVisite;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "visite")
public class Visite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private FormatVisite format;

    @Column(length = 500)
    private String commentaires;

    @ManyToOne(fetch = FetchType.LAZY)
    private AnneeAlternance AnneeAlternance;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public FormatVisite getFormat() { return format; }
    public void setFormat(FormatVisite format) { this.format = format; }

    public String getCommentaires() { return commentaires; }
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }

    public AnneeAlternance getApprenti() { return AnneeAlternance; }
    public void setApprenti(AnneeAlternance apprenti) { this.AnneeAlternance = apprenti; }
}
