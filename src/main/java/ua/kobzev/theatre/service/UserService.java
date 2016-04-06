package ua.kobzev.theatre.service;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

import java.util.List;

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

	boolean updateUser(User user);

	User getById(Integer id);

	User getUserByEmail(String email);

	List<User> getUsersByName(String name);

	List<User> findAll();

	List<Ticket> getBookedTickets(User user);

}
