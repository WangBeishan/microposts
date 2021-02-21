package cf.beishan.microposts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {

    @RequestMapping("/session")
    public Map<String, Object> getSession(HttpServletRequest request) {
        request.getSession().setAttribute("userName", "wbs");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        return map;
    }

    @RequestMapping("/get")
    public String get(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        return userName;
    }
}
