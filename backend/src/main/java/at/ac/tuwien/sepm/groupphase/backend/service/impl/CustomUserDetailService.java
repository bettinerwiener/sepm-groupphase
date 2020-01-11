package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.EmailExistsException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.debug("Load all user by email");
        try {
            User user = findApplicationUserByEmail(email);
            List<GrantedAuthority> grantedAuthorities;
            if (user.getIsEmployee())
                grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
            else
                grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_USER");

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public User findApplicationUserByEmail(String email) {
        LOGGER.debug("Find application user by email");
        User user = userRepository.findFirstByEmail(email);
        if (!(user==null)) return user;
        throw new NotFoundException(String.format("Could not find the user with the email address %s", email));
    }


    @Override
    public User createUser (User user) throws EmailExistsException, NotCreatedException {
        log.info("Creating user",user);

        if (userRepository.findFirstByEmail(user.getEmail()) != null) {
            log.error("email adress already in use", user.getEmail());
            throw new EmailExistsException("There already is an account with the email adress: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return this.userRepository.save(user);
        } catch (DataAccessException notCreated) {
            log.error("user could not be created", user);
            throw new NotCreatedException(String.format("The user with emailadress: %s could not be created: %s",
                user.getEmail(), notCreated.getMessage()));
        }
    }


    public boolean addLogincount(String email) {
        User user = userRepository.findFirstByEmail(email);
        if (!(user==null)) {
            user.setLoginCount(user.getLoginCount() + 1);
            if (user.getLoginCount() > 3) {
                user.setLocked(true);
            }
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean resetLogincount(String email) {
        User user = userRepository.findFirstByEmail(email);
        if (!(user==null)) {
            user.setLoginCount(0);
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean unlockUser(String email) {
        User user = userRepository.findFirstByEmail(email);
        if (!(user==null)) {
            user.setLocked(false);
            userRepository.saveAndFlush(user);
            return true;
        }
        throw new NotFoundException(String.format("Could not find the user with the email address %s", email));
    }

    @Override
    public boolean isLocked(String email) {
        User user = userRepository.findFirstByEmail(email);
        if (!(user==null)) {
            if (user.getLocked() == null) {
                return false;
            }
            return user.getLocked();
        }
        return false;
    }
}
