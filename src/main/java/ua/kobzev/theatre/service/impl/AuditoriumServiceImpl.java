package ua.kobzev.theatre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;
import ua.kobzev.theatre.service.AuditoriumService;

/**
 * 
 * @author kkobziev
 *
 */

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	private AuditoriumRepository auditoriumRepository;

	@Override
	public List<Auditorium> getAuditoriums() {
		return auditoriumRepository.getAuditoriums();
	}

	@Override
	public Auditorium findAuditoriumByName(String auditoriumName) {
		return auditoriumRepository.findAuditoriumByName(auditoriumName);
	}

	@Override
	public int getSeatsNumber(String auditoriumName) {
		return auditoriumRepository.getSeatsNumber(auditoriumName);
	}

	@Override
	public List<Integer> getVipSeats(String auditoriumName) {
		return auditoriumRepository.getVipSeats(auditoriumName);
	}

}
