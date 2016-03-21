package ua.kobzev.theatre.repository.impl.mybatis.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ua.kobzev.theatre.domain.User;

import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(@Param("id")Integer id);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User getUserByEmail(@Param("email")String email);

    @Insert("INSERT INTO users (name,email,birthDay) VALUES (#{name},#{email},#{birthDay})")
    int createUser(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    int removeUser(@Param("id")Integer id);

    @Select("SELECT * FROM users WHERE name = #{name}")
    List<User> getUsersByName(@Param("name") String name);

    @Select("SELECT * FROM users")
    List<User> findAllUsers();
}
