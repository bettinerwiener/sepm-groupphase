package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserLoginDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.UserMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserMapper userMapper;
    private final AdminService adminService;


    @Autowired
    public AdminEndpoint(UserMapper userMapper, AdminService adminService) {
        this.userMapper = userMapper;
        this.adminService = adminService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all users", authorizations = {@Authorization(value = "apiKey")})
    public List<UserDto> getUsers(@RequestParam("name") String username) {
        LOGGER.info("GET /api/v1/admin/seach?name=" + username);

        return userMapper.userToUserDto(adminService.findUserByName(username));

    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/user")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user with a certain username", authorizations = {@Authorization(value = "apiKey")})
    public UserDto getUser(@RequestParam("name") String username) {
        LOGGER.info("GET /api/v1/admin/user?name=" + username);

        return userMapper.userToUserDto(adminService.findOneByName(username));
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/edit")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update user", authorizations = {@Authorization(value = "apiKey")})
    public UserDto updateUser(@RequestBody UserDto userDto) {
        LOGGER.info("PUT /api/v1/admin/edit username: " + userDto.getEmail());

        return userMapper.userToUserDto(adminService.updateUser(userMapper.userDtoToUser(userDto)));
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete User", authorizations = {@Authorization(value = "apiKey")})
    public boolean deleteUser(@RequestBody UserDto userDto) {
        LOGGER.info("DELETE /api/vi/admin/delete username: " + userDto.getEmail());
        return adminService.deleteUser(userMapper.userDtoToUser(userDto));
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/validate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Validate Admin", authorizations = {@Authorization(value = "apiKey")})
    public boolean validate(@RequestBody UserLoginDto userLoginDto) {
        LOGGER.info("GET /api/vi/admin/valdate");
        return adminService.validate(userLoginDto);
    }
}
