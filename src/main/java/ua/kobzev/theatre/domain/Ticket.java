package ua.kobzev.theatre.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author kkobziev
 *
 */

@Component
@Scope(value = "prototype")
public class Ticket {

	private User user;
	private AssignedEvent assignedEvent;
	private int seat;
	private double price;

	public Ticket(AssignedEvent assignedEvent, int seat) {
		this.assignedEvent = assignedEvent;
		this.seat = seat;
	}

	public Ticket(User user, AssignedEvent assignedEvent, int seat) {
		this.user = user;
		this.assignedEvent = assignedEvent;
		this.seat = seat;
	}

	public Ticket() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AssignedEvent getAssignedEvent() {
		return assignedEvent;
	}

	public void setAssignedEvent(AssignedEvent assignedEvent) {
		this.assignedEvent = assignedEvent;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"user=" + user.getName() +
				", seat=" + seat +
				", assignedEvent=" + assignedEvent +
				", price=" + price +
				'}';
	}
}
