/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.models.Quiz;
import com.mycompany.onlinequiz.storage.DataStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collection;

/**
 *
 * @author kcuar
 */

@Path("quizzes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizResource {
    private static int quizIdCounter = 1;

    // CREATE quiz
    @POST
    public Response createQuiz(Quiz quiz) {
        quiz.setId(quizIdCounter++);
        DataStore.quizzes.put(quiz.getId(), quiz);
        return Response.status(Response.Status.CREATED).entity(quiz).build();
    }

    // GET all quizzes
    @GET
    public Response getAllQuizzes() {
        Collection<Quiz> all = DataStore.quizzes.values();
        return Response.ok(all).build();
    }

    // GET quiz by ID
    @GET
    @Path("{id}")
    public Response getQuiz(@PathParam("id") int id) {
        Quiz quiz = DataStore.quizzes.get(id);

        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Quiz not found\"}")
                    .build();
        }

        return Response.ok(quiz).build();
    }

    // ADD question to quiz
    @POST
    @Path("{id}/add-question/{questionId}")
    public Response addQuestionToQuiz(
            @PathParam("id") int quizId,
            @PathParam("questionId") int questionId) {

        Quiz quiz = DataStore.quizzes.get(quizId);

        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Quiz not found\"}")
                    .build();
        }

        quiz.getQuestionIds().add(questionId);

        return Response.ok(quiz).build();
    }
}
