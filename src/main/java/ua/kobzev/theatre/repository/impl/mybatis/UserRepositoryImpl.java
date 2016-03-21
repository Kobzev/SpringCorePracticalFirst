package ua.kobzev.theatre.repository.impl.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private Mapper mapper;

    @Override
    public boolean register(User user) {
        return mapper.createUser(user)>0;
    }

    @Override
    public boolean remove(User user) {
        return mapper.removeUser(user.getId())>0;
    }

    @Override
    public User getById(Integer id) {
        return mapper.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return mapper.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return mapper.getUsersByName(name);
    }

    @Override
    public List<User> findAll() {
        return mapper.findAllUsers();
    }


}
