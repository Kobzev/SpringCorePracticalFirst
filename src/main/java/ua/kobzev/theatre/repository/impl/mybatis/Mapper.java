package ua.kobzev.theatre.repository.impl.mybatis;

import ua.kobzev.theatre.repository.impl.mybatis.mappers.AuditoriumMapper;
import ua.kobzev.theatre.repository.impl.mybatis.mappers.EventMapper;
import ua.kobzev.theatre.repository.impl.mybatis.mappers.UserMapper;

/**
 * Created by kkobziev on 2/16/16.
 */
public interface Mapper extends UserMapper, EventMapper, AuditoriumMapper{
}
