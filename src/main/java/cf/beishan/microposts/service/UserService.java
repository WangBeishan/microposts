package cf.beishan.microposts.service;

import cf.beishan.microposts.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

    void deleteUser(Long id);

    List<User> getAllUser();
}
