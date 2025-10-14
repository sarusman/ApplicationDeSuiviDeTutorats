package ApplicationDeSuiviDeTutorat.model;


import ApplicationDeSuiviDeTutorat.Enum.VisitFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visit")
public class Visit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user_id -> Trainee (interprétation)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="trainee_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_visit_trainee"))
    private Trainee trainee;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="mentor_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_visit_mentor"))
    private Mentor mentor;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(length=10, nullable=false)
    private VisitFormat format; // DISTANCE / PRESENCE

    @Column(length=1000)
    private String comments; // présent dans l’énoncé global

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public VisitFormat getFormat() {
        return format;
    }

    public void setFormat(VisitFormat format) {
        this.format = format;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}