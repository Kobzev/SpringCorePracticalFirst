package ua.kobzev.theatre.service;

import java.util.List;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

/**
 * 
 * @author kkobziev
 *
 *         register, remove, getById, getUserByEmail, getUsersByName,
 *         getBookedTickets
 *
 */

public interface UserService {

	boolean register(User user);

	boolean remove(User user);

	User getById(Integer id);

	User getUserByEmail(String email);

	List<User> getUsersByName(String name);

	List<Ticket> getBookedTickets(User user);

}
