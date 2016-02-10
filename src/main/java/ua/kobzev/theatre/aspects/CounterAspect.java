package ua.kobzev.theatre.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author kkobziev
 * 
 * 
 *         count how many times each event was accessed by name, how many times
 *         its prices were queried, how many times its tickets were booked.
 *         Store counters in map for now (later could be replaced by DB dao)
 */

@Component
@Aspect
public class CounterAspect {

	public static int staticVar = 0;

	@Pointcut("execution(* ua.kobzev.theatre..UserService.getById(..))")
	private void accessedByName() {
	}

	@Before("accessedByName()")
	public void testMethod() {
		staticVar++;
		System.out.println("Aspect");
	}

}
