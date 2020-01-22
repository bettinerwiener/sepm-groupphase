package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserLoginDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.EmailExistsException;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.LinkedList;
import java.util.List;

@Service
public class SimpleAdminService implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SimpleAdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findUserByName(String username) {
        LOGGER.debug("Admin finding User like: " + username);
        List<User> users = userRepository.findByEmailContaining(username);
  //      List<User> users = new LinkedList<User>();
        for (User u : users
        ) {
            u.setPassword("yourtinysecret");
        }
        return users;
    }

    @Override
    public User findOneByName(String username) {
        LOGGER.debug("Admin finding User with username: " + username);
        if (userRepository.existsByEmail(username)) {
            User user = userRepository.findFirstByEmail(username);
            if (user != null) {
                user.setPassword("yourtinysecret");
            }
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        User helpUser = userRepository.findFirstByEmail(user.getEmail());
        LOGGER.debug("Updating User with ID: " + user.getId());
        if (userRepository.existsByEmail(user.getEmail())) {
            if (helpUser.getEmail().equals(user.getEmail())) {
                // if this id already owns this email
            } else {
                // email is already used by an other user
                throw new EmailExistsException("Email is already in use!");
            }
        }

        if (user.getPassword().equals("yourtinysecret")) {
            user.setPassword(helpUser.getPassword());
        } else {
            String pw = user.getPassword();
            user.setPassword(passwordEncoder.encode(pw));
        }
        user.setLocked(helpUser.getLocked());
        user.setIsEmployee(helpUser.getIsEmployee());

        userRepository.saveAndFlush(user);
        user.setPassword("yourtinysecret");
        return user;
    }

    @Override
    public boolean deleteUser(String  username) {
        User user = userRepository.findFirstByEmail(username);
        if (user == null) {
            return false;
        }
        LOGGER.debug("Deleting User with ID: " + user.getId());
        userRepository.deleteById(user.getId());
        userRepository.flush();
        return true;
    }

    @Override
    public boolean validate(UserLoginDto user) {
        LOGGER.debug("validate Admin " + user.getEmail());
        User help = userRepository.findFirstByEmail(user.getEmail());
        return passwordEncoder.matches(user.getPassword(), help.getPassword());
    }

}
