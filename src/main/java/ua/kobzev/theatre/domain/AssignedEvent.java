package ua.kobzev.theatre.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

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

	public AssignedEvent() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AssignedEvent that = (AssignedEvent) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (event != null ? !event.equals(that.event) : that.event != null) return false;
		if (auditorium != null ? !auditorium.equals(that.auditorium) : that.auditorium != null) return false;
		return date != null ? date.equals(that.date) : that.date == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (event != null ? event.hashCode() : 0);
		result = 31 * result + (auditorium != null ? auditorium.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "AssignedEvent{" +
				"id=" + id +
				", event=" + event +
				", auditorium=" + auditorium +
				", date=" + date +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
