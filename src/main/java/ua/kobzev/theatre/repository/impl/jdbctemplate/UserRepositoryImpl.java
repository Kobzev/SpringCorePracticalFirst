package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public boolean register(User user) {
        int result = jdbcOperations
                .update("INSERT into users(name,email,birthDay,password,remMeToken,lastLogin) VALUES (?,?,?,?,?,?)"
                        ,user.getName(),user.getEmail(),user.getBirthDay()
                        ,user.getPassword(),user.getRemMeToken(),user.getLastLogin());
        return result!=0;
    }

    @Override
    public boolean remove(User user) {
        int result = jdbcOperations.update("DELETE FROM users WHERE id =?", user.getId());
        return result!=0;
    }

    @Override
    public User getById(Integer id) {
        List<User> userList = getUsersByParameter("id", id);
        if (userList.size()==0) return null;
        return userList.get(0);
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> userList = getUsersByParameter("email", email);
        if (userList.size()==0) return null;
        return userList.get(0);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return getUsersByParameter("name", name);
    }

    @Override
    public List<User> findAll() {
        return jdbcOperations.query("select * from users",
                (ResultSet resultSet, int rowNum) -> {
                    return mapUserFromResultSet(resultSet);
                });
    }

    private List<User> getUsersByParameter(String param, Object val){
        return jdbcOperations.query("select * from users where "+param+" = ?",
                new Object[]{val},
                (ResultSet resultSet, int rowNum) -> {
                    return mapUserFromResultSet(resultSet);
                });
    }

    private User mapUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setBirthDay(LocalDateTime.of(resultSet.getDate("birthDay").toLocalDate(), LocalTime.NOON));
        user.setPassword(resultSet.getString("password"));
        user.setRemMeToken(resultSet.getString("remMeToken"));
        if (resultSet.getTimestamp("lastLogin") != null) {
            user.setLastLogin(resultSet.getTimestamp("lastLogin").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return user;
    }


}
