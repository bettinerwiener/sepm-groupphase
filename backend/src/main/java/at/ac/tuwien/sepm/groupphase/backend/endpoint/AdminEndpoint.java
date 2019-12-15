package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.UserMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
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
    public List<UserDto> getUsers(@RequestParam("name") String username) {
        LOGGER.info("GET /api/v1/admin/seach?name=" + username);

        return userMapper.userToUserDto(adminService.findUserByName(username));

    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/user")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@RequestParam("name") String username) {
        LOGGER.info("GET /api/v1/admin/user?name=" + username);

        return userMapper.userToUserDto(adminService.findOneByName(username));
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/edit")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        LOGGER.info("PUT /api/v1/admin/edit username: " + userDto.getEmail());

        return userMapper.userToUserDto(adminService.updateUser(userMapper.userDtoToUser(userDto)));
    }
}
