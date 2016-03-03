package ua.kobzev.theatre.domain;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.kobzev.theatre.enums.EventRate;

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
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "events")
public class Event {

	@Id
	private String name;

	private Double basePrice;

	@Enumerated(EnumType.STRING)
	private EventRate rate;
}
