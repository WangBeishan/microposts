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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FrontController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MicropostServiceImpl micropostService;

    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                        Model model) {

        HttpSession session = request.getSession();

        if(session != null) {
            User user = userService.getUserById((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
            PageHelper.startPage(pageNum, pageSize);
            try {
                List<Micropost> microposts = micropostService.getAllMP();
                PageInfo<Micropost> pageInfo = new PageInfo<>(microposts);
                model.addAttribute("pageInfo", pageInfo);
            } finally {
                PageHelper.clearPage();
            }
            return "index";
        } else {
            System.out.println("email or password error, please try again");
            return "login";
        }


    }
}