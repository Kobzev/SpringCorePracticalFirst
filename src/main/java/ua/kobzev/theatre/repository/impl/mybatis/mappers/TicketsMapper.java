package ua.kobzev.theatre.repository.impl.mybatis.mappers;

import org.apache.ibatis.annotations.*;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kkobziev on 2/17/16.
 */
public interface TicketsMapper {
    // TODO

    @Insert("INSERT INTO tickets(userid,assignedeventid,seat,price) VALUES (#{user.id},#{assignedEvent.id},#{seat},#{price})")
    int saveTicket(Ticket ticket);

    @Select("SELECT count(*) FROM tickets WHERE assignedeventid = #{assignedeventid} AND seat = #{seat}")
    int isPurchased(@Param("assignedeventid")Integer assignedeventid, @Param("seat") Integer seat);

    @Select("SELECT * FROM tickets WHERE userid = #{userid}")
    @Results(value = {
            @Result(property="id", column="id"),
            @Result(property="seat", column = "seat"),
            @Result(property="price", column = "price"),
            @Result(property="user",  column="userid",  javaType=User.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getUserById")),
            @Result(property="assignedEvent", column="assignedeventid", javaType=AssignedEvent.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.findAssignedEventById"))
    })
    List<Ticket> getAllTicketsForUser(@Param("userid")Integer userid);

    @Select("SELECT tickets.* " +
            "FROM tickets,assignedevent " +
            "WHERE tickets.assignedeventid = assignedevent.id " +
            "AND assignedevent.eventname = eventname " +
            "AND assignedevent.date = date")
    @Results(value = {
            @Result(property="id", column="id"),
            @Result(property="seat", column = "seat"),
            @Result(property="price", column = "price"),
            @Result(property="user",  column="userid",  javaType=User.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getUserById")),
            @Result(property="assignedEvent", column="assignedeventid", javaType=AssignedEvent.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.findAssignedEventById"))
    })
    List<Ticket> getAllTicketsByEvenAndDate(@Param("eventname")String eventName, @Param("date")LocalDateTime date);
}
