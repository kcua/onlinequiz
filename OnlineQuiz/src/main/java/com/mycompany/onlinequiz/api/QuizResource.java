/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.models.Quiz;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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


@Path("quizzes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizResource {
    public static Map<Integer, Quiz> quizzes = new HashMap<>();
    public static int idCounter = 1;

    // Anyone can create quizzes
    @POST
    public Response create(Quiz quiz) {
        quiz.setId(idCounter++);
        quizzes.put(quiz.getId(), quiz);
        return Response.ok(quiz).build();
    }

    // Anyone can list quizzes
    @GET
    public Response getAll() {
        return Response.ok(quizzes.values()).build();
    }

    // Anyone can get a quiz
    @GET
    @Path("{id}")
    public Response get(@PathParam("id") int id) {
        Quiz q = quizzes.get(id);

        if (q == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\":\"Quiz not found\"}")
                           .build();
        }

        return Response.ok(q).build();
    }

    // Add questions without checking if question exists
    @POST
    @Path("{quizId}/add/{questionId}")
    public Response addQuestion(
            @PathParam("quizId") int quizId,
            @PathParam("questionId") int questionId
    ) {
        Quiz quiz = quizzes.get(quizId);

        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\":\"Quiz not found\"}")
                           .build();
        }

        quiz.getQuestionIds().add(questionId); // No validation
        return Response.ok(quiz).build();
    }
}
