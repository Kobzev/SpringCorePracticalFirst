package ua.kobzev.theatre.service;

import ua.kobzev.theatre.domain.Event;

/**
 * Created by kkobziev on 2/10/16.
 */
public interface AspectService {

    void printStatistic();
    void saveAccessByName(Event event);
    void saveBookedTicket(Event event);
}
