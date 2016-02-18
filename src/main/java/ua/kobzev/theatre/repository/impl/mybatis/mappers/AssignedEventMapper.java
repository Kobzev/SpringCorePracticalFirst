package ua.kobzev.theatre.repository.impl.mybatis.mappers;

import org.apache.ibatis.annotations.*;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kkobziev on 2/17/16.
 */
public interface AssignedEventMapper {

    @Select("SELECT * FROM assignedevent")
    @Results(value = {
            @Result(property="id", column="id"),
            @Result(property = "date", column = "date"),
            @Result(property="event",  column="eventname",  javaType=Event.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getEventByName")),
            @Result(property="auditorium", column="auditoriumname", javaType=Auditorium.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getAuditoriumByName"))
    })
    List<AssignedEvent> getAllAssignedEvent();

    @Select("SELECT count(*) FROM assignedevent " +
            "WHERE eventname = #{event_name} " +
            "AND auditoriumname = #{auditorium_name} " +
            "AND date = #{date}")
    int countAssignedEvents(@Param("event_name") String eventName, @Param("auditorium_name") String auditoriumName,
                            @Param("date")LocalDateTime date);

    @Select("SELECT * FROM assignedevent WHERE date BETWEEN #{fromdate} AND #{todate}")
    @Results(value = {
            @Result(property="id", column="id"),
            @Result(property = "date", column = "date"),
            @Result(property="event",  column="eventname",  javaType=Event.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getEventByName")),
            @Result(property="auditorium", column="auditoriumname", javaType=Auditorium.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getAuditoriumByName"))
    })
    List<AssignedEvent> getForDateRange(@Param("fromdate")LocalDateTime from, @Param("todate")LocalDateTime to);

    @Insert("INSERT into assignedevent(eventname,auditoriumname,date) VALUES (#{eventname},#{auditoriumname},#{date})")
    int assignAuditorium(@Param("eventname")String eventname, @Param("auditoriumname") String auditoriumname,
                         @Param("date") LocalDateTime date);

    @Select("SELECT " +
            "auditoriums.name name, " +
            "auditoriums.numberOfSeats numberOfSeats, " +
            "auditoriums.vipSeats vipSeats " +
            "FROM assignedevent, auditoriums " +
            "WHERE assignedevent.auditoriumname = auditoriums.name " +
            "AND assignedevent.date = #{date} and assignedevent.eventname = #{eventname}")
    Auditorium findEventAuditorium(@Param("eventname")String eventName, @Param("date") LocalDateTime date);

    @Select("SELECT * FROM assignedevent WHERE assignedevent.eventname = #{eventname} AND assignedevent.date = #{date}")
    @Results(value = {
            @Result(property="id", column="id"),
            @Result(property = "date", column = "date"),
            @Result(property="event",  column="eventname",  javaType=Event.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getEventByName")),
            @Result(property="auditorium", column="auditoriumname", javaType=Auditorium.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getAuditoriumByName"))
            })
    AssignedEvent findAssignedEventByEventAndDate(@Param("eventname")String eventName, @Param("date") LocalDateTime date);

    /*

    private static final String SQLGETFORDATERANGE = "SELECT \n" +
            "events.name eName, \n" +
            "events.basePrice basePrice,\n" +
            "events.rate rate,\n" +
            "auditoriums.name aName,\n" +
            "auditoriums.numberOfSeats numberOfSeats,\n" +
            "auditoriums.vipSeats vipSeats,\n" +
            "assignedevent.id id,\n" +
            "assignedevent.date date\n" +
            "FROM assignedevent, auditoriums, events\n" +
            "where assignedevent.auditoriumname = auditoriums.name\n" +
            "and assignedevent.eventname = events.name\n" +
            "and assignedevent.date >= ? and assignedevent.date <= ?";

    private static final String SQLFINDBYEVENTANDDATE = "SELECT \n" +
            "events.name eName, \n" +
            "events.basePrice basePrice,\n" +
            "events.rate rate,\n" +
            "auditoriums.name aName,\n" +
            "auditoriums.numberOfSeats numberOfSeats,\n" +
            "auditoriums.vipSeats vipSeats,\n" +
            "assignedevent.id id,\n" +
            "assignedevent.date date\n" +
            "FROM assignedevent, auditoriums, events\n" +
            "where assignedevent.auditoriumname = auditoriums.name\n" +
            "and assignedevent.eventname = events.name\n" +
            "and assignedevent.date = ? and assignedevent.eventname = ?";*/
}
