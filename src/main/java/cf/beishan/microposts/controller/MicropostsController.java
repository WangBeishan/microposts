package cf.beishan.microposts.controller;

import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.service.MicropostService;
import cf.beishan.microposts.service.impl.MicropostServiceImpl;
import cf.beishan.microposts.service.impl.UserServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class MicropostsController {

    @Autowired
    MicropostServiceImpl micropostService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/addMP")
    @ResponseBody
    public void addMicropost(@RequestParam("micropost_content") String text,
                             HttpServletResponse response,
                             HttpServletRequest request) throws IOException {
        Micropost micropost = new Micropost();

        micropost.setText(text);
//        micropost.setText("Hello World!");
        micropost.setUserId((Long) request.getSession().getAttribute("userId"));

        micropostService.addMicropost(micropost);

        response.sendRedirect("index");
    }

    @DeleteMapping("/deleteMp")
    @ResponseBody
    public void deleteMicropost(@RequestParam long id) {
        micropostService.deleteMicropost(id);
    }

    @GetMapping("/microposts")
    @ResponseBody
    public PageInfo<Micropost> getAllPM(Model model,
                                        @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                        @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
                                        HttpServletResponse response) {

        if(pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if(pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Micropost> microposts = micropostService.getAllMP();
            PageInfo<Micropost> pageInfo = new PageInfo<>(microposts);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("microposts", microposts);
            return pageInfo;
        } finally {
            PageHelper.clearPage();
        }
    }


}
