package cf.beishan.microposts.controller;

import cf.beishan.microposts.controller.vo.MPUserVO;
import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.mapper.UserMapper;
import cf.beishan.microposts.service.MicropostService;
import cf.beishan.microposts.service.UserService;
import cf.beishan.microposts.util.Result;
import cf.beishan.microposts.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MicropostController {

    @Autowired
    MicropostService micropostService;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/addMP")
    @ResponseBody
    public Result addMicropost(HttpSession session, @RequestParam("content") String text) {

        if(session == null) {
            System.out.println("error");
            return ResultGenerator.genFailResult("未登录");
        }
        if(text.isEmpty()) {
           return ResultGenerator.genFailResult("please enter valid content");
        }
        micropostService.addMicropost(session, text);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/getAllMP")
    @ResponseBody
    public List<MPUserVO> getAllMP(Model model) {
        List<Micropost> microposts = micropostService.getAllMP();
        List<MPUserVO> views = new ArrayList<>();
        Long userId;
        String userName;

        for(Micropost micropost : microposts) {
            userId = micropost.getUserId();
            userName = userMapper.selectById(userId).getName();
            MPUserVO view = new MPUserVO();
            view.setUserId(userId);
            view.setUserName(userName);
            view.setText(micropost.getText());
            view.setPostTime(micropost.getCrtTime());

            views.add(view);
        }
        return views;
    }
}
