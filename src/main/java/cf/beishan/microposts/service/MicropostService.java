package cf.beishan.microposts.service;

import cf.beishan.microposts.entity.Micropost;

public interface MicropostService {

    void addMicropost(Micropost micropost);

    Micropost deleteMicropost(Long id);
}
