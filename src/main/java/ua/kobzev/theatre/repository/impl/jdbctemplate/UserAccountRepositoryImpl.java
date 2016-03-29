package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;
import ua.kobzev.theatre.repository.UserAccountRepository;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
public class UserAccountRepositoryImpl implements UserAccountRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
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

    @Override
    public void createAccountForUser(User user) {
        jdbcOperations.update("INSERT into accounts(userid,money) VALUES (?,?)"
                        ,user.getId(), 0.0d);
    }

    @Override
    public boolean updateAccount(UserAccount account) {
        int result = jdbcOperations
                .update("UPDATE accounts SET money=? WHERE id=?"
                        ,new Object[] {account.getPrepaidMoney(), account.getId()});
        return result!=0;
    }
}
