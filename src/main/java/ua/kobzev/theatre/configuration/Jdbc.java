package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;

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
        sessionFactoryBean.setAnnotatedClasses(User.class, Event.class);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.DIALECT, environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty(org.hibernate.cfg.Environment.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
            /*
            hibernateProperties.setProperty(Environment.HBM2DDL_AUTO,
                    "create-drop");
             */

            /*

            hibernateProperties.setProperty(Environment.FORMAT_SQL,"true");
             */

        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }
}
