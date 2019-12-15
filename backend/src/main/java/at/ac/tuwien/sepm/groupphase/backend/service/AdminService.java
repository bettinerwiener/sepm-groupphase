package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.User;

import java.util.List;

public interface AdminService {

    List<User> findUserByName(String username);
    User findOneByName(String username);
    User updateUser(User user);
}
