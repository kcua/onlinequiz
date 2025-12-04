/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.models.Question;
import com.mycompany.onlinequiz.models.Quiz;
import com.mycompany.onlinequiz.models.Submission;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kcuar
 * 
 * 
 * 
 */



@Path("submit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubmissionResource {
     @POST
    public Response submit(Submission s) {

        Quiz quiz = QuizResource.quizzes.get(s.getQuizId());

        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"error\":\"Quiz not found\"}")
                           .build();
        }

        // Insecure auto-grading
        int score = 0;
        for (int qid : quiz.getQuestionIds()) {
            Question q = QuestionResource.questions.get(qid);
            if (q != null) {
                String correct = q.getAnswer();
                String userAns = s.getAnswers().get(qid);
                if (correct != null && correct.equals(userAns)) {
                    score++;
                }
            }
        }

        s.setScore(score);

        return Response.ok(s).build();
    }
}
