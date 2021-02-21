package cf.beishan.microposts.mapper;

import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MicropostMapper {

    void insertMp(Micropost micropost);

    void deleteMp(Long id);
}
