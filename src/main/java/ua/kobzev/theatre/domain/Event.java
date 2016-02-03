package ua.kobzev.theatre.domain;

import org.springframework.stereotype.Component;

/**
 * 
 * @author kkobziev
 *
 */

@Component
public class Event {
	private int id;
	private String name;

	public Event() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
