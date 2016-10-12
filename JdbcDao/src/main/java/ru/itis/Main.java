package ru.itis;

import ru.itis.dao.OwnersDaoJdbcImpl;
import ru.itis.models.Owner;

import java.sql.Connection;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        Connection connection = JdbcConnection.getInstance().getConnection();

        OwnersDaoJdbcImpl ownersDaoJdbc = new OwnersDaoJdbcImpl(connection);

        System.out.println(ownersDaoJdbc.find(1));

        for (Owner owner : ownersDaoJdbc.getAll()){
            System.out.println(owner);
        }
    }
}
