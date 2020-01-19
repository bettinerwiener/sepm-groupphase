package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserLoginDto;
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

    /**
     * Increments the login count of a user in case the wrong credentials are entered
     * @param email of the user the login is incremented for
     * @return status of the increment operation
     */
    boolean addLogincount(String email);

    /**
     * Resets the login count to 0
     * @param email of the user the login count is reset for
     * @return status of the reset operation
     */
    boolean resetLogincount(String email);

    /**
     * Unlocks the user
     * @param email of the user to be unlocked
     * @return status of the unlock operation
     */
    boolean unlockUser(String email);

    /**
     * Checks whether a user with a certain email is locked
     * @param email the lock status is checked for
     * @return the lock status
     */
    boolean isLocked(String email);

    User getUser(String username);

    User updateUser(User user);

    boolean deleteUser(String username);

    boolean validate(UserLoginDto userLoginDto);

    boolean exists(String username);
}
