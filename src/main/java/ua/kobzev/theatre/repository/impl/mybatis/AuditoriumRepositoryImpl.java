package ua.kobzev.theatre.repository.impl.mybatis;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;

import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class AuditoriumRepositoryImpl implements AuditoriumRepository {
    @Override
    public List<Auditorium> getAuditoriums() {
        // TODO
        return null;
    }

    @Override
    public Auditorium findAuditoriumByName(String auditoriumName) {
        // TODO
        return null;
    }

    @Override
    public int getSeatsNumber(String auditoriumName) {
        // TODO
        return 0;
    }

    @Override
    public List<Integer> getVipSeats(String auditoriumName) {
        // TODO
        return null;
    }
}
