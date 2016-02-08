package ua.kobzev.theatre.repository;

import java.util.List;

import ua.kobzev.theatre.domain.Auditorium;

public interface AuditoriumRepository {

	List<Auditorium> getAuditoriums();

	Auditorium findAuditoriumByName(String auditoriumName);

	int getSeatsNumber(String auditoriumName);

	List<Integer> getVipSeats(String auditoriumName);

}
