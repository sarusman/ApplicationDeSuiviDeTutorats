package ApplicationDeSuiviDeTutorat.model;


import ApplicationDeSuiviDeTutorat.Enum.Major;
import ApplicationDeSuiviDeTutorat.Enum.Program;
import ApplicationDeSuiviDeTutorat.Enum.VisitFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "trainee",
        uniqueConstraints = @UniqueConstraint(name="uk_trainee_mail", columnNames = "mail"))
public class Trainee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK -> Mentor
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="mentor_id", nullable=false,
            foreignKey = @ForeignKey(name="fk_trainee_mentor"))
    private Mentor mentor;

    // FK -> Company (optionnel: certains apprentis peuvent ne pas encore avoir d’entreprise)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id",
            foreignKey = @ForeignKey(name="fk_trainee_company"))
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(length=80, nullable=false)
    private Program program;

    @Column(name="school_year", length=20)  // ex: "2025-2026"
    private String schoolYear;

    @Enumerated(EnumType.STRING)
    @Column(length=100, nullable=false)
    private Major major; // ex: "LSI", "BDML"

    @Column(name="last_name", nullable=false, length=100)
    private String lastName;

    @Column(name="first_name", nullable=false, length=100)
    private String firstName;  // interprétation de "first_year" -> "first_name"

    @Column(length=180, nullable=false)
    private String mail;

    @Column(length=20)
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}