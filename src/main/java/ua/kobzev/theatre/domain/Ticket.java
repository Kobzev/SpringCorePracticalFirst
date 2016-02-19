package ua.kobzev.theatre.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 
 * @author kkobziev
 *
 */

@Component
@Scope(value = "prototype")
@Getter
@Setter
@NoArgsConstructor
@ToString
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
}
