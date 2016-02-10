package ua.kobzev.theatre.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

/**
 * 
 * @author kkobziev
 *
 */

@Component
public class User {
	private int id;
	private String email;
	private String name;
	private LocalDateTime birthDay;

	public User(String email, String name, LocalDateTime birthDay) {
		this.email = email;
		this.name = name;
		this.birthDay = birthDay;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDateTime birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return "User{" +
				"email='" + email + '\'' +
				", name='" + name + '\'' +
				", birthDay=" + birthDay +
				'}';
	}
}
