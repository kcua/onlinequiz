/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.models.Question;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kcuar
 */

@Path("questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionResource {
    // Completely insecure public data store
    public static Map<Integer, Question> questions = new HashMap<>();
    public static int idCounter = 1;

    // Anyone can see all questions
    @GET
    public Response getAll() {
        return Response.ok(questions.values()).build();
    }

    // Anyone can add a question
    @POST
    public Response addQuestion(Question q) {
        q.setId(idCounter++);
        questions.put(q.getId(), q);
        return Response.ok(q).build();
    }

    // Anyone can get a question
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") int id) {
        Question q = questions.get(id);

        if (q == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\":\"Not found\"}")
                           .build();
        }

        return Response.ok(q).build();
    }

    // Anyone can update ANY question
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Question newQ) {

        questions.put(id, newQ); // no validation
        newQ.setId(id);
        return Response.ok(newQ).build();
    }

    // Anyone can delete ALL questions
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        questions.remove(id);
        return Response.ok("{\"message\":\"Deleted (insecure)\"}").build();
    }
}
