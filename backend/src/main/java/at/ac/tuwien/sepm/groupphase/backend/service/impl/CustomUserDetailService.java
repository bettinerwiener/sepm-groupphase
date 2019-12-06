package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class CustomUserDetailService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;

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
        List<User> user = userRepository.findByEmail(email);
        if (!user.isEmpty()) return user.get(0);
        // TODO: GLEICHE EMAILS SOLLTE NICHT ERLAUBT SEIN
        throw new NotFoundException(String.format("Could not find the user with the email address %s", email));
    }

    @Override
    public boolean addLogincount(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            User user = users.get(0);
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
        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            users.get(0).setLoginCount(0);
            userRepository.saveAndFlush(users.get(0));
            return true;
        }
        return false;
    }

    @Override
    public boolean unlockUser(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            users.get(0).setLocked(false);
            userRepository.saveAndFlush(users.get(0));
            return true;
        }
        throw new NotFoundException(String.format("Could not find the user with the email address %s", email));
    }

    @Override
    public boolean isLocked(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            return users.get(0).getLocked();
        }
        return false;
    }
}
