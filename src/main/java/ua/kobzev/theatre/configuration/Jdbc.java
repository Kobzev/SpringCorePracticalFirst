package ua.kobzev.theatre.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ua.kobzev.theatre.domain.*;
import ua.kobzev.theatre.repository.impl.mybatis.Mapper;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by kkobziev on 2/11/16.
 */

@PropertySource("file:src/main/resources/jdbc.properties")
public class Jdbc {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    @Autowired
    public JdbcOperations jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setAnnotatedClasses(User.class, Event.class, Auditorium.class, AssignedEvent.class, Ticket.class);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.DIALECT, environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "false");

        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

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
    @Autowired
    public SpringLiquibase liquibase(DataSource dataSource){
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:db/changelog.xml");

        return springLiquibase;
    }
}
