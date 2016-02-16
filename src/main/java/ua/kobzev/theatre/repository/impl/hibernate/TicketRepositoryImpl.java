package ua.kobzev.theatre.repository.impl.hibernate;

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
    @Override
    public boolean save(Ticket ticket) {
        // TODO
        return false;
    }

    @Override
    public List<Ticket> findAllByEvent(Event event, LocalDateTime dateTime) {
        // TODO
        return null;
    }

    @Override
    public boolean isPurchased(AssignedEvent assignedEvent, Integer seat) {
        // TODO
        return false;
    }

    @Override
    public List<Ticket> findAllByUser(User user) {
        // TODO
        return null;
    }
}
