package ua.kobzev.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;
import ua.kobzev.theatre.repository.UserAccountRepository;
import ua.kobzev.theatre.repository.UserRepository;
import ua.kobzev.theatre.service.UserAccountService;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAccount findByUserId(Integer id) {
        UserAccount userAccount = userAccountRepository.findByUserId(id);
        if (userAccount == null) {
            userAccountRepository.createAccountForUser(userRepository.getById(id));
            userAccount = userAccountRepository.findByUserId(id);
        }
        return userAccount;
    }

    @Override
    public UserAccount findById(Integer id) {
        return userAccountRepository.findById(id);
    }

    @Override
    public void addMoney(Integer id, Double money) {
        UserAccount userAccount = userAccountRepository.findById(id);
        userAccount.setPrepaidMoney(userAccount.getPrepaidMoney() + money);
        userAccountRepository.updateMoney(userAccount);
    }

    @Override
    public boolean withdrawMoney(User user, Double money) {
        UserAccount userAccount = findByUserId(user.getId());
        userAccount.setPrepaidMoney(userAccount.getPrepaidMoney() - money);
        if (userAccount.getPrepaidMoney() >= 0.0d) {
            userAccountRepository.updateMoney(userAccount);
            return true;
        }
        return false;
    }
}
