package ua.kobzev.theatre.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import ua.kobzev.theatre.enums.UserRoles;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
	//Security
	private String password;
	@Transient
	private List<Role> roles;
	private String remMeToken;
	private LocalDateTime lastLogin;

	public User(String email, String name, LocalDateTime birthDay) {
		this.email = email;
		this.name = name;
		this.birthDay = birthDay;
		this.roles = Arrays.asList(new Role[] {new Role(name, UserRoles.RESGISTERED_USER)});
	}
}
