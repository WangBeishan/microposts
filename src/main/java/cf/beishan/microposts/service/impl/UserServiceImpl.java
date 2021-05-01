package cf.beishan.microposts.service.impl;

import cf.beishan.microposts.common.Constant;
import cf.beishan.microposts.common.ServiceResultEnum;
import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.mapper.UserMapper;
import cf.beishan.microposts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String register(String username, String email, String password) {

        if(userMapper.selectByEmail(email) != null) {
            return ServiceResultEnum.SAME_LOGIN_EMAIL_EXITS.getResult();
        }

        User user = new User();
        user.setName(username);
        user.setGmail(email);
        user.setPassword(password);
        userMapper.insert(user);
        return ServiceResultEnum.SUCCESS.getResult();
    }

    @Override
    public String login(String email, String password, HttpSession session) {

        User user = userMapper.selectByEmail(email);
        if(user != null && session !=null) {
            session.setAttribute(Constant.USER_SESSION_KEY, user);
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return ServiceResultEnum.LOGIN_ERROR.getResult();
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.selectAll();
        if(users != null) {
            return users;
        }
        return null;
    }
}
