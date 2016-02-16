package ua.kobzev.theatre.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	private String email;
	private String name;
	private LocalDateTime birthDay;

	public User(String email, String name, LocalDateTime birthDay) {
		this.email = email;
		this.name = name;
		this.birthDay = birthDay;
	}
}
