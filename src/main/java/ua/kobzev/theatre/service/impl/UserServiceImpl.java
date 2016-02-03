package ua.kobzev.theatre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
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

	public boolean register(User user) {
		return userRepository.register(user);
	}

	public boolean remove(User user) {
		return userRepository.remove(user);
	}

	public User getById(int id) {
		return userRepository.getById(id);
	}

	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	public List<User> getUsersByName(String name) {
		return userRepository.getUsersByName(name);
	}

	public List<Ticket> getBookedTickets(User user) {
		return userRepository.getBookedTickets(user);
	}

}
