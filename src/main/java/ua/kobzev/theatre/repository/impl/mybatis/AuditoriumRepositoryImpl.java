package ua.kobzev.theatre.repository.impl.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class AuditoriumRepositoryImpl implements AuditoriumRepository {

    @Autowired
    private Mapper mapper;

    @Override
    public List<Auditorium> getAuditoriums() {
        return mapper.getAllAuditoriums();
    }

    @Override
    public Auditorium findAuditoriumByName(String auditoriumName) {
        return mapper.getAuditoriumByName(auditoriumName);
    }

    @Override
    public int getSeatsNumber(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium != null) return auditorium.getNumberOfSeats();
        return 0;
    }

    @Override
    public List<Integer> getVipSeats(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium != null) auditorium.getVipSeats();
        return new ArrayList<>();
    }
}
