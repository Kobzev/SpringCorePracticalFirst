package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository {

	boolean save(Ticket ticket);

	List<Ticket> findAllByEvent(Event event, LocalDateTime dateTime);

	boolean isPurchased(AssignedEvent assignedEvent, Integer seat);

	List<Ticket> findAllByUser(User user);

	Ticket findTicketByUserIdAssignedEventIdSeat(Ticket ticket);

	List<Ticket> findAll();

	Ticket findTicketById(Integer id);

}
