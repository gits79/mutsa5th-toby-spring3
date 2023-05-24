package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("3");
        user.setName("changyu");
        user.setPassword("1212");
//        userDao.add(user);

        User selectUser = userDao.get("3");
        System.out.printf("%s %s %s",selectUser.getId(),selectUser.getName(),selectUser.getPassword());

    }
}
