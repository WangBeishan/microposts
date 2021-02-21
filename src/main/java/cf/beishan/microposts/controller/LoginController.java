package cf.beishan.microposts.controller;

import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {

        User user = getUserByEmail(email);

        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();

        if(session != null && user.getPassword().equals(password)) {
            Cookie cookie = new Cookie("userId", user.getId().toString());
            response.addCookie(cookie);
            System.out.println("sessionId : " + session.getId());
            
            return "home";
        } else {
            session.setAttribute("user", user);
            System.out.println("sessionId : " + session.getId());
        }

        return "/error.html";
    }

//-----------------------------------------------------------------------------
    private User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }
}
