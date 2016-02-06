package ua.kobzev.theatre.service.impl;

import java.util.ArrayList;
import java.util.List;

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
		List<Auditorium> result = new ArrayList<>();
		result.addAll(auditoriumList);
		return result;
	}

	@Override
	public int getSeatsNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVipSeats() {
		// TODO Auto-generated method stub
		return 0;
	}

}
