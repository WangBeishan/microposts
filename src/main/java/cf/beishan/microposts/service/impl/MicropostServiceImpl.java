package cf.beishan.microposts.service.impl;

import cf.beishan.microposts.common.ServiceResultEnum;
import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.entity.User;
import cf.beishan.microposts.mapper.MicropostMapper;
import cf.beishan.microposts.mapper.UserMapper;
import cf.beishan.microposts.service.MicropostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MicropostServiceImpl implements MicropostService {

    @Autowired
    MicropostMapper mapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public String addMicropost(HttpSession session, String text) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return ServiceResultEnum.ADD_MP_ERROR.getResult();
        }
        Micropost micropost = new Micropost();
        micropost.setUserId(user.getId());
        micropost.setText(text);
        mapper.insert(micropost);

        return ServiceResultEnum.SUCCESS.getResult();
    }

    @Override
    public Micropost deleteMicropost(Long id) {
        return null;
    }

    @Override
    public List<Micropost> getAllMP() {
        return mapper.selectAll();
    }

    @Override
    public List<Micropost> getByUserId(Long id) {
        return mapper.getByUserId(id);
    }
}
