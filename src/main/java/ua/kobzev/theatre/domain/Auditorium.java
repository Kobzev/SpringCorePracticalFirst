package ua.kobzev.theatre.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
@Entity
@Table(name = "auditoriums")
public class Auditorium {

	@Id
	private String name;

	private Integer numberOfSeats;

	@Column(name = "vipSeats")
	private String strVipSeats;

	@Transient
	private List<Integer> vipSeats;

	public void setVipSeats(String vipSeats) {
		String[] seats = vipSeats.split(",");
		this.vipSeats = new ArrayList<>();

		for (int i = 0; i < seats.length; i++) {
			this.vipSeats.add(Integer.parseInt(seats[i]));
		}
	}

	public List<Integer> getVipSeats(){
		if (vipSeats == null && strVipSeats != null) setVipSeats(strVipSeats);
		return vipSeats;
	}

}
