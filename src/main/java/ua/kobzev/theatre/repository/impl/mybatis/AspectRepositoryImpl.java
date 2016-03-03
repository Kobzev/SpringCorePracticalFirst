package ua.kobzev.theatre.repository.impl.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.AspectRepository;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kkobziev on 2/16/16.
 */

public class AspectRepositoryImpl implements AspectRepository {

    @Autowired
    private Mapper mapper;

    @Override
    public Map<Event, Integer> getAccessByName() {
        List<Entry> entryList = mapper.getAccessByName();

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (Event) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void saveAccessByName(Event event) {
        Map<Event, Integer> eventMap = getAccessByName();

        if (eventMap.containsKey(event)) mapper.updateAccessByName(event.getName(), eventMap.get(event) + 1);
        else mapper.insertAccessByName(event.getName());
    }

    @Override
    public Map<Event, Integer> getPriceQueried() {
        List<Entry> entryList = mapper.getPriceQueried();

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (Event) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void savePriceQueried(Event event) {
        Map<Event, Integer> eventMap = getPriceQueried();

        if (eventMap.containsKey(event)) mapper.updatePriceQueried(event.getName(), eventMap.get(event) + 1);
        else mapper.insertPriceQueried(event.getName());
    }

    @Override
    public Map<Event, Integer> getBookedTicket() {
        List<Entry> entryList = mapper.getBookedTicket();

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (Event) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void saveBookedTicket(Event event) {
        Map<Event, Integer> eventMap = getBookedTicket();

        if (eventMap.containsKey(event)) mapper.updateBookedTicket(event.getName(), eventMap.get(event) + 1);
        else mapper.insertBookedTicket(event.getName());
    }

    @Override
    public Map<String, Integer> getTotalDiscounts() {
        List<Entry> entryList = mapper.getTotalDiscounts();

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (String) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void saveTotalDiscount(DiscountStrategy strategy) {
        Map<String, Integer> discountsMap = getTotalDiscounts();

        if (discountsMap.containsKey(strategy.toString())) mapper.updateTotalDiscounts(strategy.toString(), discountsMap.get(strategy.toString()) + 1);
        else mapper.insertTotalDiscounts(strategy.toString());
    }

    @Override
    public Map<User, Integer> getInfoAboutTotalDiscountForUser() {
        List<Entry> entryList = mapper.getInfoAboutTotalDiscountForUser();

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (User) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void saveInfoAboutTotalDiscountForUser(User user) {
        Map<User, Integer> userMap = getInfoAboutTotalDiscountForUser();

        if (userMap.containsKey(user)) mapper.updateInfoAboutTotalDiscountForUser(user.getId(), userMap.get(user) + 1);
        else mapper.insertInfoAboutTotalDiscountForUser(user.getId());
    }
}
