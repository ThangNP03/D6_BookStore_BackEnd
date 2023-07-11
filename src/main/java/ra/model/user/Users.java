package ra.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "password")
    @Size(min = 6)
    private String passWord;
    @Column(name = "fullName")
    private String fullName ;
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Roles> roles = new HashSet<>();

    public Users(String username,String phoneNumber, String fullName, String passWord, Set<Roles> roles) {
       this.username= username;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.fullName = fullName;
        this.roles = roles;
    }
}
