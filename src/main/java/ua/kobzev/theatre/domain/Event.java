package ua.kobzev.theatre.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ua.kobzev.theatre.enums.EventRate;

/**
 * 
 * @author kkobziev
 *
 */

@Component
@Scope(value = "prototype")
public class Event {
	private String name;
	private double basePrice;
	private EventRate rate;

	public Event(String name, double basePrise, EventRate rate) {
		this.name = name;
		this.basePrice = basePrise;
		this.rate = rate;
	}

	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrise) {
		this.basePrice = basePrise;
	}

	public EventRate getRate() {
		return rate;
	}

	public void setRate(EventRate rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(basePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (Double.doubleToLongBits(basePrice) != Double.doubleToLongBits(other.basePrice))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rate != other.rate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event{" +
				"name='" + name + '\'' +
				", basePrice=" + basePrice +
				", rate=" + rate +
				'}';
	}
}
