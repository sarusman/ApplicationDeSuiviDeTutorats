package ApplicationDeSuiviDeTutorat.Models.Entities;
import jakarta.persistence.*;


@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String motsCles;

    @Column(length = 200)
    private String metierCible;

    @Column(length = 500)
    private String commentaires;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMotsCles() { return motsCles; }
    public void setMotsCles(String motsCles) { this.motsCles = motsCles; }

    public String getMetierCible() { return metierCible; }
    public void setMetierCible(String metierCible) { this.metierCible = metierCible; }

    public String getCommentaires() { return commentaires; }
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }
}
