/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.filters;

import com.mycompany.onlinequiz.storage.DataStore;
import com.mycompany.onlinequiz.api.UserResource;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author kcuar
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {

        // Allow registration and login without an API key
        String path = requestContext.getUriInfo().getPath();

        if (path.startsWith("users/register") || path.startsWith("users/login")) {
            return;
        }

        // Read Authorization header
        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Missing or invalid Authorization header\"}")
                        .build()
            );
            return;
        }

        String apiKey = authHeader.substring("Bearer ".length());

        // Validate API key using DataStore
        boolean exists = DataStore.users.values()
                .stream()
                .anyMatch(user -> apiKey.equals(user.getApiKey()));

        if (!exists) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Invalid API key\"}")
                        .build()
            );
        }
    }
}