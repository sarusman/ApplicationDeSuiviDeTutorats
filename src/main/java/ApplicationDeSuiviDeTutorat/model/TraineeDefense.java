package ApplicationDeSuiviDeTutorat.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trainee_defense")
public class TraineeDefense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="trainee_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_defense_trainee"))
    private Trainee trainee;

    @Column(length=200)
    private String topic;

    @Column(name="final_grade")
    private Double finalGrade;

    @Column(length=1000)
    private String comments;

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}