package ru.itis.dao;

import ru.itis.models.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class OwnersDaoJdbcImpl implements Dao {

    // language=SQL
    private static final String FIND_QUERY =
            "SELECT owner_id, name, city, age" +
                    " FROM test.owners" +
                    " WHERE owner_id = ?";

    // language=SQL
    private static final String FIND_ALL_QUERY =
            "SELECT owner_id, name, city, age" +
                    " FROM test.owners";

    private Connection connection;

    public OwnersDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public Owner find(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            result.next();
            return new Owner(result.getString("name"), result.getInt("owner_id"),
                    result.getString("city"), result.getInt("age"));
        } catch (SQLException e) {
            System.out.print("not found");
            throw new IllegalArgumentException(e);
        }
    }

    public List<Owner> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);

            ResultSet result = statement.executeQuery();
            List<Owner> owners = new ArrayList<Owner>();
            while (result.next()){
                owners.add(new Owner(result.getString("name"), result.getInt("owner_id"),
                        result.getString("city"), result.getInt("age")));
            }
            return owners;
        } catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(int id) {

    }

    public void update(Object obj) {

    }

    public void add(Object obj) {

    }
}
