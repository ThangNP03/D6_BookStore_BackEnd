package ra.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ra.model.ReturnAndBorrowBooks;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @JsonIgnore
    @Column(name = "password")
    @Size(min = 6)
    private String passWord;
    @Column(name = "fullName")
    private String fullName ;
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "address")
    private String address;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Roles> roles = new HashSet<>();
}
