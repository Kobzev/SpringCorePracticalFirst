package ua.kobzev.theatre.domain;

import ua.kobzev.theatre.enums.EventRate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author kkobziev
 *
 */

@Entity
@Table(name = "events")
@XmlRootElement
@XmlType(namespace = "http://ua.kobziev.movie/event")
public class Event {

	@Id
	private String name;

	private Double basePrice;

	@Enumerated(EnumType.STRING)
	private EventRate rate;

	public Event() {
	}

	public Event(String name, Double basePrice, EventRate rate) {
		this.name = name;
		this.basePrice = basePrice;
		this.rate = rate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Event event = (Event) o;

		if (name != null ? !name.equals(event.name) : event.name != null) return false;
		if (basePrice != null ? !basePrice.equals(event.basePrice) : event.basePrice != null) return false;
		return rate == event.rate;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
		result = 31 * result + (rate != null ? rate.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Event{" +
				"name='" + name + '\'' +
				", basePrice=" + basePrice +
				", rate=" + rate +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public EventRate getRate() {
		return rate;
	}

	public void setRate(EventRate rate) {
		this.rate = rate;
	}
}
