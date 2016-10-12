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
        System.out.println();

        for (Owner owner : ownersDaoJdbc.getAll()){
            System.out.println(owner);
        }

        ownersDaoJdbc.delete(2);

        ownersDaoJdbc.update(new Owner("Vasiliy", 1, "Moscow", 25));

        ownersDaoJdbc.add(new Owner("John", "New York", 43));

        System.out.println();

        for (Owner owner : ownersDaoJdbc.getAll()){
            System.out.println(owner);
        }
    }
}
