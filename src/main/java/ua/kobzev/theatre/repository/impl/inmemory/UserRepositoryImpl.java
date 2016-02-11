package ua.kobzev.theatre.repository.impl.inmemory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

/**
 * 
 * @author kkobziev
 *
 */

//@Repository
public class UserRepositoryImpl implements UserRepository {

	private List<User> usersList;

	{
		usersList = new ArrayList<>();
		User user = new User("admin@test.com", "admin", LocalDateTime.now());
		user.setId(usersList.size() + 1);
		usersList.add(user);
	}

	@Override
	public boolean register(User user) {
		user.setId(usersList.size() + 1);
		usersList.add(user);

		return true;
	}

	@Override
	public boolean remove(User user) {
		return usersList.remove(user);
	}

	@Override
	public User getById(int id) {
		return usersList.stream().filter(user -> ((Integer) user.getId()).equals(id)).findFirst().orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {

		return usersList.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);

	}

	@Override
	public List<User> getUsersByName(String name) {
		return usersList.stream().filter(user -> user.getName().equals(name)).collect(Collectors.toList());
	}

}
