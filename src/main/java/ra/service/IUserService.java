package ra.service;

import ra.model.user.Users;

public interface IUserService extends IGeneric<Users, Long >{
    Users findByUsername(String username);
    boolean existsByUsername (String username );
    boolean existsByPhoneNumber(String phoneNumber);
}
