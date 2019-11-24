package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

//TODO: replace this class with a correct ApplicationUser JPARepository implementation
@Repository
public class UserRepository {

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


}
