package cf.beishan.microposts.controller;

import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.service.MicropostService;
import cf.beishan.microposts.service.impl.MicropostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicropostsController {

    @Autowired
    MicropostServiceImpl micropostService;

    @PostMapping("/addMp")
    public void addMicropost(@RequestParam("text") String text) {
        Micropost micropost = new Micropost();

        micropost.setText(text);

        micropostService.addMicropost(micropost);
    }

    @DeleteMapping("/deleteMp")
    public void deleteMicropost(@RequestParam long id) {
        micropostService.deleteMicropost(id);
    }
}
