package ua.kobzev.theatre.domain;

/**
 * 
 * @author kkobziev
 *
 */

public class Ticket {

	private User user;
	private AssignedEvent assignedEvent;
	private int seat;

	public Ticket(AssignedEvent assignedEvent, int seat) {
		this.assignedEvent = assignedEvent;
		this.seat = seat;
	}

	public Ticket(User user, AssignedEvent assignedEvent, int seat) {
		this.user = user;
		this.assignedEvent = assignedEvent;
		this.seat = seat;
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

}
