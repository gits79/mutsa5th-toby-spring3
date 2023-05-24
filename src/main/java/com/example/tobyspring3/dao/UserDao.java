package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {

//    SimpleConnectionMaker connectionMaker = new SimpleConnectionMaker();
    ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

//        Connection conn = connectionMaker.makeNewConnection();
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id,name,password) " +
                "values (?,?,?)");
        pstmt.setString(1,user.getId());
        pstmt.setString(2,user.getName());
        pstmt.setString(3,user.getPassword());
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {

//        Connection conn = connectionMaker.makeNewConnection();
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // rs -> pstmt -> conn 순으로 종료
        rs.close();
        pstmt.close();
        conn.close();


        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker cm = new DConnectionMaker();
        UserDao ud = new UserDao(cm);
        User user = new User();
        user.setId("2");
        user.setName("jungjae");
        user.setPassword("1111");
//        ud.add(user);

        User selectUser = ud.get("2");
        System.out.printf("%s %s %s",selectUser.getId(),selectUser.getName(),selectUser.getPassword());
    }

}
