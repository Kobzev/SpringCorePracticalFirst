package ua.kobzev.theatre.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.service.AspectService;

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

	@Autowired
	private AspectService aspectService;

	@Pointcut("execution(* ua.kobzev.theatre..EventService.getByName(..))")
	private void accessedByName() {
	}

	@Pointcut("execution(* *.getBasePrice(..))")
	private void priceQueried() {}

	@Pointcut("execution(* ua.kobzev.theatre..BookingService.bookTicket(..))")
	private void bookedTicket() {}

	@AfterReturning(value = "accessedByName()", returning = "retVal")
	public void saveInformationAboutAccessedByName(Object retVal) {
		if (retVal == null) return;

		aspectService.saveAccessByName((Event) retVal);
	}

	@Before("priceQueried()")
	public void saveInformationAboutPriceQueried(JoinPoint joinPoint) {

		aspectService.savePriceQueried((Event) joinPoint.getThis());
	}

	@AfterReturning(value = "bookedTicket()", returning = "retVal")
	public void saveInformationAboutBookedTicket(JoinPoint joinPoint, Object retVal) {
		if (retVal == null) return;
		if (!(boolean) retVal) return;

		Ticket ticket = (Ticket) joinPoint.getArgs()[1];
		aspectService.saveBookedTicket(ticket.getAssignedEvent().getEvent());
	}

}
