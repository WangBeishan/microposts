package cf.beishan.microposts.controller;

import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.service.impl.MicropostServiceImpl;
import cf.beishan.microposts.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MicropostServiceImpl micropostService;

    @GetMapping("/users/{id}")
    @ResponseBody
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public String getAllUser(Model model,
                             HttpServletResponse response,
                             @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize
                             ) throws IOException {

        PageHelper.startPage(pageNum, pageSize);
        try {
            List<User> users = userService.getAllUser();
            PageInfo<User> pageInfo = new PageInfo<>(users);
            model.addAttribute("usersPageInfo", pageInfo);
        } finally {
            PageHelper.clearPage();
        }
        return "users";
    }

    @PutMapping("/users/{id}/edit")
    public void updateUserInfoById(@PathVariable Long id,
                               @RequestParam("username") String username,
                               @RequestParam("gmail") String gmail,
                               @RequestParam("password") String password) {
        User user = getById(id);

        user.setId(user.getId());
        user.setName(username);
        user.setGmail(gmail);
        user.setPassword(password);

        userService.updateUser(user);
    }

    @DeleteMapping("admin/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
