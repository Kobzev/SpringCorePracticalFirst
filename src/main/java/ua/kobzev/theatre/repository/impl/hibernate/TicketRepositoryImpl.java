package ua.kobzev.theatre.repository.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.TicketRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(ticket);
        tx.commit();
        Serializable id = session.getIdentifier(ticket);
        session.close();
        return ((Integer) id) > 0;
    }

    @Override
    public List<Ticket> findAllByEvent(Event event, LocalDateTime dateTime) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ticket where assignedEvent.event = :event and assignedEvent.date = :date");
        query.setParameter("event", event);
        query.setParameter("date", dateTime);

        return query.list();
    }

    @Override
    public boolean isPurchased(AssignedEvent assignedEvent, Integer seat) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ticket where assignedEvent = :assignedEvent and seat = :seat");
        query.setParameter("assignedEvent", assignedEvent);
        query.setParameter("seat", seat);

        return query.list().size()>0;
    }

    @Override
    public List<Ticket> findAllByUser(User user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ticket where user = :user");
        query.setParameter("user", user);

        return query.list();
    }
}
