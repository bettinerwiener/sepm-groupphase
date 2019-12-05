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
    public void givenNothing_whenMapUserDtoToEntity_thenEntityHasAllProperties() {

        user.setId(ID);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);

        UserDto userDto = userMapper.entityToDto(user);

        assertAll(
            () -> assertEquals(ID, userDto.getId()),
            () -> assertEquals(TEST_FIRST_NAME, userDto.getFirstName()),
            () -> assertEquals(TEST_LAST_NAME, userDto.getLastName()),
            () -> assertEquals(TEST_EMAIL, userDto.getEmail()),
            () -> assertEquals(TEST_PASSWORD, userDto.getPassword())
        );
    }


}
