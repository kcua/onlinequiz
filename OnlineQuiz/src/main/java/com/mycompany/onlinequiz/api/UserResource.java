/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.models.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kcuar
 */

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    public static Map<String, User> users = new HashMap<>();
    public static int idCounter = 1;

    @POST
    @Path("register")
    public Response register(User newUser) {
        newUser.setId(idCounter++);
        users.put(newUser.getUsername(), newUser); // stores plaintext password

        return Response.ok(newUser).build(); // returns password openly
    }

    @POST
    @Path("login")
    public Response login(User loginRequest) {
        User u = users.get(loginRequest.getUsername());

        if (u == null || !u.getPasswordHash().equals(loginRequest.getPasswordHash())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Invalid credentials\"}")
                    .build();
        }

        return Response.ok("{\"message\":\"Login successful (INSECURE)\"}").build();
    }

    @GET
    @Path("all")
    public Response getAllUsers() {
        return Response.ok(users.values()).build(); // exposes passwords
    }
}
