package at.ac.tuwien.sepm.groupphase.backend.endpoint;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.UserMapper;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.lang.invoke.MethodHandles;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserEndpoint {


    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserEndpoint(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @CrossOrigin
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a user", authorizations = {@Authorization(value = "apiKey")})
    public UserDto post(@RequestBody @Validated UserDto userDto) {
        log.info("POST /api/v1/user/register: creating new user");

        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userService.createUser(user));

    }

    @GetMapping(value = "/profile")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a user", authorizations = {@Authorization(value = "apiKey")})
    public UserDto getProfile() {
        log.info("GET /api/v1/user/profile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.getUser(username);
        return userMapper.userToUserDto(user);
    }

}
