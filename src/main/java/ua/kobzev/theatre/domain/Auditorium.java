package ua.kobzev.theatre.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

@Component
@Scope(value = "prototype")
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

	@Override
	public String toString() {
		return "Auditorium{" +
				"name='" + name + '\'' +
				", numberOfSeats=" + numberOfSeats +
				", vipSeats=" + vipSeats +
				'}';
	}
}
