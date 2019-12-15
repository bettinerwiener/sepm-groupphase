package at.ac.tuwien.sepm.groupphase.backend.unittests;
import at.ac.tuwien.sepm.groupphase.backend.basetest.RegistrationTestData;
import at.ac.tuwien.sepm.groupphase.backend.basetest.TestData;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class RegisterUserRepositoryTest implements RegistrationTestData {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUserThenCheckSizeWhenFindAllThenFindUserById() {
        User user = new User();
        user.setId(ID);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        user.setIsEmployee(false);
        user.setLocked(false);


        userRepository.save(user);

        assertAll(
            () -> assertEquals(5, userRepository.findAll().size()),
            () -> assertNotNull(userRepository.findById(user.getId()))
        );
    }

    @Test
    public void saveUserThenCheckSizeWhenFindAllThenFindUserByWrongId() {
        User user = new User();
        user.setId(12L);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        user.setIsEmployee(false);
        user.setLocked(false);


        userRepository.save(user);

        assertAll(
            () -> assertEquals(5, userRepository.findAll().size()),
            () -> assertNotEquals(user, userRepository.findById(100L))
        );
    }

  /*  @Test
    public void tryingToSaveUserWithWrong() {

        UserDto userDto = new UserDto( TEST_FIRST_NAME, TEST_LAST_NAME,TEST_EMAIL,TEST_PASSWORD);

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertTrue(violations.isEmpty());
    }*/




}
