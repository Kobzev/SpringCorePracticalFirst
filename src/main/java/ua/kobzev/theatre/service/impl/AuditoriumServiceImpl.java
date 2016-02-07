package ua.kobzev.theatre.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.service.AuditoriumService;

/**
 * 
 * @author kkobziev
 *
 */

public class AuditoriumServiceImpl implements AuditoriumService {

	private List<Auditorium> auditoriumList;

	public void setAuditoriumList(List<Auditorium> auditoriumList) {
		this.auditoriumList = auditoriumList;
	}

	@Override
	public List<Auditorium> getAuditoriums() {
		return new ArrayList<Auditorium>(auditoriumList);
	}

	@Override
	public Auditorium findAuditoriumByName(String auditoriumName) {
		Optional<Auditorium> audit = auditoriumList.stream().filter(p -> auditoriumName.equals(p.getName()))
				.findFirst();

		if (audit.isPresent())
			return audit.get();

		return null;
	}

	@Override
	public int getSeatsNumber(String auditoriumName) {
		Auditorium auditorium = findAuditoriumByName(auditoriumName);

		if (auditorium == null)
			return 0;

		return auditorium.getNumberOfSeats();
	}

	@Override
	public List<Integer> getVipSeats(String auditoriumName) {
		Auditorium auditorium = findAuditoriumByName(auditoriumName);

		if (auditorium == null)
			return null;

		return auditorium.getVipSeats();
	}

}
