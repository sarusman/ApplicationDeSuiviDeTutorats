package ApplicationDeSuiviDeTutorat.model;


import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mentor_feedback")
public class MentorFeedback {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="trainee_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_feedback_trainee"))
    private Trainee trainee;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="mentor_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_feedback_mentor"))
    private Mentor mentor;

    @Column(length=1500, nullable=false)
    private String content;

    @Column(name="created_at", nullable=false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}