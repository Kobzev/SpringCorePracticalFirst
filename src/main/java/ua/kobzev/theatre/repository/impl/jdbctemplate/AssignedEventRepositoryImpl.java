package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.AssignedEventRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kkobziev on 2/11/16.
 */

public class AssignedEventRepositoryImpl implements AssignedEventRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private ApplicationContext context;

    private static final String SQLGETALL = "SELECT \n" +
            "events.name eName, \n" +
            "events.basePrice basePrice,\n" +
            "events.rate rate,\n" +
            "auditoriums.name aName,\n" +
            "auditoriums.numberOfSeats numberOfSeats,\n" +
            "auditoriums.vipSeats vipSeats,\n" +
            "assignedevent.id id,\n" +
            "assignedevent.date date\n" +
            "FROM assignedevent, auditoriums, events\n" +
            "where assignedevent.auditoriumname = auditoriums.name\n" +
            "and assignedevent.eventname = events.name";


    @Override
    public List<AssignedEvent> getAll() {
        return jdbcOperations.query(SQLGETALL,
                (ResultSet resultSet, int rowNum) -> {
                    return mapAssignedEventFromResulSet(resultSet);
                });
    }


    private static final String SQLGETFORDATERANGE = "SELECT \n" +
            "events.name eName, \n" +
            "events.basePrice basePrice,\n" +
            "events.rate rate,\n" +
            "auditoriums.name aName,\n" +
            "auditoriums.numberOfSeats numberOfSeats,\n" +
            "auditoriums.vipSeats vipSeats,\n" +
            "assignedevent.id id,\n" +
            "assignedevent.date date\n" +
            "FROM assignedevent, auditoriums, events\n" +
            "where assignedevent.auditoriumname = auditoriums.name\n" +
            "and assignedevent.eventname = events.name\n" +
            "and assignedevent.date BETWEEN ? AND ?";

    @Override
    public List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to) {
        return jdbcOperations.query(SQLGETFORDATERANGE,
                new Object[]{from, to},
                (ResultSet resultSet, int rowNum) -> {
                    return mapAssignedEventFromResulSet(resultSet);
                });
    }

    @Override
    public List<AssignedEvent> getNextEvents(LocalDateTime to) {
        return getForDateRange(LocalDateTime.now(), to);
    }

    @Override
    public boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
        if (isEventAssigned(event, auditorium, date)) return false;

        int result = jdbcOperations.update("INSERT into assignedevent(eventname,auditoriumname,date) VALUES (?,?,?)",event.getName(),auditorium.getName(),date);
        return result!=0;
    }

    private static final String SQLISEVENTASSIGNED = "SELECT count(*) FROM assignedevent " +
                                                    "WHERE eventname = ? " +
                                                    "AND auditoriumname = ? " +
                                                    "AND date = ?";

    private boolean isEventAssigned(Event event, Auditorium auditorium, LocalDateTime date){
        int result = jdbcOperations.queryForObject(SQLISEVENTASSIGNED,
                new Object[]{event.getName(),auditorium.getName(), date},
                Integer.class);
        return result > 0;
    }

    private static final String SQLFINDEVENTAUDITORIUM = "SELECT \n" +
            "auditoriums.name aName,\n" +
            "auditoriums.numberOfSeats numberOfSeats,\n" +
            "auditoriums.vipSeats vipSeats\n" +
            "FROM assignedevent, auditoriums\n" +
            "where assignedevent.auditoriumname = auditoriums.name\n" +
            "and assignedevent.date = ? and assignedevent.eventname = ?";

    @Override
    public Auditorium findEventAuditorium(Event event, LocalDateTime dateTime) {
        List<Auditorium> auditoriumList =  jdbcOperations.query(SQLFINDEVENTAUDITORIUM,
                new Object[]{dateTime, event.getName()},
                (ResultSet resultSet, int rowNum) -> {
                    Auditorium auditorium = new Auditorium();
                    auditorium.setName(resultSet.getString("aName"));
                    auditorium.setNumberOfSeats(resultSet.getInt("numberOfSeats"));
                    auditorium.setVipSeats(resultSet.getString("vipSeats"));

                    return auditorium;
                });

        if (auditoriumList.size()==0) return null;

        return auditoriumList.get(0);
    }

    private static final String SQLFINDBYEVENTANDDATE = "SELECT \n" +
            "events.name eName, \n" +
            "events.basePrice basePrice,\n" +
            "events.rate rate,\n" +
            "auditoriums.name aName,\n" +
            "auditoriums.numberOfSeats numberOfSeats,\n" +
            "auditoriums.vipSeats vipSeats,\n" +
            "assignedevent.id id,\n" +
            "assignedevent.date date\n" +
            "FROM assignedevent, auditoriums, events\n" +
            "where assignedevent.auditoriumname = auditoriums.name\n" +
            "and assignedevent.eventname = events.name\n" +
            "and assignedevent.date = ? and assignedevent.eventname = ?";

    @Override
    public AssignedEvent findByEventAndDate(Event event, LocalDateTime dateTime) {
        List<AssignedEvent> assignedEvents = jdbcOperations.query(SQLFINDBYEVENTANDDATE,
                new Object[]{dateTime, event.getName()},
                (ResultSet resultSet, int rowNum) -> {
                    return mapAssignedEventFromResulSet(resultSet);
                });

        if (assignedEvents.size()== 0) return null;
        return assignedEvents.get(0);
    }

    private static final String SQL_FIND_BY_ID = "SELECT \n" +
            "events.name eName, \n" +
            "events.basePrice basePrice,\n" +
            "events.rate rate,\n" +
            "auditoriums.name aName,\n" +
            "auditoriums.numberOfSeats numberOfSeats,\n" +
            "auditoriums.vipSeats vipSeats,\n" +
            "assignedevent.id id,\n" +
            "assignedevent.date date\n" +
            "FROM assignedevent, auditoriums, events\n" +
            "where assignedevent.auditoriumname = auditoriums.name\n" +
            "and assignedevent.eventname = events.name\n" +
            "and assignedevent.id = ?";

    @Override
    public AssignedEvent findById(Integer id) {
        List<AssignedEvent> assignedEvents = jdbcOperations.query(SQL_FIND_BY_ID,
                new Object[]{id},
                (ResultSet resultSet, int rowNum) -> {
                    return mapAssignedEventFromResulSet(resultSet);
                });

        if (assignedEvents.size()!= 0) return assignedEvents.get(0);
        return null;
    }

    private AssignedEvent mapAssignedEventFromResulSet(ResultSet resultSet) throws SQLException{
        AssignedEvent assignedEvent = new AssignedEvent();

        Auditorium auditorium = new Auditorium();
        auditorium.setName(resultSet.getString("aName"));
        auditorium.setNumberOfSeats(resultSet.getInt("numberOfSeats"));
        auditorium.setVipSeats(resultSet.getString("vipSeats"));

        Event ev = new Event();
        ev.setBasePrice(resultSet.getDouble("basePrice"));
        ev.setName(resultSet.getString("eName"));
        ev.setRate(EventRate.valueOf(resultSet.getString("rate")));

        assignedEvent.setAuditorium(auditorium);
        assignedEvent.setId(resultSet.getInt("id"));
        assignedEvent.setEvent(ev);
        assignedEvent.setDate(resultSet.getTimestamp("date").toLocalDateTime());

        return assignedEvent;
    }
}
