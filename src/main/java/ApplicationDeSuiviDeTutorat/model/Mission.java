package ApplicationDeSuiviDeTutorat.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1–1 : une mission principale par apprenti (ajuste en 1–N si nécessaire)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="trainee_id", nullable=false,
            foreignKey = @ForeignKey(name="fk_mission_trainee"))
    private Trainee trainee;

    @ElementCollection
    @CollectionTable(name="mission_keyword",
            joinColumns = @JoinColumn(name="mission_id",
                    foreignKey=@ForeignKey(name="fk_missionkeyword_mission")))
    @Column(name="keyword", length=60, nullable=false)
    private List<String> keywords = new ArrayList<>(); // 3NF via table de collection

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

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}