package ru.khannanovayrat.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by KFU-user on 28.10.2016.
 */
public class CarJdbcDaoImpl {

    private Connection connection;


    public CarJdbcDaoImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
