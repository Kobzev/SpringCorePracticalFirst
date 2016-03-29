package ua.kobzev.theatre.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

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

	public Auditorium() {
	}

	@Override
	public String toString() {
		return "Auditorium{" +
				"name='" + name + '\'' +
				", numberOfSeats=" + numberOfSeats +
				", strVipSeats='" + strVipSeats + '\'' +
				", vipSeats=" + vipSeats +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getStrVipSeats() {
		return strVipSeats;
	}

	public void setStrVipSeats(String strVipSeats) {
		this.strVipSeats = strVipSeats;
	}

	public void setVipSeats(List<Integer> vipSeats) {
		this.vipSeats = vipSeats;
	}
}
