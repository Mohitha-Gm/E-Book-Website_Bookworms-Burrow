package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class DBUtil {
    private static final String DB_DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
    private static final String DB_USERNAME="root";
    private static final String DB_PASSWORD="Subhash@7432";
    private static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/subhash";

    private static Connection connection = null;
    static{
        try {
            Class.forName(DB_DRIVER_CLASS);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}