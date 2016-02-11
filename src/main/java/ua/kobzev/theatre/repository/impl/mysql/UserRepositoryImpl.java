package ua.kobzev.theatre.repository.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public boolean register(User user) {
        int result = jdbcOperations.update("INSERT into users(name,email,birthDay) VALUES (?,?,?)",user.getName(),user.getEmail(),user.getBirthDay());
        return result==0 ? false : true;
    }

    @Override
    public boolean remove(User user) {
        int result = jdbcOperations.update("DELETE FROM users WHERE id =?", user.getId());
        return result==0 ? false : true;
    }

    @Override
    public User getById(int id) {
        return jdbcOperations.queryForObject("select * from users where id = ?",
                new Object[]{id},
                (ResultSet resultSet, int rowNum) -> {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setBirthDay(LocalDateTime.of(resultSet.getDate("birthDay").toLocalDate(), LocalTime.NOON));

                    return user;
                });
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcOperations.query("select * from users where email = ?",
                new Object[]{email},
                (ResultSet resultSet) -> {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setBirthDay(LocalDateTime.of(resultSet.getDate("birthDay").toLocalDate(), LocalTime.NOON));

                    return user;
        });
    }

    @Override
    public List<User> getUsersByName(String name) {
        return jdbcOperations.query("select * from users where name = ?",
                new Object[]{name},
                (ResultSet resultSet, int rowNum) -> {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setBirthDay(LocalDateTime.of(resultSet.getDate("birthDay").toLocalDate(), LocalTime.NOON));

                    return user;
                });
    }
}
