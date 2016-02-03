package ua.kobzev.theatre.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

/**
 * 
 * @author kkobziev
 *
 */

@Repository
public class InMemoryUserRepository implements UserRepository {

	private List<User> usersList;
	private List<Ticket> bookedTicket;

	{
		usersList = new ArrayList<>();
		User user = new User("admin@test.com", "admin");
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

		user.setId(usersList.size() + 1);
		usersList.add(user);

		return true;
	}

	@Override
	public boolean remove(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getBookedTickets(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
