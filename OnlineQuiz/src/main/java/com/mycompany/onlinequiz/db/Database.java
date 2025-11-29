/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kcuar
 */
public class Database {
                                                                                       
    private static final String URL = "jdbc:sqlite:C:/DB/onlinequiz.db"; // Vulnerable: Hardcoded database path

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
