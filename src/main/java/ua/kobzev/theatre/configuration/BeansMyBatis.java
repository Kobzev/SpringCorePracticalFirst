package ua.kobzev.theatre.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.kobzev.theatre.repository.*;
import ua.kobzev.theatre.repository.impl.jdbctemplate.RoleRepositoryImpl;
import ua.kobzev.theatre.repository.impl.jdbctemplate.UserAccountRepositoryImpl;
import ua.kobzev.theatre.repository.impl.mybatis.*;

import javax.sql.DataSource;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
@Configuration
@Profile("MYBATIS")
public class BeansMyBatis {

    @Bean
    @Autowired
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeHandlersPackage("ua.kobzev.theatre.repository.impl.mybatis.handlers");

        return sqlSessionFactoryBean;
    }

    @Bean
    @Autowired
    public MapperFactoryBean mapperFactoryBean(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        MapperFactoryBean mapperFactoryBean = new MapperFactoryBean();
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        mapperFactoryBean.setMapperInterface(Mapper.class);

        return mapperFactoryBean;
    }

    @Bean
    public AuditoriumRepository auditoriumRepository() {
        return new AuditoriumRepositoryImpl();
    }

    @Bean
    public AssignedEventRepository assignedEventRepository(){
        return new AssignedEventRepositoryImpl();
    }

    @Bean
    public EventRepository eventRepository(){
        return new EventRepositoryImpl();
    }

    @Bean
    public TicketRepository ticketRepository(){
        return new TicketRepositoryImpl();
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl();
    }

    @Bean
    public RoleRepository roleRepository(){
        return new RoleRepositoryImpl();
    }

    @Bean
    public UserAccountRepository userAccountRepository() {
        return new UserAccountRepositoryImpl();
    }
}
