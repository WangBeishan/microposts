package cf.beishan.microposts.service;

import cf.beishan.microposts.entity.Micropost;

import java.util.List;

public interface MicropostService {

    void addMicropost(Micropost micropost);

    Micropost deleteMicropost(Long id);

    List<Micropost> getAllMP();

    List<Micropost> getByUserId(Long id);
}
