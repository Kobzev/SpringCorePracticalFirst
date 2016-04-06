package ua.kobzev.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.TicketRepository;
import ua.kobzev.theatre.repository.UserRepository;
import ua.kobzev.theatre.service.UserService;

import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

@Service
@Transactional
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

	@Override
	public boolean updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Transactional(readOnly = true)
	public User getById(Integer id) {
		return userRepository.getById(id);
	}

	@Transactional(readOnly = true)
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	@Transactional(readOnly = true)
	public List<User> getUsersByName(String name) {
		return userRepository.getUsersByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Ticket> getBookedTickets(User user) {
		return ticketRepository.findAllByUser(user);
	}

//	@Override
//	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//		User user = userRepository.getUserByEmail(userEmail);
//		if (user == null) {
//			throw new UsernameNotFoundException("Username not found");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
//				true, true, true, true, getGrantedAuthorities(user));
//	}
//
//	private List<GrantedAuthority> getGrantedAuthorities(User user){
//		List<GrantedAuthority> authorities = new ArrayList<>();
//
//		String[] roles = user.getRoles().split(", ");
//
//		Arrays.asList(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role)));
//		return authorities;
//	}
}
