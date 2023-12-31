package ra.controller;

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
import ra.dto.respone.*;
import ra.model.ReturnAndBorrowBooks;
import ra.model.user.RoleName;
import ra.model.user.Roles;
import ra.model.user.Users;
import ra.sercurity.jwt.JwtTokenProvider;
import ra.sercurity.userpricipal.CustomUserDetail;
import ra.service.IMPL.RoleService;
import ra.service.IMPL.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/getAll")
    public List<Users> getAll(){
        List<Users> usersList = userService.findAll();
        return usersList;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserResponse userResponse, @PathVariable Long id){
        Users users = userService.findById(id);
        users.setFullName(userResponse.getFullName());
        users.setUsername(userResponse.getUsername());
        users.setPhoneNumber(userResponse.getPhoneNumber());
        users.setAvatar(userResponse.getAvatar());
        users.setAddress(userResponse.getAddress());
        userService.save(users);
        return ResponseEntity.ok(new ResponseMessage("Chỉnh sửa thông tin cá nhân thành công !!!"));
    }
    @PostMapping("/signIn")
    public ResponseEntity<?> login(@RequestBody FormLogin formLogin) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(formLogin.getUsername(), formLogin.getPassWord())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
            String token = tokenProvider.createToken(customUserDetail);
            JwtUserResponse response = new JwtUserResponse(customUserDetail.getUserId(), customUserDetail.getUsername() ,customUserDetail.getPhoneNumber(), customUserDetail.getFullName(), customUserDetail.getAvatar(), token, customUserDetail.getListRoles());
            return ResponseEntity.ok(response);



        } catch (AuthenticationException e) {
            // Xử lý khi tên người dùng hoặc mật khẩu không chính xác

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản hoặc mật khẩu không đúng !!!");
        }
    }
    @PostMapping("/signUp")
    public ResponseEntity<?> register(@RequestBody FormRegister formRegister){
        if(userService.existsByUsername(formRegister.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tên tài khoản đã tồn tại!!!");
        }
        if(userService.existsByPhoneNumber(formRegister.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Số điện thoại đã được đăng ký !!!");
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
                        break;
                    case "pm":
                        listRoles.add(roleService.findByRoleName(RoleName.PM));
                    case "user":
                        listRoles.add(roleService.findByRoleName(RoleName.USER));
                }
            });
        }
        Users user = new Users(formRegister.getUsername(), formRegister.getPhoneNumber(), formRegister.getFullName(),passwordEncoder.encode(formRegister.getPassWord()),formRegister.getAddress(), listRoles);
        user.setStatus(true);
        userService.save(user);
        return ResponseEntity.ok(new ResponseMessage("Register success"));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ResponseMessage> changePassword(@RequestBody ChangePassword changePasswordRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Object principal = authentication.getPrincipal();
            if (!(principal instanceof CustomUserDetail)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            CustomUserDetail customUserDetails = (CustomUserDetail) principal;
            if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), customUserDetails.getPassword())) {
                return ResponseEntity.badRequest().body(new ResponseMessage("Mật khẩu cũ không trùng khớp !!!"));
            }

            Users user = userService.findByUsername(customUserDetails.getUsername());
            user.setPassWord(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userService.save(user);

            return ResponseEntity.ok(new ResponseMessage("Thay đổi mật khẩu thành công !!!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Đã xảy ra lỗi server."));
        }

    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findListCartByUserID(@PathVariable Long id) {
        Users users = userService.findById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
