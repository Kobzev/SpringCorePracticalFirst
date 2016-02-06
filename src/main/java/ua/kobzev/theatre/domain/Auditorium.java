package ua.kobzev.theatre.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

public class Auditorium {
	private String name;
	private int numberOfSeats;
	private List<Integer> vipSeats;

	public Auditorium() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public List<Integer> getVipSeats() {
		return vipSeats;
	}

	public void setVipSeats(String vipSeats) {
		String[] seats = vipSeats.split(",");
		this.vipSeats = new ArrayList<>();

		for (int i = 0; i < seats.length; i++) {
			this.vipSeats.add(Integer.parseInt(seats[i]));
		}
	}

}
