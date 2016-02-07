package ua.kobzev.theatre.service;

import java.util.List;

import ua.kobzev.theatre.domain.Auditorium;

/**
 * 
 * @author kkobziev
 *
 *
 */

public interface AuditoriumService {

	List<Auditorium> getAuditoriums();

	Auditorium findAuditoriumByName(String auditoriumName);

	int getSeatsNumber(String auditoriumName);

	List<Integer> getVipSeats(String auditoriumName);

}
