package ra.sercurity.userpricipal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.model.user.Users;
import ra.repository.IUserRepository;
import ra.service.IMPL.UserService;

import java.util.Optional;


@Data
@AllArgsConstructor
@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private IUserRepository userrepository;
    @Autowired
    private UserService userService;
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
    public Users getCurrentUser(){
        Optional<Users> user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        if(userrepository.existsByUsername(userName)){
            user = Optional.ofNullable(userService.findByUsername(userName));
        } else {
            user = Optional.of(new Users());
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
