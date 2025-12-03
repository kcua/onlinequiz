/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.storage;

import com.mycompany.onlinequiz.models.User;
import com.mycompany.onlinequiz.models.Quiz;
import com.mycompany.onlinequiz.models.Submission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author kcuar
 */
public class DataStore {
        public static Map<String, User> users = new HashMap<>();
        public static Map<Integer, Quiz> quizzes = new HashMap<>();
        public static List<Submission> submissions = new ArrayList<>();
}
