package ua.kobzev.theatre.repository.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.AssignedEventRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class AssignedEventRepositoryImpl implements AssignedEventRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AssignedEvent> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from AssignedEvent");
        List<AssignedEvent> assignedEventList = query.list();

        session.close();
        return assignedEventList;
    }

    @Override
    public List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from AssignedEvent where date BETWEEN :from and :to ");
        query.setParameter("from", from);
        query.setParameter("to", to);

        return query.list();
    }

    @Override
    public List<AssignedEvent> getNextEvents(LocalDateTime to) {
        return getForDateRange(LocalDateTime.now(), to);
    }

    @Override
    public boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        AssignedEvent assignedEvent = new AssignedEvent(event, auditorium, date);
        session.saveOrUpdate(assignedEvent);
        tx.commit();
        Serializable id = session.getIdentifier(assignedEvent);
        session.close();
        return ((Integer) id) > 0;
    }

    @Override
    public Auditorium findEventAuditorium(Event event, LocalDateTime dateTime) {
        AssignedEvent assignedEvent = findByEventAndDate(event, dateTime);
        if (assignedEvent != null) return assignedEvent.getAuditorium();
        return null;
    }

    @Override
    public AssignedEvent findByEventAndDate(Event event, LocalDateTime dateTime) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from AssignedEvent where date = :date and eventname = :eventname ");
        query.setParameter("date", dateTime);
        query.setParameter("eventname", event.getName());


        return ((List<AssignedEvent>)query.list()).stream().findFirst().orElse(null);
    }
}
