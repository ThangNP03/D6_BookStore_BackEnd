package ra.sercurity.userpricipal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ra.model.user.Users;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class CustomUserDetail implements UserDetails {

    private Long userId;
    private String username;

    private String phoneNumber;
    @JsonIgnore
    private String passWord;

    private String fullName ;

    private boolean status;
    Collection<? extends GrantedAuthority> listRoles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listRoles;
    }

    // custom lại đối tượng user thành userDetail
    public static CustomUserDetail build(Users user) {
        List<GrantedAuthority> authorityList  = user.getRoles().stream().map(
                        role->new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        return CustomUserDetail.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .passWord(user.getPassWord())
                .status(user.isStatus())
                .listRoles(authorityList)
                .build();

    }
    public void getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                // Thực hiện các thao tác với thông tin người dùng đã đăng nhập
            } else {
                String username = principal.toString();
                // Thực hiện các thao tác với thông tin người dùng đã đăng nhập
            }
        } else {
            // Không có người dùng nào đăng nhập
        }
    }
    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
