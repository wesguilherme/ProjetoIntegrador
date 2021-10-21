package com.projetointegrador.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection conn;

    public static Connection getConection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pi_frescos", "root", "");
        return conn;
    }
}
