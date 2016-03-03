package ua.kobzev.theatre.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "assignedevent")
public class AssignedEvent {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "eventname")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "auditoriumname")
	private Auditorium auditorium;

	private LocalDateTime date;

	public AssignedEvent(Event event, Auditorium auditorium, LocalDateTime date) {
		this.event = event;
		this.auditorium = auditorium;
		this.date = date;
	}
}
