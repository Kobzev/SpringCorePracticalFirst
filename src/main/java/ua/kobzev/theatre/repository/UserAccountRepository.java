package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
public interface UserAccountRepository {
    UserAccount findByUserId(Integer id);
    UserAccount findById(Integer id);
    void createAccountForUser(User user);
    void updateMoney(UserAccount userAccount);
}
