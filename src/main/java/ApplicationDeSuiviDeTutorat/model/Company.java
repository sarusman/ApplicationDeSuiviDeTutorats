package ApplicationDeSuiviDeTutorat.model;


import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=150)
    private String name;

    @Column(length=255)
    private String address;

    @Column(name = "practical_information", length=500)
    private String practicalInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPracticalInformation() {
        return practicalInformation;
    }

    public void setPracticalInformation(String practicalInformation) {
        this.practicalInformation = practicalInformation;
    }
}