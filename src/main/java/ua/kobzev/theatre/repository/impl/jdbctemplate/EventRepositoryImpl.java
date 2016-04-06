package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.annotation.Transactional;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.EventRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by kkobziev on 2/11/16.
 */

@Transactional
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private ApplicationContext context;

    @Override
    public boolean create(Event event) {
        int result = jdbcOperations.update("INSERT into events(name,basePrice,rate) VALUES (?,?,?)",event.getName(),event.getBasePrice(),event.getRate().name());
        return result!=0;
    }

    @Override
    public boolean remove(Event event) {
        int result = jdbcOperations.update("DELETE FROM events WHERE name =?", event.getName());
        return result!=0;
    }

    @Override
    public boolean updateEvent(Event event) {
        int result = jdbcOperations.update("UPDATE events SET basePrice=?, rate=? WHERE name =?",
                event.getBasePrice(), event.getRate(), event.getName());
        return result!=0;
    }

    @Override
    @Transactional(readOnly = true)
    public Event getByName(String name) {
        List<Event> eventList = jdbcOperations.query("select * from events WHERE name =?",
                new Object[]{name},
                (ResultSet resultSet, int rowNum) -> {
                    return mapEvenFromResultSet(resultSet);
                });

        if (eventList.size()==0) return null;
        return eventList.get(0);
    }

    private Event mapEvenFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();//(Event) context.getBean("event");//
        event.setBasePrice(resultSet.getDouble("basePrice"));
        event.setName(resultSet.getString("name"));
        event.setRate(EventRate.valueOf(resultSet.getString("rate")));

        return event;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return jdbcOperations.query("select * from events",
                (ResultSet resultSet, int rowNum) -> {
                    return mapEvenFromResultSet(resultSet);
                });
    }
}
