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


    /**
     * Maps a User to a UserDto
     * @param user the User to be mapped
     * @return the UserDto with same values as user
     */
    UserDto userToUserDto(User user);

    /**
     * Maps a List of Users to a List of UserDtos
     * @param users the List of Users to be mapped
     * @return the List of UserDtos with same values as user
     */
    List<UserDto> userToUserDto(List<User> users);

    /**
     * Maps a UserDto to a User
     * @param userDto the UserDto to be mapped
     * @return the User with same values as userDto
     */
    User userDtoToUser(UserDto userDto);

    /**
     * Maps a List of UserDtos to a List of Users
     * @param userDtos the List of UserDtos to be mapped
     * @return the List of Users with same values as userDtos
     */
    List<User> userDtoToUser(List<UserDto> userDtos);





}

