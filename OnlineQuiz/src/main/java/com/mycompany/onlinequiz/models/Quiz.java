/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlinequiz.models;

import java.util.List;

/**
 *
 * @author kcuar
 */
public class Quiz {
        private int id;
    private List<Integer> questionIds;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Integer> getQuestionIds() { return questionIds; }
    public void setQuestionIds(List<Integer> questionIds) { this.questionIds = questionIds; }
}
