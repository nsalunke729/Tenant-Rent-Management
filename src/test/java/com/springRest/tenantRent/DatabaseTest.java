package com.springRest.tenantRent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        
        try {
            // It's no longer necessary to explicitly load the driver class with Class.forName
            String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=master;trustServerCertificate=true;integratedSecurity=false;encrypt=false";  
            Connection con = DriverManager.getConnection(connectionUrl,"user1","user");  
            if (con != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
