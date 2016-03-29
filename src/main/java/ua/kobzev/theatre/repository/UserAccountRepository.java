package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
public interface UserAccountRepository {
    UserAccount findByUser(User user);
    void createAccountForUser(User user);
    boolean updateAccount(UserAccount account);
}
