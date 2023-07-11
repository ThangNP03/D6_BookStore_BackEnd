package ra.controller;

import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.dto.respone.FormLogin;
import ra.dto.respone.FormRegister;
import ra.dto.respone.JwtUserResponse;
import ra.dto.respone.ResponseMessage;
import ra.model.user.RoleName;
import ra.model.user.Roles;
import ra.model.user.Users;
import ra.sercurity.jwt.JwtTokenProvider;
import ra.sercurity.userpricipal.CustomUserDetail;
import ra.service.IMPL.RoleService;
import ra.service.IMPL.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookStore/auth")
public class AuthorController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signIn")
    public ResponseEntity<?> login(@RequestBody FormLogin formLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(formLogin.getUsername(), formLogin.getPassWord())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            String token = tokenProvider.createToken(customUserDetail);
            JwtUserResponse response = new JwtUserResponse(customUserDetail.getUsername(), customUserDetail.getPhoneNumber(), token, customUserDetail.getListRoles());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            // Xử lý khi tên người dùng hoặc mật khẩu không chính xác

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản hoặc mật khẩu không đúng !!!");
        }
    }
    @PostMapping("/signUp")
    public ResponseEntity<ResponseMessage> register(@RequestBody FormRegister formRegister){

        if(userService.existsByPhoneNumber(formRegister.getPhoneNumber())){
            return ResponseEntity.ok(new ResponseMessage("Số điện thoại đã tồn tại!!!!"));
        }
        if(userService.existsByUsername(formRegister.getUsername())){
            return ResponseEntity.ok(new ResponseMessage("Tên đăng nhập đã tồn tại!!!!!!"));
        }

        Set<String> roles = formRegister.getRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (roles== null||roles.isEmpty()){
            listRoles.add(roleService.findByRoleName(RoleName.USER));
        }else {
            roles.forEach(role->{
                switch (role){
                    case "admin":
                        listRoles.add(roleService.findByRoleName(RoleName.ADMIN));
                    case "pm":
                        listRoles.add(roleService.findByRoleName(RoleName.PM));
                    case "user":
                        listRoles.add(roleService.findByRoleName(RoleName.USER));
                }
            });
        }
        Users user = new Users(formRegister.getUsername(), formRegister.getPhoneNumber(), formRegister.getFullName(),passwordEncoder.encode(formRegister.getPassWord()), listRoles);
        userService.save(user);
        return ResponseEntity.ok(new ResponseMessage("Register success"));
    }
}
