package ua.kobzev.theatre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.TicketRepository;
import ua.kobzev.theatre.repository.UserRepository;
import ua.kobzev.theatre.service.UserService;

/**
 * 
 * @author kkobziev
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public boolean register(User user) {
		if (user == null)
			return false;

		if (getUserByEmail(user.getEmail()) != null)
			return false;

		return userRepository.register(user);
	}

	public boolean remove(User user) {
		return userRepository.remove(user);
	}

	public User getById(Integer id) {
		return userRepository.getById(id);
	}

	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	public List<User> getUsersByName(String name) {
		return userRepository.getUsersByName(name);
	}

	public List<Ticket> getBookedTickets(User user) {
		return ticketRepository.findAllByUser(user);
	}

}
