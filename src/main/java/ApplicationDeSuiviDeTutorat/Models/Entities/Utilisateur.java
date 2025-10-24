package ApplicationDeSuiviDeTutorat.Models.Entities;

import ApplicationDeSuiviDeTutorat.Models.Enums.Role;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "tuteurPedagogique")
    private List<Apprenti> apprentis;


    public Utilisateur() {
    }

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public List<Apprenti> getApprentis() { return apprentis; }
    public void setApprentis(List<Apprenti> apprentis) { this.apprentis = apprentis; }
}
