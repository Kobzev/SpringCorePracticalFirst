package ua.kobzev.theatre.domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AssignedEvent {

	@Id
	@GeneratedValue
	private Integer id;

	private Event event;
	private Auditorium auditorium;
	private LocalDateTime date;

	public AssignedEvent(Event event, Auditorium auditorium, LocalDateTime date) {
		this.event = event;
		this.auditorium = auditorium;
		this.date = date;
	}
}
