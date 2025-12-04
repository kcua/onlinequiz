OnlineQuiz - Secure vs Vulnerable Web App.

This is for my Secure Application Programming implementing online quiz system in Jakarta EE, showcasing secure coding practices vs intentionally vulnerable code.

This repo contains 2 fully functional versions of the same app.

Branch                  |             Description

secure-version          |     this version implements secure coding, API keys, hashed passwords, input validation, and defence against SQL injection, XSS & Sensitive Data Exposure
vulnerable-version      |     this version contains intentionally insecure code: SQL injection, XSS, and sensitive data exposure
main                    |     general branch, not used for assessment.



AUTHENTICATION 
-User Registration
-Login with hashed passwords (SHA-256)
-API key generation
-Protected endpoints with an API key filter (Authorisation: Bearer <APIKEY>)


QUESTIONS API
-Creates, Reads, Updates, Deletes, Quiz Questions
-Each questions has id, text, choices, and answer


QUIZ API
-Creates Quizzes
-Add Questions to a quiz
-Retrieve Quizzes and linked questions



SUBMISSIONS API
-Submit Quiz Answers
-Score is automatically calculated
-Store User Submissions



TECHNOLOGIES USED
-Jakarta EE10
-JAX-RS (RESTful Web Services_
-Glassfish 7 Server
-Java 17
-SQLite3 (secure-version)
-Maven
-Postman for testing


INSTALLATION AND SETUP
-JDK 17
-Glassfish 7
-Netbeans 19 (recommended)
-SQLite3 (for secured-version)
-Git


---->HOW TO RUN THE APP? <----

1. CLONE THE REPOSITORY


   '''bash
   git clone https://github.com/kcua/onlinequiz.git
cd onlinequiz


2. CHOOSE A BRANCH

   for secured-version    ----> git checkout secure-version
   for vulnerable-versio  ----> git checkout vulnerable-version


3. OPEN THE PROJECT IN NETBEANS
   
   Go to file > open project\
   Select the folder OnlineQuiz/
   Build the project


4. DEPLOY TO GLASSFISH

   Right-click the project
   Click RUN
   The app will be available at **http://localhost:8080/OnlineQuiz/api/**


5. API Usage Guide

   **To register User**

   POST http://localhost:8080/OnlineQuiz/api/users/register
   Content-Type: application/json

   {
   "username": "test",
   "passwordHash": "testpass123"
   }

  **To login**

  POST  http://localhost:8080/OnlineQuiz/api/users/register

{
  "username": "test",
  "passwordHash": "password123"
}



**You will get this response**
  {
  "message": "Login successful",
  "apiKey": "<YOUR_API_KEY>"
}

6. **YOU NEED TO ADD THE API KEY**

Header
Authorization : Bearer <APIKEY>


7. QUESTION ENDPOINTS

Method	|             Endpoint          |	      Description

GET	    |           /api/questions	    |     Get all questions
POST	  |           /api/questions	    |     Create a new question
GET	    |           /api/questions/{id} |   	Get a question by ID
PUT	    |           /api/questions/{id} |	    Update a question
DELETE	|           /api/questions/{id}	|     Delete a question


8. QUIZZES ENDPOINTS


Method	|             Endpoint                         |	      Description

POST	  |             /api/quizzes	                   |      Create a new quiz
POST	  |    /api/quizzes/{quizId}/add/{questionId}    |	   Add question to quiz
GET	    |          /api/quizzes/{id}	                 |     Get quiz with questions


9. SUBMISSION ENDPOINTS


Method	|             Endpoint                         |	      Description

POST    |        	/api/submissions                     |	  Submit answers for scoring
GET     |     	/api/submissions/user/{username}       |	    View user submissions

    

   




























