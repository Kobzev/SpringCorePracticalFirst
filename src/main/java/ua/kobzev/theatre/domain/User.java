package ua.kobzev.theatre.domain;

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

	public User(String email, String name) {
		this.email = email;
		this.name = name;
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
}
