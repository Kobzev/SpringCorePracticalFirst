package ua.kobzev.theatre.repository.impl.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.EventRepository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by kkobziev on 2/11/16.
 */

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public boolean create(Event event) {
        int result = jdbcOperations.update("INSERT into events(name,basePrice,rate) VALUES (?,?,?)",event.getName(),event.getBasePrice(),event.getRate().name());
        return result==0 ? false : true;
    }

    @Override
    public boolean remove(Event event) {
        int result = jdbcOperations.update("DELETE FROM events WHERE name =?", event.getName());
        return result==0 ? false : true;
    }

    @Override
    public Event getByName(String name) {
        List<Event> eventList = jdbcOperations.query("select * from events WHERE name =?",
                new Object[]{name},
                (ResultSet resultSet, int rowNum) -> {
                    Event event = new Event();
                    event.setBasePrice(resultSet.getDouble("basePrice"));
                    event.setName(resultSet.getString("name"));
                    event.setRate(EventRate.valueOf(resultSet.getString("rate")));

                    return event;
                });

        if (eventList.size()==0) return null;
        return eventList.get(0);
    }
}
