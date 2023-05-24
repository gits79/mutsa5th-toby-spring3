package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {
    Map<String, String> env = getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );
        return conn;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );
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
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );
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
        UserDao ud = new UserDao();
        User user = new User();
        user.setId("2");
        user.setName("jungjae");
        user.setPassword("1111");
//        ud.add(user);

        User selectUser = ud.get("1");
        System.out.printf("%s %s %s",selectUser.getId(),selectUser.getName(),selectUser.getPassword());
    }

}
