/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.storage.DataStore;
import com.mycompany.onlinequiz.models.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author kcuar
 */

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static int idCounter = 1;

    // Register new user
    @POST
    @Path("register")
    public Response register(User newUser) {

        // Check if username already exists
        if (DataStore.users.containsKey(newUser.getUsername())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Username already exists\"}")
                    .build();
        }

        try {
            String passwordHash = hash(newUser.getPasswordHash());
            String apiKey = generateApiKey();

            newUser.setId(idCounter++);
            newUser.setPasswordHash(passwordHash);
            newUser.setApiKey(apiKey);

            // Save user in shared DataStore
            DataStore.users.put(newUser.getUsername(), newUser);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"User registered successfully\",\"apiKey\":\"" + apiKey + "\"}")
                    .build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\":\"Server error\"}")
                    .build();
        }
    }

    // Login
    @POST
    @Path("login")
    public Response login(User loginRequest) {

        User user = DataStore.users.get(loginRequest.getUsername());

        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Invalid username or password\"}")
                    .build();
        }

        try {
            String hashed = hash(loginRequest.getPasswordHash());

            if (!hashed.equals(user.getPasswordHash())) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Invalid username or password\"}")
                        .build();
            }

            return Response.ok("{\"message\":\"Login successful\",\"apiKey\":\"" + user.getApiKey() + "\"}")
                    .build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\":\"Server error\"}")
                    .build();
        }
    }

    // Hash password using SHA-256
    private String hash(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encoded = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(encoded);
    }

    // Generate API key
    private String generateApiKey() {
        byte[] bytes = new byte[24];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
