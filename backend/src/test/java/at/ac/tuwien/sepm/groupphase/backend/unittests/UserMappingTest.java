package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.basetest.RegistrationTestData;
import at.ac.tuwien.sepm.groupphase.backend.basetest.TestData;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.UserMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserMappingTest implements RegistrationTestData {

     User user = new User();

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mapUserDtoToEntity_thenEntityHasAllProperties() {

        user.setId(ID);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        user.setIsEmployee(false);
        user.setLocked(false);

        UserDto userDto = userMapper.userToUserDto(user);

        assertAll(
            () -> assertEquals(ID, userDto.getId()),
            () -> assertEquals(TEST_FIRST_NAME, userDto.getFirstName()),
            () -> assertEquals(TEST_LAST_NAME, userDto.getLastName()),
            () -> assertEquals(TEST_EMAIL, userDto.getEmail()),
            () -> assertEquals(TEST_PASSWORD, userDto.getPassword()),
            () -> assertEquals(false, userDto.getIsEmployee()),
            () -> assertEquals(false, userDto.getLocked())
        );
    }

    @Test
    public void mapUserToUserDto_thenDtoHasAllProperties() {


        UserDto userDto = new UserDto( TEST_FIRST_NAME, TEST_LAST_NAME,TEST_EMAIL,TEST_PASSWORD);

        User user = userMapper.userDtoToUser(userDto);

        assertAll(
            () -> assertEquals(null, user.getId()),
            () -> assertEquals(TEST_FIRST_NAME, user.getFirstName()),
            () -> assertEquals(TEST_LAST_NAME, user.getLastName()),
            () -> assertEquals(TEST_EMAIL, user.getEmail()),
            () -> assertEquals(TEST_PASSWORD, user.getPassword()),
            () -> assertEquals(null, user.getIsEmployee()),
            () -> assertEquals(null, user.getLocked())
        );
    }


}
