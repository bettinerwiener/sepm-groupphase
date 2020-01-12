package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserLoginDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;

import java.util.List;

public interface AdminService {

    /**
     * Finds all users with the same username (should only be one)
     * @param username for which users are found
     * @return a list of users with @param username
     */
    List<User> findUserByName(String username);

    /**
     * see above
     * @param username
     * @return
     */
    User findOneByName(String username);

    /**
     * Updates a user
     * @param user to be updated
     * @return the updated user
     */
    User updateUser(User user);


    /**
     * @param user to delete
     * @return If delete was successful
     */
    boolean deleteUser(User user);

    boolean validate(UserLoginDto user);
}
