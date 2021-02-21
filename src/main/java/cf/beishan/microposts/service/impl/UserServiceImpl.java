package cf.beishan.microposts.service.impl;

import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.mapper.UserMapper;
import cf.beishan.microposts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.delete(id);
    }
}
