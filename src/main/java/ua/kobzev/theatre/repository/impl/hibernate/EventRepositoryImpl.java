package ua.kobzev.theatre.repository.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.EventRepository;

import java.io.Serializable;

import static java.util.Objects.isNull;

/**
 * Created by kkobziev on 2/16/16.
 */

public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Event event) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(event);
        tx.commit();
        Serializable id = session.getIdentifier(event);
        session.close();
        return !isNull(id);
    }

    @Override
    public boolean remove(Event event) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Event eventLoad = session.load(Event.class, event.getName());
        session.delete(eventLoad);
        tx.commit();
        Serializable ids = session.getIdentifier(eventLoad);
        session.close();
        return !isNull(ids);
    }

    @Override
    public Event getByName(String name) {
        Session session = sessionFactory.openSession();
        Event event = session.get(Event.class, name);
        session.close();
        return event;
    }
}
