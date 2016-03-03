package ua.kobzev.theatre.repository.impl.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.AspectRepository;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kkobziev on 2/11/16.
 */

public class AspectRepositoryImpl implements AspectRepository{

    @Autowired
    private JdbcOperations jdbcOperations;

    class Entry{
        private Object key;
        private Integer value;

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }


    private static final String SQLGETACCESSBYNAME =
            "SELECT * " +
                    /*"events.basePrice, " +
                    "events.name, " +
                    "events.rate, " +
                    "accessbyname.count " +*/
            "FROM accessbyname, events " +
            "WHERE " +
            "accessbyname.eventname = events.name";

    @Override
    public Map<Event, Integer> getAccessByName() {
        List<Entry> entryList = jdbcOperations.query(SQLGETACCESSBYNAME,
                (ResultSet resultSet, int rowNum) -> {
                    Event event = new Event();
                    event.setBasePrice(resultSet.getDouble("basePrice"));
                    event.setName(resultSet.getString("name"));
                    event.setRate(EventRate.valueOf(resultSet.getString("rate")));

                    Entry entry = new Entry();
                    entry.setKey(event);
                    entry.setValue(resultSet.getInt("count"));

                    return entry;
                });

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (Event) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void saveAccessByName(Event event) {
        saveEventToTable("accessbyname", event);

    }

    private Integer getCountByTable(String tableName, Event event) {
        try {
            return jdbcOperations.queryForObject("SELECT count FROM "+tableName+" WHERE eventname = ?",
                    new Object[]{event.getName()}, Integer.class);
        }catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    private void saveEventToTable(String tableName, Event event){
        Integer times = getCountByTable(tableName, event);

        if (times == 0) {
            jdbcOperations.update("INSERT into "+tableName+"(eventname,count) VALUES (?,?)",event.getName(),1);
        }else {
            jdbcOperations.update("UPDATE "+tableName+" SET count = ? WHERE eventname = ?", ++times, event.getName());
        }
    }

    private static final String SQLGETPRICEQUERIED =
            "SELECT * " +
                    "FROM pricequeried, events " +
                    "WHERE " +
                    "pricequeried.eventname = events.name";

    @Override
    public Map<Event, Integer> getPriceQueried() {
        List<Entry> entryList = jdbcOperations.query(SQLGETPRICEQUERIED,
                (ResultSet resultSet, int rowNum) -> {
                    Event event = new Event();
                    event.setBasePrice(resultSet.getDouble("basePrice"));
                    event.setName(resultSet.getString("name"));
                    event.setRate(EventRate.valueOf(resultSet.getString("rate")));

                    Entry entry = new Entry();
                    entry.setKey(event);
                    entry.setValue(resultSet.getInt("count"));

                    return entry;
                });

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (Event) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void savePriceQueried(Event event) {
        saveEventToTable("pricequeried", event);
    }

    private static final String SQLGETBOOKEDTICKET=
            "SELECT * " +
                    "FROM bookedtickets, events " +
                    "WHERE " +
                    "bookedtickets.eventname = events.name";

    @Override
    public Map<Event, Integer> getBookedTicket() {
        List<Entry> entryList = jdbcOperations.query(SQLGETBOOKEDTICKET,
                (ResultSet resultSet, int rowNum) -> {
                    Event event = new Event();
                    event.setBasePrice(resultSet.getDouble("basePrice"));
                    event.setName(resultSet.getString("name"));
                    event.setRate(EventRate.valueOf(resultSet.getString("rate")));

                    Entry entry = new Entry();
                    entry.setKey(event);
                    entry.setValue(resultSet.getInt("count"));

                    return entry;
                });

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (Event) entry.getKey(), entry -> entry.getValue()));
    }

    @Override
    public void saveBookedTicket(Event event) {
        saveEventToTable("bookedtickets", event);
    }

    private static final String SQLGETTOTALDISCOUNTS =
            "SELECT * FROM totaldiscounts";

    @Override
    public Map<String, Integer> getTotalDiscounts() {
        List<Entry> entryList = jdbcOperations.query(SQLGETTOTALDISCOUNTS,
                (ResultSet resultSet, int rowNum) -> {
                    Entry entry = new Entry();
                    entry.setKey(resultSet.getString("discountstrategy"));
                    entry.setValue(resultSet.getInt("count"));

                    return entry;
                });

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (String) entry.getKey(), entry -> entry.getValue()));
    }

    private Integer getTotalDiscountTimes(DiscountStrategy strategy) {
        try {
            return jdbcOperations.queryForObject("SELECT count FROM totaldiscounts WHERE discountstrategy = ?",
                    new Object[]{strategy.toString()}, Integer.class);
        }catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public void saveTotalDiscount(DiscountStrategy strategy) {
        Integer times = getTotalDiscountTimes(strategy);

        if (times == 0) {
            jdbcOperations.update("INSERT into totaldiscounts(discountstrategy,count) VALUES (?,?)",strategy.toString(),1);
        }else {
            jdbcOperations.update("UPDATE totaldiscounts SET count = ? WHERE discountstrategy = ?", ++times, strategy.toString());
        }

    }

    private static final String SQLGETINFOABOUTTOTALDISCOUNTFORUSER =
            "SELECT " +
            " totaldiscountsforuser.count, " +
            " users.id id, " +
            " users.birthDay, " +
            " users.email, " +
            " users.name " +
            "FROM totaldiscountsforuser, users " +
            "WHERE " +
            "totaldiscountsforuser.userid = users.id;";

    @Override
    public Map<User, Integer> getInfoAboutTotalDiscountForUser() {
        List<Entry> entryList = jdbcOperations.query(SQLGETINFOABOUTTOTALDISCOUNTFORUSER,
                (ResultSet resultSet, int rowNum) -> {
                    User user = new User();
                    user.setBirthDay(LocalDateTime.of(resultSet.getDate("birthDay").toLocalDate(), LocalTime.NOON));
                    user.setEmail(resultSet.getString("email"));
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));


                    Entry entry = new Entry();
                    entry.setKey(user);
                    entry.setValue(resultSet.getInt("count"));

                    return entry;
                });

        return entryList.stream()
                .collect(Collectors.toMap(entry -> (User) entry.getKey(), entry -> entry.getValue()));
    }

    private Integer previousInfoAboutTotalDiscountForUser(User user){
        try {
            return jdbcOperations.queryForObject("SELECT count FROM totaldiscountsforuser WHERE userid = ?",
                    new Object[]{user.getId()}, Integer.class);
        }catch (EmptyResultDataAccessException e) {
            return 0;
        }

    }

    @Override
    public void saveInfoAboutTotalDiscountForUser(User user) {
        Integer times = previousInfoAboutTotalDiscountForUser(user);

        if (times == 0) {
            jdbcOperations.update("INSERT into totaldiscountsforuser(userid,count) VALUES (?,?)",user.getId(),1);
        }else {
            jdbcOperations.update("UPDATE totaldiscountsforuser SET count = ? WHERE userid = ?", ++times, user.getId());
        }

    }
}
