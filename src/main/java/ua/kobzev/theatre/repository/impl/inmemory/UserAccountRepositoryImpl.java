package ua.kobzev.theatre.repository.impl.inmemory;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;
import ua.kobzev.theatre.repository.UserAccountRepository;

/**
 * Created by kkobziev on 4/6/16.
 */
public class UserAccountRepositoryImpl implements UserAccountRepository{
    @Override
    public UserAccount findByUserId(Integer id) {
        return null;
    }

    @Override
    public UserAccount findById(Integer id) {
        return null;
    }

    @Override
    public void createAccountForUser(User user) {

    }

    @Override
    public void updateMoney(UserAccount userAccount) {

    }
}
