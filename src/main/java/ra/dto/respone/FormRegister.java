package ra.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormRegister {
    private String username ;
    private String phoneNumber;
    private String passWord;
    private String fullName ;
    private  String address;
    private Set<String> roles;
}
