package ra.sercurity.userpricipal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.model.user.Users;
import ra.repository.IUserRepository;


@Data
@AllArgsConstructor
@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private IUserRepository userrepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userrepository.findByUsername(username);
        if (users == null){
            throw new UsernameNotFoundException("Người dùng không tồn tại !!!");
        }else {
            UserDetails userDetails = CustomUserDetail.build(users);
            return userDetails;
        }
    }
}
