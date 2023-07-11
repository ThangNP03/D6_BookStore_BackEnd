package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.user.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    boolean existsByUsername (String username );
    boolean existsByPhoneNumber(String phoneNumber);

}
