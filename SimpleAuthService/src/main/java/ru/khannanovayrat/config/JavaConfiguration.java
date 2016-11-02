package ru.khannanovayrat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.khannanovayrat.dao.CarJdbcDaoImpl;
import ru.khannanovayrat.dao.UserJdbcUserDaoImpl;
import ru.khannanovayrat.service.CarService;
import ru.khannanovayrat.service.UserService;

import javax.sql.DataSource;

/**
 * Created by Ayrat on 02.11.2016.
 */
@Configuration
public class JavaConfiguration {

    @Bean(name = "userService")
    @Scope("singleton")
    public UserService userService(){
        return new UserService(userJdbcUserDao());
    }

    @Bean
    @Scope("singleton")
    public UserJdbcUserDaoImpl userJdbcUserDao(){
        return new UserJdbcUserDaoImpl(dataSource());
    }

    @Bean
    @Scope("singleton")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/authorized_users");
        dataSource.setUsername("postgres");
        dataSource.setPassword("guitar012yamaha");
        return dataSource;
    }

    @Bean(name = "carService")
    @Scope("singleton")
    public CarService carService(){
        return new CarService(carJdbcDao());
    }

    @Bean
    @Scope("singleton")
    public CarJdbcDaoImpl carJdbcDao(){
        return new CarJdbcDaoImpl(dataSource());
    }
}
