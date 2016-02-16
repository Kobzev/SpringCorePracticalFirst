package ua.kobzev.theatre.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AssignedEvent {
	private Event event;
	private Auditorium auditorium;
	private LocalDateTime date;
	private Integer id;

	public AssignedEvent(Event event, Auditorium auditorium, LocalDateTime date) {
		this.event = event;
		this.auditorium = auditorium;
		this.date = date;
	}
}
