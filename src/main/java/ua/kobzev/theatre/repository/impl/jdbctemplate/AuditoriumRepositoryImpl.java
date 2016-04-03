package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.annotation.Transactional;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkobziev on 2/11/16.
 */

@Transactional
public class AuditoriumRepositoryImpl implements AuditoriumRepository{

    @Autowired
    private JdbcOperations jdbcOperations;


    @Override
    @Transactional(readOnly = true)
    public List<Auditorium> getAuditoriums() {
        return jdbcOperations.query("select * from auditoriums",
                (ResultSet resultSet, int rowNum) -> {
                    return mapAuditoriumFromResultSet(resultSet);
                });
    }

    private Auditorium mapAuditoriumFromResultSet(ResultSet resultSet) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(resultSet.getString("name"));
        auditorium.setNumberOfSeats(resultSet.getInt("numberOfSeats"));
        auditorium.setVipSeats(resultSet.getString("vipSeats"));
        return auditorium;
    }

    @Override
    @Transactional(readOnly = true)
    public Auditorium findAuditoriumByName(String auditoriumName) {
        List<Auditorium> auditoriumList = jdbcOperations.query("select * from auditoriums WHERE name =?",
                new Object[]{auditoriumName},
                (ResultSet resultSet, int rowNum) -> {
                    return mapAuditoriumFromResultSet(resultSet);
                });

        if (auditoriumList.size()==0) return null;

        return auditoriumList.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public int getSeatsNumber(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium==null) return 0;

        return auditorium.getNumberOfSeats();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> getVipSeats(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium==null) return new ArrayList<>();

        return auditorium.getVipSeats();
    }
}
