package ua.kobzev.theatre.repository.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class AuditoriumRepositoryImpl implements AuditoriumRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Auditorium> getAuditoriums() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("from auditoriums").addEntity(AuditoriumRepository.class);
        List<Auditorium> auditoriumList = query.list();

        session.close();
        return auditoriumList;
    }

    @Override
    public Auditorium findAuditoriumByName(String auditoriumName) {
        Session session = sessionFactory.openSession();
        Auditorium auditorium = session.get(Auditorium.class, auditoriumName);
        session.close();

        return auditorium;
    }

    @Override
    public int getSeatsNumber(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium != null) return auditorium.getNumberOfSeats();
        return 0;
    }

    @Override
    public List<Integer> getVipSeats(String auditoriumName) {
        Auditorium auditorium = findAuditoriumByName(auditoriumName);
        if (auditorium != null) auditorium.getVipSeats();
        return new ArrayList<>();
    }
}
