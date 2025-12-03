/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.api;

import com.mycompany.onlinequiz.models.Question;
import com.mycompany.onlinequiz.models.Quiz;
import com.mycompany.onlinequiz.models.Submission;
import com.mycompany.onlinequiz.storage.DataStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author kcuar
 */


@Path("submit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubmissionResource {
    @POST
    public Response submitQuiz(Submission submission) {

        Quiz quiz = DataStore.quizzes.get(submission.getQuizId());

        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Quiz not found\"}")
                    .build();
        }

        int score = 0;

        // Loop through question IDs
        for (int questionId : quiz.getQuestionIds()) {

            Question q = QuestionResource.getQuestionByIdStatic(questionId);

            if (q != null) {
                String correct = q.getAnswer();
                String userAnswer = submission.getAnswers().get(questionId);

                if (correct != null && correct.equals(userAnswer)) {
                    score++;
                }
            }
        }

        submission.setScore(score);
        DataStore.submissions.add(submission); // save result

        return Response.ok(submission).build();
    }
}
