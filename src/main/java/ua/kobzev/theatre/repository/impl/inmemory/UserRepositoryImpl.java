package ua.kobzev.theatre.repository.impl.inmemory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

/**
 * 
 * @author kkobziev
 *
 */

// @Repository
public class UserRepositoryImpl implements UserRepository {

	private List<User> usersList;
	private List<Ticket> bookedTicket;

	{
		usersList = new ArrayList<>();
		User user = new User("admin@test.com", "admin", new Date());
		user.setId(usersList.size() + 1);
		usersList.add(user);

		bookedTicket = new ArrayList<>();
		Ticket ticket = new Ticket();
		bookedTicket.add(ticket);
	}

	@Override
	public boolean register(User user) {
		if (user == null)
			return false;

		if (getUserByEmail(user.getEmail()) != null)
			return false;

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
		Optional<User> optionalUser = usersList.stream().filter(user -> ((Integer) user.getId()).equals(id))
				.findFirst();

		if (!optionalUser.isPresent())
			return null;

		return optionalUser.get();
	}

	@Override
	public User getUserByEmail(String email) {

		Optional<User> optionalUser = usersList.stream().filter(user -> user.getEmail().equals(email)).findFirst();

		if (!optionalUser.isPresent())
			return null;

		return optionalUser.get();

	}

	@Override
	public List<User> getUsersByName(String name) {
		List<User> result = new ArrayList<>();

		usersList.stream().filter(user -> user.getName().equals(name)).forEach(user -> result.add(user));

		return result;
	}

	@Override
	public List<Ticket> getBookedTickets(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
