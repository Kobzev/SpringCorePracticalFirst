package ua.kobzev.theatre.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ua.kobzev.theatre.domain.*;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by kkobziev on 2/11/16.
 */

@Configuration
@PropertySource("classpath:jdbc.properties")
@Profile({"JDBCTEMPLATE", "HIBERNATE", "MYBATIS"})
public class Jdbc {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }


    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String show_sql;

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setAnnotatedClasses(User.class, Event.class, Auditorium.class, AssignedEvent.class, Ticket.class);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.DIALECT, dialect);
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL, show_sql);
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "false");

        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
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
