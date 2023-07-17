package ra.dto.respone;

import com.sun.tools.javac.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtUserResponse {
    private String username;
    private String phoneNumber ;
    private String fullName ;
    private String token;
    private String type ="Bearer";
    private Collection<? extends GrantedAuthority> listRoles;

    public JwtUserResponse(String ussername, String phoneNumber,String fullName, String token, Collection<? extends GrantedAuthority> listRoles) {
        this.username = ussername;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.token = token;
        this.listRoles = listRoles;
    }
}
