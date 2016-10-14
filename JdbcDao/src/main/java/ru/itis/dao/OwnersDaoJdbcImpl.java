package ru.itis.dao;

import ru.itis.models.JdbcModel;
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

    //language = SQL
    private static final String DELETE_BY_ID_QUERY =
            "DELETE FROM test.owners" +
                    " WHERE owner_id = ?";

    //language = SQL;
    private static final String UPDATE_QUERY =
            "UPDATE test.owners " +
                    " SET name = ?, city = ?, age = ? " +
                    " WHERE owner_id = ?";

    //language = SQL
    private static final String INSERT_QUERY =
            "INSERT INTO test.owners (name, city, age)" +
                    " VALUES (?, ?, ?)";

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
            while (result.next()) {
                owners.add(new Owner(result.getString("name"), result.getInt("owner_id"),
                        result.getString("city"), result.getInt("age")));
            }
            return owners;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings("all")
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(JdbcModel obj) {
        try {
            Owner owner = (Owner) obj;
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, owner.getName());
            statement.setString(2, owner.getCity());
            statement.setInt(3, owner.getAge());
            statement.setInt(4, owner.getId());
            statement.execute();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void add(JdbcModel obj) {
        try {
            Owner owner = (Owner) obj;
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, owner.getName());
            statement.setString(2, owner.getCity());
            statement.setInt(3, owner.getAge());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
