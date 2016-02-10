package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.Event;

import java.util.Map;

/**
 * Created by kkobziev on 2/10/16.
 */
public interface AspectRepository {

    Map<Event, Integer> getAccessByName();
    void saveAccessByName(Event event);

    Map<Event, Integer> getBookedTicket();
    void saveBookedTicket(Event event);
}
