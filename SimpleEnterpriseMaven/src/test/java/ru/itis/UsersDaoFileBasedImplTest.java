package ru.itis;

import org.junit.Before;
import org.junit.Test;
import ru.itis.dao.UsersDaoFileBasedImpl;

/**
 * Created by admin on 06.10.2016.
 */
public class UsersDaoFileBasedImplTest {

    private UsersDaoFileBasedImpl usersDao;

    @Before
    public void setUp() throws Exception {
        usersDao = new UsersDaoFileBasedImpl("C:\\Users\\Ayrat\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\",
                "Users.txt", "UsersOriginal.txt");
    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void save(){

    }

    @Test
    public void get(){

    }

    @Test
    public void delete(){

    }

}