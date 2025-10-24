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

    @ManyToOne
    @JoinColumn(name = "entreprise_id", nullable = false)
    private Entreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "tuteur_id", nullable = false)
    private TuteurEntreprise tuteur;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMotsCles() { return motsCles; }
    public void setMotsCles(String motsCles) { this.motsCles = motsCles; }

    public String getMetierCible() { return metierCible; }
    public void setMetierCible(String metierCible) { this.metierCible = metierCible; }

    public String getCommentaires() { return commentaires; }
    public void setCommentaires(String commentaires) { this.commentaires = commentaires; }

    public Entreprise getEntreprise() { return entreprise; }
    public void setEntreprise(Entreprise entreprise) { this.entreprise = entreprise; }

    public TuteurEntreprise getTuteur() { return tuteur; }
    public void setTuteur(TuteurEntreprise tuteur) { this.tuteur = tuteur; }
}
