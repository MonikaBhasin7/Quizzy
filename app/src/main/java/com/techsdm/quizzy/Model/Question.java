package com.techsdm.quizzy.Model;

/**
 * Created by Monika Bhasin on 25-07-2018.
 */

public class Question {

    public String question_name;
    public String question_answer;
    public String clue1;
    public String clue2;
    public String clue3;
    public String clue4;

    public Question() {
    }

    public Question(String question_name, String question_answer, String clue1, String clue2, String clue3, String clue4) {
        this.question_name = question_name;
        this.question_answer = question_answer;
        this.clue1 = clue1;
        this.clue2 = clue2;
        this.clue3 = clue3;
        this.clue4 = clue4;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    public String getClue1() {
        return clue1;
    }

    public void setClue1(String clue1) {
        this.clue1 = clue1;
    }

    public String getClue2() {
        return clue2;
    }

    public void setClue2(String clue2) {
        this.clue2 = clue2;
    }

    public String getClue3() {
        return clue3;
    }

    public void setClue3(String clue3) {
        this.clue3 = clue3;
    }

    public String getClue4() {
        return clue4;
    }

    public void setClue4(String clue4) {
        this.clue4 = clue4;
    }
}
