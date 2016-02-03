package ua.kobzev.theatre.repository.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.UserRepository;

/**
 * 
 * @author kkobziev
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = { "classpath:context.xml" })
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("smth@smth.com", "user");
		// when(userRepository.register(anyObject())).thenReturn(true);
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

}
