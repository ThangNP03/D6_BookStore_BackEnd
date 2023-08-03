package ra.dto.respone;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String phoneNumber;
    private String fullName ;
    private String username;
    private String avatar;
    private String address;

}
