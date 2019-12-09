package at.ac.tuwien.sepm.groupphase.backend.endpoint;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.UserMapper;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.lang.invoke.MethodHandles;


@RestController
@RequestMapping(value = "/api/v1/user")
public class UserEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserEndpoint(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @CrossOrigin
    @PostMapping(value= "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto post(@RequestBody @Validated UserDto userDto) {
        LOGGER.info("POST /api/v1/user/register");

        User user= userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userService.createUser(user));

    }

}
