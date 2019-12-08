package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.EmailExistsException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
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
            if (user.getEmployee())
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

        if (userRepository.findFirstByEmail(email) != null) {
            return userRepository.findFirstByEmail(email);
        }else{
            throw new NotFoundException(String.format("Could not find the user with the email address %s", email));
        }


    }

    @Override
    public User createUser (User user) throws EmailExistsException, NotCreatedException {
        LOGGER.info("Creating user");


        if (userRepository.findFirstByEmail(user.getEmail()) != null) {
            throw new EmailExistsException("There already is an account with the email adress: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return this.userRepository.save(user);
        } catch (NotCreatedException notCreated) {
            throw new NotCreatedException(String.format("The user with emailadress: %s could not be created: %s",
                user.getEmail(), notCreated.getMessage()));
        }
    }


}
