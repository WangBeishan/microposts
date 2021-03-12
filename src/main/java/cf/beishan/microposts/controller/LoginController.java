package cf.beishan.microposts.controller;

import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public void login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) throws IOException {

        User user = getUserByEmail(email);

        if(user.getGmail().equals(email) && user.getPassword().equals(password)) {
            request.getSession().setAttribute("userId", user.getId());
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/login");
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username,
                       @RequestParam("gmail") String gmail,
                       @RequestParam("password") String password) {
        User user = new User();
        user.setName(username);
        user.setGmail(gmail);
        user.setPassword(password);

        userService.addUser(user);

        return "index";
    }

    @GetMapping("/signout")
    public String signout(HttpServletRequest request) {

        User user = userService.getUserById((Long) request.getSession().getAttribute("userIdd"));
        if(user != null) {
            request.getSession().removeAttribute("userId");
        }
        return "index";
    }

//-----------------------------------------------------------------------------
    private User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }
}
