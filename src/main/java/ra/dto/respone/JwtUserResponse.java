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
    private String phoneNumber ;
    private String token;
    private String type ="Bearer";
    private Collection<? extends GrantedAuthority> listRoles;

    public JwtUserResponse(String phoneNumber, String token, Collection<? extends GrantedAuthority> listRoles) {
        this.phoneNumber = phoneNumber;
        this.token = token;
        this.listRoles = listRoles;
    }
}
