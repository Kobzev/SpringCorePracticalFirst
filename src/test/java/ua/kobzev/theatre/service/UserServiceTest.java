package ua.kobzev.theatre.service;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;
import ua.kobzev.theatre.service.impl.UserServiceImpl;

/**
 * 
 * @author kkobziev
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	private static final int ID = 1000;
	private static final String EMAIL = "mail@mail.com";
	private static final String NAME = "user";

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserRepository userRepository;

	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User(EMAIL, NAME, new Date());
		// when(userRepository.register(anyObject())).thenReturn(true);
	}

	@Test
	public void testRegister() {
		userService.register(user);
		verify(userRepository, times(1)).register(anyObject());
	}

	@Test
	public void testRemove() {
		userService.remove(user);
		verify(userRepository, times(1)).remove(anyObject());
	}

	@Test
	public void testGetById() {
		userService.getById(ID);
		verify(userRepository, times(1)).getById(anyInt());
	}

	@Test
	public void testGetUserByEmail() {
		userService.getUserByEmail(EMAIL);
		verify(userRepository, times(1)).getUserByEmail(anyString());
	}

	@Test
	public void testGetUsersByName() {
		userService.getUsersByName(NAME);
		verify(userRepository, times(1)).getUsersByName(anyString());
	}

	@Test
	public void testGetBookedTickets() {
		userService.getBookedTickets(user);
		verify(userRepository, times(1)).getBookedTickets(anyObject());
	}

}
