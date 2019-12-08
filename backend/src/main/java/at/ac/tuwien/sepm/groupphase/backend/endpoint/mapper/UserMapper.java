package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDto entityToDto(User user) {
        return new UserDto( user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getLocked(), user.getIsEmployee());
    }

    public User dtoToEntity(UserDto userDto) {

        return new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword());

    }
    public List<UserDto> entityListToDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        userList.forEach(User -> userDtoList.add(entityToDto(User)));
        return userDtoList;
    }
}

