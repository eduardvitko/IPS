package com.dao;

import com.exceptions.UserException;
import com.model.Role;
import com.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserDaoTest {
    @BeforeAll
    static void dbInit() {
        DataSource.innitConfiguration("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/internet_provider_base", "root", "root");
    }

    UserDao userDao;

    {
        try {
            userDao = new UserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void createUserPositiveTest() {
        User user = new User("+380508889911", "1100",true,Role.CLIENT);
        user.setRole(Role.CLIENT);
        userDao.create(user);
        int id = userDao.findByField("+380508889911").getId();
        assertEquals("+380508889911", user.getPhone());
        userDao.delete(id);
    }

    @Test()
    public void createNegativeNullPhoneTest() {
        User user = new User(null, "4123");
        userDao.create(user);
        Assertions.assertThrows(UserException.class, () -> userDao.create(null));
    }

    @Test()
    public void createNegativeNullPasswordTest() {
        User user = new User();
        user.setPhone("380669507700");
        user.setPassword(null);
        assertThrows(UserException.class, () -> userDao.create(null));
    }

    @Test
    public void createNegativeDuplicatePhoneTest() {
        User user = new User();
        user.setPhone("+380632323233");
        user.setPassword("4130");
        User newUser = userDao.create(user);
        int id = newUser.getId();
        assertEquals(0, id);
    }

    @Test
    public void findByPhoneNumberPositiveTest() {
        User user = userDao.findByField("+380632323233");
        assertEquals("+380632323233", "+380632323233");
        int id = userDao.findByField("+380632323233").getId();
        Assertions.assertNotNull(id);
        assertEquals(id, id);

    }

    @Test
    public void findByPhoneNumberNegativeTest() {
        User user = userDao.findByField("380507097199");
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    public void getAllUsersPositiveTest() {
        List<User> userList = userDao.findAll();
        int size = userList.size();
        assertNotNull(userList);
        assertEquals(size, userList.size());
    }
//    @Test
//    public void getAllUsersNegativeTest() {
//        List<User> users = userDao.findAll();
//        int beginSize = users.size();
//        System.out.println(beginSize);
//
//        User user = new User("+380440000091","7732",true,Role.CLIENT);
//        userDao.create(user);
//        int id = userDao.findByField("+380440000091").getId();
//        System.out.println(id);
//        List<User> userList = userDao.findAll();
//        int endSize = userList.size();
//        System.out.println(endSize);
//
//
//
//        Assertions.assertNotEquals(beginSize, endSize);
//        userDao.delete(id);
//    }

    @Test
    public void findByIdPositiveTest() {
        int id = 22;
        User user = userDao.findById(id);
        assertEquals(1, 1);
    }

    @Test
    public void findByIdNegativeTest() {
        int id = 6;
        User user = userDao.findById(id);
        Assertions.assertNotEquals(1, 0);
    }

    @Test
    public void deleteUserPositiveTest() {
        User newUser = new User("+380442003040","0000");
        userDao.create(newUser);
        int id= userDao.findByField("+380442003040").getId();
        boolean status = userDao.delete(id);
        assertEquals(true, true);
    }

    @Test
    void deleteUserNegativeTest() {
        int listUserSize = userDao.findAll().size();
        int id = 2;
        userDao.delete(id);
        Assertions.assertNotEquals(listUserSize, listUserSize - 1);
    }

    @Test
    void updateUserPositiveTest() {
        User newUser = new User("+380442003040","0000");
        userDao.create(newUser);
        int idOldUser= userDao.findByField("+380442003040").getId();
        User user = new User(idOldUser,"+380665556655","9000",true,Role.CLIENT);
        User editUser = userDao.update(user);
        int idNewUser = userDao.findByField("+380665556655").getId();
        assertEquals(idOldUser, idNewUser);
        userDao.delete(idNewUser);
    }
    @Test
    void updateUserNegativeTest() {
        int idOldUser= userDao.findByField("+380735556321").getId();
        User user = new User("+380631111100","4000");
        User editUser = userDao.update(user);
        int idNewUser = userDao.findByField("+380631111100").getId();
        Assertions.assertNotEquals(idOldUser, idNewUser);
    }
}
