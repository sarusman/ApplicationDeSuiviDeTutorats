package ApplicationDeSuiviDeTutorat.model;


import jakarta.persistence.*;

@Entity
@Table(name = "mentor")
public class Mentor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="last_name", nullable=false, length=100)
    private String lastName;

    @Column(name="first_name", nullable=false, length=100)
    private String firstName;

    @Column(length=120)
    private String job;

    @Column(length=180)
    private String mail;

    @Column(length=20)
    private String phone;

    @Column(length=500)
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}