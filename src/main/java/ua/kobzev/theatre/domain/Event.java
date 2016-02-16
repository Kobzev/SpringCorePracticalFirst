package ua.kobzev.theatre.domain;

import lombok.*;
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
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Event {
	private String name;
	private Double basePrice;
	private EventRate rate;

	public Event(String name, Double basePrise, EventRate rate) {
		this.name = name;
		this.basePrice = basePrise;
		this.rate = rate;
	}
}
