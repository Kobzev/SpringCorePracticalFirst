package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;
import ua.kobzev.theatre.domain.*;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.TicketRepository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by kkobziev on 2/11/16.
 */

public class TicketRepositoryImpl implements TicketRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private ApplicationContext context;

    private static final String SQLSAVE = "INSERT into tickets(userid,assignedeventid,seat,price)" +
                                          "VALUES (?,?,?,?)";

    @Override
    public boolean save(Ticket ticket) {
        if (isPurchased(ticket.getAssignedEvent(), ticket.getSeat())) return false;

        int result = jdbcOperations.update(SQLSAVE,
                ticket.getUser().getId(), ticket.getAssignedEvent().getId(), ticket.getSeat(), ticket.getPrice());
        return result!=0;
    }

    private static final String SQLFINDALLBYEVENT =
            "SELECT users.id uid, " +
                    "users.name uname, " +
                    "users.email email, " +
                    "users.birthDay birthDay, " +
                    "auditoriums.name aname, " +
                    "auditoriums.numberOfSeats seats, " +
                    "auditoriums.vipSeats vipseat, " +
                    "tickets.assignedeventid aid, " +
                    "tickets.price price, " +
                    "tickets.seat seat, " +
                    "tickets.id tid " +
            "FROM tickets,users,assignedevent,auditoriums " +
            "WHERE tickets.userid = users.id " +
            "AND tickets.assignedeventid = assignedevent.id " +
            "AND assignedevent.auditoriumname = auditoriums.name " +
            "AND assignedevent.eventname = ? " +
            "AND assignedevent.date = ?";

    @Override
    public List<Ticket> findAllByEvent(Event event, LocalDateTime dateTime) {
        return jdbcOperations.query(SQLFINDALLBYEVENT,
                new Object[]{event.getName(), dateTime},
                (ResultSet resultSet, int rowNum) -> {
                    User user = new User();
                    user.setId(resultSet.getInt("uid"));
                    user.setName(resultSet.getString("uname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setBirthDay(LocalDateTime.of(resultSet.getDate("birthDay").toLocalDate(), LocalTime.NOON));

                    Auditorium auditorium = new Auditorium();
                    auditorium.setName(resultSet.getString("aname"));
                    auditorium.setNumberOfSeats(resultSet.getInt("seats"));
                    auditorium.setVipSeats(resultSet.getString("vipseat"));

                    AssignedEvent assignedEvent = new AssignedEvent();
                    assignedEvent.setAuditorium(auditorium);
                    assignedEvent.setDate(dateTime);
                    assignedEvent.setEvent(event);
                    assignedEvent.setId(resultSet.getInt("aid"));

                    Ticket ticket = new Ticket();
                    ticket.setAssignedEvent(assignedEvent);
                    ticket.setPrice(resultSet.getDouble("price"));
                    ticket.setSeat(resultSet.getInt("seat"));
                    ticket.setUser(user);
                    ticket.setId(resultSet.getInt("tid"));

                    return ticket;
                });
    }

    private static final String SQLISPURCHASED = "SELECT count(*) from tickets " +
                                                "WHERE assignedeventid = ? " +
                                                "AND seat = ? ";

    @Override
    public boolean isPurchased(AssignedEvent assignedEvent, Integer seat) {
        int result = jdbcOperations.queryForObject(SQLISPURCHASED,
                new Object[]{assignedEvent.getId(), seat},
                Integer.class);
        return result > 0;
    }

    private static final String SQLFINDALLBYUSER =
            "SELECT auditoriums.name aname, " +
                    "auditoriums.numberOfSeats seats, " +
                    "auditoriums.vipSeats vipseat, " +
                    "assignedevent.date adate, " +
                    "events.name ename, " +
                    "events.basePrice basePrice, " +
                    "events.rate rate, " +
                    "tickets.assignedeventid aid, " +
                    "tickets.price price, " +
                    "tickets.seat seat, " +
                    "tickets.id tid " +
            "FROM tickets,events,assignedevent,auditoriums " +
            "WHERE tickets.assignedeventid = assignedevent.id " +
            "AND assignedevent.auditoriumname = auditoriums.name " +
            "AND assignedevent.eventname = events.name " +
            "AND tickets.userid = ? ";

    @Override
    public List<Ticket> findAllByUser(User user) {
        return jdbcOperations.query(SQLFINDALLBYUSER,
                new Object[]{user.getId()},
                (ResultSet resultSet, int rowNum) -> {

                    Auditorium auditorium = new Auditorium();
                    auditorium.setName(resultSet.getString("aname"));
                    auditorium.setNumberOfSeats(resultSet.getInt("seats"));
                    auditorium.setVipSeats(resultSet.getString("vipseat"));

                    Event event = (Event) context.getBean("event");
                    event.setBasePrice(resultSet.getDouble("basePrice"));
                    event.setName(resultSet.getString("ename"));
                    event.setRate(EventRate.valueOf(resultSet.getString("rate")));

                    AssignedEvent assignedEvent = new AssignedEvent();
                    assignedEvent.setAuditorium(auditorium);
                    assignedEvent.setDate(resultSet.getTimestamp("adate").toLocalDateTime());
                    assignedEvent.setEvent(event);
                    assignedEvent.setId(resultSet.getInt("aid"));

                    Ticket ticket = new Ticket();
                    ticket.setAssignedEvent(assignedEvent);
                    ticket.setId(resultSet.getInt("tid"));
                    ticket.setPrice(resultSet.getDouble("price"));
                    ticket.setSeat(resultSet.getInt("seat"));
                    ticket.setUser(user);

                    return ticket;
                });
    }
}
