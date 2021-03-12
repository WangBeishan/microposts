package cf.beishan.microposts.mapper;

import cf.beishan.microposts.entity.Micropost;
import cf.beishan.microposts.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MicropostMapper {

    void insert(Micropost micropost);

    void deleteMp(Long id);

    List<Micropost> selectAll();
}
