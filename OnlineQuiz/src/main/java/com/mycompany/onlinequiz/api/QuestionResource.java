/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.models.Question;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
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

    // In-memory database
    private static Map<Integer, Question> questions = new HashMap<>();
    private static int idCounter = 1;

    // GET all questions
    @GET
    public Response getAllQuestions() {
        return Response.ok(questions.values()).build();
    }

    // POST new question
    @POST
    public Response addQuestion(Question q) {
        q.setId(idCounter++);
        questions.put(q.getId(), q);
        return Response.status(Response.Status.CREATED).entity(q).build();
    }

    // GET question by ID
    @GET
    @Path("{id}")
    public Response getQuestion(@PathParam("id") int id) {
        Question q = questions.get(id);
        if (q == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Question not found")
                           .build();
        }
        return Response.ok(q).build();
    }

    // PUT update question
    @PUT
    @Path("{id}")
    public Response updateQuestion(@PathParam("id") int id, Question updated) {
        Question existing = questions.get(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Question not found")
                           .build();
        }

        existing.setText(updated.getText());
        existing.setChoices(updated.getChoices());
        existing.setAnswer(updated.getAnswer());

        return Response.ok(existing).build();
    }

    // DELETE question
    @DELETE
    @Path("{id}")
    public Response deleteQuestion(@PathParam("id") int id) {
        Question removed = questions.remove(id);
        if (removed == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Question not found")
                           .build();
        }
        return Response.ok("Question deleted").build();
    }
    
    public static Question getQuestionByIdStatic(int id) {
    return questions.get(id);
}
}

