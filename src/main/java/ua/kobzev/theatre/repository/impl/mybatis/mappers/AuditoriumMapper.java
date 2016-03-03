package ua.kobzev.theatre.repository.impl.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ua.kobzev.theatre.domain.Auditorium;

import java.util.List;

/**
 * Created by kkobziev on 2/17/16.
 */
public interface AuditoriumMapper {

    @Select("SELECT * FROM auditoriums")
    List<Auditorium> getAllAuditoriums();

    @Select("SELECT * FROM auditoriums WHERE name = #{name}")
    Auditorium getAuditoriumByName(@Param("name")String name);
}
