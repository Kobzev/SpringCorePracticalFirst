package ua.kobzev.theatre.repository;

import java.util.List;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

/**
 * 
 * @author kkobziev
 *
 */

public interface UserRepository {

	boolean register(User user);

	boolean remove(User user);

	User getById(int id);

	User getUserByEmail(String email);

	List<User> getUsersByName(String name);

	List<Ticket> getBookedTickets(User user);

}
