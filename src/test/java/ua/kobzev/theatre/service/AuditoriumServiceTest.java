package ua.kobzev.theatre.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({ "file:src/test/resources/test-context.xml" })
public class AuditoriumServiceTest {

	@Autowired
	private AuditoriumService auditoriumService;

	@Test
	public void shouldReturnTwoAuditoriums() {
		assertEquals(2, auditoriumService.getAuditoriums().size());
	}

}
