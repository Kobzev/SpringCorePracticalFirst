package ua.kobzev.theatre.repository;

import java.util.List;

import ua.kobzev.theatre.domain.User;

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

}
