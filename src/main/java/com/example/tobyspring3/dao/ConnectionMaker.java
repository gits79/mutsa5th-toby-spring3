package com.example.tobyspring3.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    // public 안 붙여도 됨
    Connection makeConnection() throws ClassNotFoundException, SQLException;


}
