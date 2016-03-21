package ua.kobzev.theatre.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 
 * @author kkobziev
 *
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
@EqualsAndHashCode
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	private String email;
	private String name;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime birthDay;

	public User(String email, String name, LocalDateTime birthDay) {
		this.email = email;
		this.name = name;
		this.birthDay = birthDay;
	}
}
