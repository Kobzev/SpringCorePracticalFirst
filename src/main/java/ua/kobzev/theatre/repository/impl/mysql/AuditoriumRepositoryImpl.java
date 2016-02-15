package ua.kobzev.theatre.repository.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkobziev on 2/11/16.
 */

@Repository
public class AuditoriumRepositoryImpl implements AuditoriumRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Auditorium> getAuditoriums() {
        return jdbcOperations.query("select * from auditoriums",
                (ResultSet resultSet, int rowNum) -> {
                    Auditorium auditorium = new Auditorium();
                    auditorium.setName(resultSet.getString("name"));
                    auditorium.setNumberOfSeats(resultSet.getInt("numberOfSeats"));
                    auditorium.setVipSeats(resultSet.getString("vipSeats"));
                    return auditorium;
                });
    }

    @Override
    public Auditorium findAuditoriumByName(String auditoriumName) {
        List<Auditorium> auditoriumList = jdbcOperations.query("select * from auditoriums WHERE name =?",
                new Object[]{auditoriumName},
                (ResultSet resultSet, int rowNum) -> {
                    Auditorium auditorium = new Auditorium();
                    auditorium.setName(resultSet.getString("name"));
                    auditorium.setNumberOfSeats(resultSet.getInt("numberOfSeats"));
                    auditorium.setVipSeats(resultSet.getString("vipSeats"));
                    return auditorium;
                });

        if (auditoriumList.size()==0) return null;

        return auditoriumList.get(0);
    }

    @Override
    public int getSeatsNumber(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium==null) return 0;

        return auditorium.getNumberOfSeats();
    }

    @Override
    public List<Integer> getVipSeats(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium==null) return new ArrayList<>();

        return auditorium.getVipSeats();
    }
}
