package at.ac.tuwien.sepm.groupphase.backend.endpoint;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserLoginDto;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUser(username);
        log.info("GET /api/v1/user/profile " + username);
        return userMapper.userToUserDto(user);
    }

    @CrossOrigin
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a user", authorizations = {@Authorization(value = "apiKey")})
    public UserDto update(@RequestBody UserDto userDto) {
        log.info("POST /api/v1/user/update " + userDto.getEmail());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUser(username);
        userDto.setId(user.getId());
        return userMapper.userToUserDto(userService.updateUser(userMapper.userDtoToUser(userDto)));
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a user", authorizations = {@Authorization(value = "apiKey")})
    public boolean delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("Delelte /api/v1/user/delete " + username);
        return userService.deleteUser(username);
    }


    @CrossOrigin(origins = "*")
    @PostMapping(value = "/validate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Validate Admin", authorizations = {@Authorization(value = "apiKey")})
    public boolean validate(@RequestBody UserLoginDto userLoginDto) {
        log.info("Post /api/vi/admin/valdate " + userLoginDto.getEmail() + userLoginDto.getPassword());
        return userService.validate(userLoginDto);
    }
}
