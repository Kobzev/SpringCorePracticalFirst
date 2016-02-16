package ua.kobzev.theatre.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
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
public class Auditorium {
	private String name;
	private Integer numberOfSeats;
	private List<Integer> vipSeats;

	public void setVipSeats(String vipSeats) {
		String[] seats = vipSeats.split(",");
		this.vipSeats = new ArrayList<>();

		for (int i = 0; i < seats.length; i++) {
			this.vipSeats.add(Integer.parseInt(seats[i]));
		}
	}
}
