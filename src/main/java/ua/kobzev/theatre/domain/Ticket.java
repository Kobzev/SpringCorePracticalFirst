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
	private Integer seat;
	private Double price;
	private Integer id;

	public Ticket(AssignedEvent assignedEvent, Integer seat) {
		this.assignedEvent = assignedEvent;
		this.seat = seat;
	}

	public Ticket(User user, AssignedEvent assignedEvent, Integer seat) {
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

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
