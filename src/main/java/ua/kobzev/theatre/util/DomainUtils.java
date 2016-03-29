package ua.kobzev.theatre.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

/**
 * Created by kkobziev on 2/11/16.
 */

@Component
public class DomainUtils {

    @Autowired
    private ApplicationContext context;

    public DomainUtils() {
    }

    public Ticket createNewTicket(User user, AssignedEvent assignedEvent, int seat) {
        Ticket ticket = new Ticket();//(Ticket) context.getBean("ticket");
        ticket.setAssignedEvent(assignedEvent);
        ticket.setSeat(seat);
        ticket.setUser(user);

        return ticket;
    }
}
