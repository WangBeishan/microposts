package cf.beishan.microposts.service;

import cf.beishan.microposts.entity.Micropost;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MicropostService {

    String addMicropost(HttpSession session, String text);

    Micropost deleteMicropost(Long id);

    List<Micropost> getAllMP();

    List<Micropost> getByUserId(Long id);
}
