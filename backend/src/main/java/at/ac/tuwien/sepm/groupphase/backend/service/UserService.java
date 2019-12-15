package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.EmailExistsException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    /**
     * Find a user in the context of Spring Security based on the email address
     * <p>
     * For more information have a look at this tutorial:
     * https://www.baeldung.com/spring-security-authentication-with-a-database
     *
     * @param email the email address
     * @return a Spring Security user
     * @throws UsernameNotFoundException is thrown if the specified user does not exists
     */
    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    /**
     * Find a application user based on the email address
     *
     * @param email the email address
     * @return a application user
     */
    User findApplicationUserByEmail(String email);

    /**
     * Create a new user
     *
     * @param user to be created
     * @return the user created
     * @throws EmailExistsException in case a user with given email already exists
     * @throws NotCreatedException in case something went wrong when accessing the database
     */
    User createUser(User user);

    boolean addLogincount(String email);

    boolean resetLogincount(String email);

    boolean unlockUser(String email);

    boolean isLocked(String email);
}
