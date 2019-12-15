package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find all users with the given email.
     *
     * @return all users with given email
     */
    User findFirstByEmail (String email);

    List<User> findByEmailContaining (String email);

    List<User> findByEmail(String email);

    User findFirstById(Long id);






 /*
    private final Customer user;
    private final Customer admin;

    @Autowired
    public UserRepository(PasswordEncoder passwordEncoder) {
        user = new Customer("user@email.com", passwordEncoder.encode("password"), false);
        admin = new Customer("admin@email.com", passwordEncoder.encode("password"), true);
    }

    public Customer findUserByEmail(String email) {
        if (email.equals(user.getEmail())) return user;
        if (email.equals(admin.getEmail())) return admin;
        return null; // In this case null is returned to fake Repository behavior
    }
*/

}
