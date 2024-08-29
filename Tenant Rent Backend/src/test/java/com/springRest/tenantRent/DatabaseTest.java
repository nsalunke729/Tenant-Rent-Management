package com.springRest.tenantRent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        
        try {
            // It's no longer necessary to explicitly load the driver class with Class.forName
            String connectionUrl = "jdbc:mysql://niranjantest.mysql.database.azure.com:3306/tenant-rent?useSSL=true&requireSSL=false";  
            Connection con = DriverManager.getConnection(connectionUrl,"niranjan","password");  
            if (con != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
