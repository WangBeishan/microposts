package cf.beishan.microposts.service;

import cf.beishan.microposts.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    String register(String username, String email, String password);

    String login(String email, String password, HttpSession session);
}
