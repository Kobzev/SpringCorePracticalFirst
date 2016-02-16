package ua.kobzev.theatre.repository.impl.mybatis;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean register(User user) {
        // TODO
        return false;
    }

    @Override
    public boolean remove(User user) {
        // TODO
        return false;
    }

    @Override
    public User getById(Integer id) {
        // TODO
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        // TODO
        return null;
    }

    @Override
    public List<User> getUsersByName(String name) {
        // TODO
        return null;
    }
}
