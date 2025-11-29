/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.db.Database;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kcuar
 */
public class LoginResource {
     // This is a vulnerabke login method. it uses plain pass + no hashing
    @POST
    public Response login(String body) {
        try {
            // Example request: "username=admin&password=1234"
            String[] parts = body.split("&");
            String username = parts[0].split("=")[1];
            String password = parts[1].split("=")[1];

            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();

            //SQL Injection vulnerability
            String sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return Response.ok("Login successful").build();
            } else {
                return Response.status(401).entity("Invalid credentials").build();
            }

        } catch (Exception e) {
            return Response.status(500).entity("Server Error").build();
        }
    }
}