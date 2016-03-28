package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ua.kobzev.theatre.domain.Role;
import ua.kobzev.theatre.enums.UserRoles;
import ua.kobzev.theatre.repository.RoleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kkobziev on 3/28/16.
 */

public class RoleRepositoryImpl implements RoleRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Role> findAllByUserEmail(String email) {
        return jdbcOperations.query("select * from roles",
                (ResultSet resultSet, int rowNum) -> {
                    return mapRoleFromRs(resultSet);
                });
    }

    @Override
    public void save(Role role) {
        jdbcOperations
                .update("INSERT into roles(username,role) VALUES (?,?)"
                        ,role.getUserName(), role.getUserRole());

    }

    private Role mapRoleFromRs(ResultSet rs) throws SQLException {
        return new Role(rs.getLong("id"), rs.getString("username"), UserRoles.valueOf(rs.getString("role")));
    }

}
