package ua.kobzev.theatre.domain;

import javax.persistence.*;

/**
 * 
 * @author kkobziev
 *
 */

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "assignedeventid")
	private AssignedEvent assignedEvent;
	private Integer seat;
	private Double price;

	public Ticket(AssignedEvent assignedEvent, Integer seat) {
		this.assignedEvent = assignedEvent;
		this.seat = seat;
	}

	public Ticket(User user, AssignedEvent assignedEvent, Integer seat) {
		this.user = user;
		this.assignedEvent = assignedEvent;
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"id=" + id +
				", user=" + user +
				", assignedEvent=" + assignedEvent +
				", seat=" + seat +
				", price=" + price +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Ticket() {

	}
}
