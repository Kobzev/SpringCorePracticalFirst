package ua.kobzev.theatre.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
}
