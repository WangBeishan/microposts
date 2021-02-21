package cf.beishan.microposts.mapper;

import cf.beishan.microposts.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(User user);

    void update(User user);

    User selectById(Long id);

    User selectByEmail(String email);

    void delete(Long id);

    List<User> selectAll();
}
