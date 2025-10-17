package ApplicationDeSuiviDeTutorat.model;


import jakarta.persistence.*;

@Entity
@Table(name = "school_evaluation")
public class SchoolEvaluation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="trainee_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_schooleval_trainee"))
    private Trainee trainee;

    @OneToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="trainee_report_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_schooleval_report"))
    private TraineeReport traineeReport;

    @OneToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="trainee_defense_id", nullable=false,
            foreignKey=@ForeignKey(name="fk_schooleval_defense"))
    private TraineeDefense traineeDefense;

}
