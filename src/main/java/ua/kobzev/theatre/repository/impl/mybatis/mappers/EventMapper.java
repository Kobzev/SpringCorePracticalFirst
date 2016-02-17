package ua.kobzev.theatre.repository.impl.mybatis.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ua.kobzev.theatre.domain.Event;

/**
 * Created by kkobziev on 2/17/16.
 */
public interface EventMapper {

    @Select("SELECT * FROM events WHERE name = #{name}")
    Event getEventByName(@Param("name")String name);

    @Insert("INSERT INTO events (name,email,birthDay) VALUES (#{name},#{email},#{birthDay})")
    int createEvent(Event event);

    @Delete("DELETE FROM events WHERE name = #{name}")
    int removeEvent(@Param("name")String name);
}
