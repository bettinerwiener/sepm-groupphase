package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {

    @Named("user")
    UserDto userToUserDto(User user);

    @IterableMapping(qualifiedByName = "user")
    List<UserDto> userToUserDto(List<User> users);

    User userDtoToUser(UserDto userDto);

    List<User> userDtoToUser(List<UserDto> userDtos);

}

