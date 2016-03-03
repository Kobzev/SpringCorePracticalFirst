package ua.kobzev.theatre.repository.impl.mybatis.mappers;

import org.apache.ibatis.annotations.*;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.impl.mybatis.Entry;

import java.util.List;

/**
 * Created by kkobziev on 2/17/16.
 */
public interface AspectsMapper {
    @Select("SELECT * FROM accessbyname")
    @Results({
            @Result(property = "key", column = "eventname", javaType = Event.class, one = @One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getEventByName")),
            @Result(property = "value", column = "count", javaType = Integer.class)
    })
    List<Entry> getAccessByName();

    @Insert("INSERT into accessbyname(eventname,count) VALUES (#{name},1)")
    int insertAccessByName(@Param("name")String eventName);

    @Update("UPDATE accessbyname SET count = #{count} WHERE eventname = #{name}")
    int updateAccessByName(@Param("name")String eventName, @Param("count")Integer count);

    @Select("SELECT * FROM pricequeried")
    @Results({
            @Result(property = "key", column = "eventname", javaType = Event.class, one = @One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getEventByName")),
            @Result(property = "value", column = "count", javaType = Integer.class)
    })
    List<Entry> getPriceQueried();

    @Insert("INSERT into pricequeried(eventname,count) VALUES (#{name},1)")
    int insertPriceQueried(@Param("name")String eventName);

    @Update("UPDATE pricequeried SET count = #{count} WHERE eventname = #{name}")
    int updatePriceQueried(@Param("name")String eventName, @Param("count")Integer count);

    @Select("SELECT * FROM bookedtickets")
    @Results({
            @Result(property = "key", column = "eventname", javaType = Event.class, one = @One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getEventByName")),
            @Result(property = "value", column = "count", javaType = Integer.class)
    })
    List<Entry> getBookedTicket();

    @Insert("INSERT into bookedtickets(eventname,count) VALUES (#{name},1)")
    int insertBookedTicket(@Param("name")String eventName);

    @Update("UPDATE bookedtickets SET count = #{count} WHERE eventname = #{name}")
    int updateBookedTicket(@Param("name")String eventName, @Param("count")Integer count);

    @Select("SELECT * FROM totaldiscounts")
    @Results({
            @Result(property = "key", column = "discountstrategy", javaType = String.class),
            @Result(property = "value", column = "count", javaType = Integer.class)
    })
    List<Entry> getTotalDiscounts();

    @Insert("INSERT into totaldiscounts(discountstrategy,count) VALUES (#{discountstrategy},1)")
    int insertTotalDiscounts(@Param("discountstrategy")String discountstrategy);

    @Update("UPDATE totaldiscounts SET count = #{count} WHERE discountstrategy = #{discountstrategy}")
    int updateTotalDiscounts(@Param("discountstrategy")String discountstrategy, @Param("count")Integer count);

    @Select("SELECT * FROM totaldiscountsforuser")
    @Results({
            @Result(property = "key", column = "userid", javaType = User.class, one=@One(select="ua.kobzev.theatre.repository.impl.mybatis.Mapper.getUserById")),
            @Result(property = "value", column = "count", javaType = Integer.class)
    })
    List<Entry> getInfoAboutTotalDiscountForUser();

    @Insert("INSERT into totaldiscountsforuser(userid,count) VALUES (#{userid},1)")
    int insertInfoAboutTotalDiscountForUser(@Param("userid")Integer userid);

    @Update("UPDATE totaldiscountsforuser SET count = #{count} WHERE userid = #{userid}")
    int updateInfoAboutTotalDiscountForUser(@Param("userid")Integer eventName, @Param("count")Integer count);
}
