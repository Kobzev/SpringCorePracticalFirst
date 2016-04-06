package ua.kobzev.theatre.repository.impl.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private Mapper mapper;

    @Override
    public boolean save(Ticket ticket) {
        if (isPurchased(ticket.getAssignedEvent(), ticket.getSeat())) return false;

        return mapper.saveTicket(ticket)>0;
    }

    @Override
    public List<Ticket> findAllByEvent(Event event, LocalDateTime dateTime) {
        return mapper.getAllTicketsByEvenAndDate(event.getName(), dateTime);
    }

    @Override
    public boolean isPurchased(AssignedEvent assignedEvent, Integer seat) {
        return mapper.isPurchased(assignedEvent.getId(), seat) > 0;
    }

    @Override
    public List<Ticket> findAllByUser(User user) {
        return mapper.getAllTicketsForUser(user.getId());
    }

    @Override
    public Ticket findTicketByUserIdAssignedEventIdSeat(Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Ticket findTicketById(Integer id) {
        return null;
    }
}
