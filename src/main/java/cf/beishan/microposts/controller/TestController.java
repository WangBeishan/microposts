package cf.beishan.microposts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String getIndex(Model model) {
        model.addAttribute("name", "wbs");
        model.addAttribute("age", 999);

        return "test";
    }
}
