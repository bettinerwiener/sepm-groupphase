package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
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
        for (User u : users
        ) {
            u.setPassword("yourtinysecret");
        }
        return users;
    }

    @Override
    public User findOneByName(String username) {
        LOGGER.debug("Admin finding User with username: " + username);
        User user = userRepository.findFirstByEmail(username);
        user.setPassword("yourtinysecret");
        return user;
    }

    @Override
    public User updateUser(User user) {
        LOGGER.debug("Updating User with ID: " + user.getId());
        if (user.getPassword().equals("yourtinysecret")) {
            user.setPassword(userRepository.findFirstById(user.getId()).getPassword());
            userRepository.saveAndFlush(user);
        } else {
            String pw = user.getPassword();
            user.setPassword(passwordEncoder.encode(pw));
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        LOGGER.debug("Deleting User with ID: " + user.getId());
        userRepository.deleteById(user.getId());
        userRepository.flush();
        if (userRepository.existsById(user.getId())) {
            return false;
        }
        return true;
    }

}
