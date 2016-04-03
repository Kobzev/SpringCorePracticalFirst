package ua.kobzev.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;
import ua.kobzev.theatre.service.AuditoriumService;

import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

@Service
@Transactional
public class AuditoriumServiceImpl implements AuditoriumService {

	@Autowired
	private AuditoriumRepository auditoriumRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Auditorium> getAuditoriums() {
		return auditoriumRepository.getAuditoriums();
	}

	@Override
	@Transactional(readOnly = true)
	public Auditorium findAuditoriumByName(String auditoriumName) {
		return auditoriumRepository.findAuditoriumByName(auditoriumName);
	}

	@Override
	@Transactional(readOnly = true)
	public int getSeatsNumber(String auditoriumName) {
		return auditoriumRepository.getSeatsNumber(auditoriumName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Integer> getVipSeats(String auditoriumName) {
		return auditoriumRepository.getVipSeats(auditoriumName);
	}

}
