package ua.kobzev.theatre.repository.impl.inmemory;

import java.util.ArrayList;
import java.util.List;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;

//@Repository
public class AuditoriumRepositoryImpl implements AuditoriumRepository {

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
		return auditoriumList.stream().filter(p -> auditoriumName.equals(p.getName())).findFirst().orElse(null);
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
