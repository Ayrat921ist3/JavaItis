package ru.khannanovayrat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.khannanovayrat.dao.CarJdbcDaoImpl;
import ru.khannanovayrat.dao.UserJdbcUserDaoImpl;
import ru.khannanovayrat.service.CarService;
import ru.khannanovayrat.service.UserService;

import javax.sql.DataSource;

/**
 * Created by Ayrat on 09.11.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.khannanovayrat")
public class WebConfig extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver configureViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/authorized_users");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("Vypapos88");
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
}
