package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.annotation.Transactional;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;
import ua.kobzev.theatre.repository.UserAccountRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
@Transactional
public class UserAccountRepositoryImpl implements UserAccountRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Transactional(readOnly = true)
    public UserAccount findByUser(final User user) {
        List<UserAccount> accounts = jdbcOperations.query("SELECT * from accounts where userid = ?",
                new Object[]{user.getId()},
                (ResultSet resultSet, int rowNum) -> {
                    UserAccount userAccount = new UserAccount();
                    userAccount.setId(resultSet.getInt("id"));
                    userAccount.setUser(user);
                    userAccount.setPrepaidMoney(resultSet.getDouble("money"));
                    return userAccount;
                });
        if (accounts.size()>0) return accounts.get(0);
        return null;
    }

    private static final String QUERY_FIND_BY_USER_ID =
            "SELECT accounts.id, " +
                    "accounts.userid, " +
                    "accounts.money, " +
                    "users.birthDay, " +
                    "users.email, " +
                    "users.name " +
                    "FROM accounts, users " +
                    "WHERE accounts.userid=users.id AND userid = ?";

    @Override
    @Transactional(readOnly = true)
    public UserAccount findByUserId(Integer id) {
        List<UserAccount> accounts = jdbcOperations.query(QUERY_FIND_BY_USER_ID,
                new Object[]{id},
                (ResultSet resultSet, int rowNum) -> mapUserAccountFromResultSet(resultSet));
        if (accounts.size()>0) return accounts.get(0);
        return null;
    }

    private static final String QUERY_FIND_BY_ID =
            "SELECT accounts.id, " +
                    "accounts.userid, " +
                    "accounts.money, " +
                    "users.birthDay, " +
                    "users.email, " +
                    "users.name " +
                    "FROM accounts, users " +
                    "WHERE accounts.userid=users.id AND accounts.id = ?";

    @Override
    @Transactional(readOnly = true)
    public UserAccount findById(Integer id) {
        List<UserAccount> accounts = jdbcOperations.query(QUERY_FIND_BY_ID,
                new Object[]{id},
                (ResultSet resultSet, int rowNum) -> mapUserAccountFromResultSet(resultSet));
        if (accounts.size()>0) return accounts.get(0);
        return null;
    }

    @Transactional
    @Override
    public void createAccountForUser(User user) {
        jdbcOperations.update("INSERT into accounts(userid,money) VALUES (?,?)"
                        ,user.getId(), 0.0d);
    }

    @Override
    public void updateMoney(UserAccount userAccount) {
        jdbcOperations
                .update("UPDATE accounts SET money=? WHERE id=?"
                        ,new Object[] {userAccount.getPrepaidMoney(), userAccount.getId()});
        //return result!=0;
    }

    private UserAccount mapUserAccountFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setPassword("");
        user.setName(resultSet.getString("name"));
        user.setBirthDay(LocalDateTime.of(resultSet.getDate("birthDay").toLocalDate(), LocalTime.NOON));
        user.setEmail(resultSet.getString("email"));
        user.setId(resultSet.getInt("userid"));

        UserAccount userAccount = new UserAccount();
        userAccount.setId(resultSet.getInt("id"));
        userAccount.setUser(user);
        userAccount.setPrepaidMoney(resultSet.getDouble("money"));
        return userAccount;

    }
}
