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
     * Find first user with the given email.
     * @param email
     * @return first user with given email
     */
    User findFirstByEmail (String email);

    /**
     * Find user with the given email.
     * @param email
     * @return user with given email
     */
    List<User> findByEmailContaining (String email);

    List<User> findByEmail(String email);

    User findFirstById(Long id);

}
