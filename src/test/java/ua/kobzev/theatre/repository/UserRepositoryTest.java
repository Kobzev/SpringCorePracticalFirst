package ua.kobzev.theatre.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.kobzev.theatre.domain.User;

/**
 * 
 * @author kkobziev
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({ "file:src/test/resources/test-context.xml" })
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	private static final String NAME = "user";
	private static final String EMAIL = "smth@smth.com";

	@Before
	public void setUp() {
		user = new User(EMAIL, NAME, new Date());
	}

	@Test
	public void shouldReturnTrueWhenRegisterUnexistingUser() {
		assertEquals(true, userRepository.register(user));
	}

	@Test
	public void shouldReturnFalseWhenRegisterExistingUser() {
		userRepository.register(user);
		assertEquals(false, userRepository.register(user));
	}

	@Test
	public void shouldReturnFalseWhenRegisterNullUser() {
		assertEquals(false, userRepository.register(null));
	}

	@Test
	public void shouldReturnFalseWhenRemoveNullUser() {
		assertEquals(false, userRepository.remove(null));

	}

	@Test
	public void shouldReturnFalseWhenRemoveUnexistinglUser() {
		assertEquals(false, userRepository.remove(user));

	}

	@Test
	public void shouldReturnTrueWhenRemoveExistingUser() {
		userRepository.register(user);
		assertEquals(true, userRepository.remove(user));

	}

	@Test
	public void shouldReturnNullWhenGivenUnexistingId() {
		assertNull(userRepository.getById(0));
	}

	@Test
	public void shouldReturnUserWhenGivenExistingId() {
		userRepository.register(user);
		assertEquals(user, userRepository.getById(user.getId()));
	}

	@Test
	public void shoulReturnNullWhenGivenUnexistingEmail() {
		assertNull(userRepository.getUserByEmail(""));
	}

	@Test
	public void shouldReturnUserWhenGivenExistingEmail() {
		userRepository.register(user);
		assertEquals(user, userRepository.getUserByEmail(EMAIL));
	}

	@Test
	public void shouldReturnEmptyListWhenGivenUnxistingName() {
		assertEquals(0, userRepository.getUsersByName("unExistingName").size());

	}

	@Test
	public void shouldReturnNotEmptyListWhenGivenExistingName() {
		userRepository.register(user);
		assertEquals(1, userRepository.getUsersByName(NAME).size());
	}

	@Test
	public void getBookedTickets() {
		// TODO Auto-generated method stub
		// List<Ticket> getBookedTickets(User user);
		assertNull(null);
	}

}
