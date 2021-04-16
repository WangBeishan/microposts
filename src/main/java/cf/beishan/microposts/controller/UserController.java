package cf.beishan.microposts.controller;

import cf.beishan.microposts.common.Constant;
import cf.beishan.microposts.common.ServiceResultEnum;
import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.service.impl.MicropostServiceImpl;
import cf.beishan.microposts.service.impl.UserServiceImpl;
import cf.beishan.microposts.util.Result;
import cf.beishan.microposts.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MicropostServiceImpl micropostService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if(StringUtils.isEmpty(email)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_EMAIL_NULL.getResult());
        }
        if(StringUtils.isEmpty(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }
        String loginResult = userService.login(email, password, session);
        if(ServiceResultEnum.SUCCESS.getResult().equals(loginResult)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("loginName") String loginName,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password) {

        if(StringUtils.isEmpty(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if(StringUtils.isEmpty(email)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_EMAIL_NULL.getResult());
        }
        if(StringUtils.isEmpty(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }

        String registerResult = userService.register(loginName, email, password);
        if(ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(registerResult);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}
