package cf.beishan.microposts.controller;

import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/signup")
    public void signup(@RequestParam("username") String username,
                       @RequestParam("gmail") String gmail,
                       @RequestParam("password") String password) {
        User user = new User();
        user.setName(username);
        user.setGmail(gmail);
        user.setPassword(password);

        userService.addUser(user);
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUser();
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
