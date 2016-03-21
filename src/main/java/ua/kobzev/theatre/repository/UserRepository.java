package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.User;

import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

public interface UserRepository {

	boolean register(User user);

	boolean remove(User user);

	User getById(Integer id);

	User getUserByEmail(String email);

	List<User> getUsersByName(String name);

	List<User> findAll();

}
