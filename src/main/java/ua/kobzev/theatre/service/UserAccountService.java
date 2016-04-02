package ua.kobzev.theatre.service;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.domain.UserAccount;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
public interface UserAccountService {
    UserAccount findByUserId(Integer id);
    UserAccount findById(Integer id);
    void addMoney(Integer id, Double money);
    boolean withdrawMoney(User user, Double money);
}
