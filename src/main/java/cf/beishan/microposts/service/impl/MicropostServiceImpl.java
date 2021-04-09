package cf.beishan.microposts.service.impl;

import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.mapper.MicropostMapper;
import cf.beishan.microposts.service.MicropostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicropostServiceImpl implements MicropostService {

    @Autowired
    MicropostMapper mapper;

    @Override
    public void addMicropost(Micropost micropost) {
        mapper.insert(micropost);
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
