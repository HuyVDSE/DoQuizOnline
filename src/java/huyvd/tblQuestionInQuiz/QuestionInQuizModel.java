/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuestionInQuiz;

import java.io.Serializable;

/**
 *
 * @author BlankSpace
 */
public class QuestionInQuizModel implements Serializable{
    private String questionId;
    private String user_answer;

    public QuestionInQuizModel() {
    }

    public QuestionInQuizModel(String questionId, String user_answer) {
	this.questionId = questionId;
	this.user_answer = user_answer;
    }

    /**
     * @return the questionId
     */
    public String getQuestionId() {
	return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(String questionId) {
	this.questionId = questionId;
    }

    /**
     * @return the user_answer
     */
    public String getUser_answer() {
	return user_answer;
    }

    /**
     * @param user_answer the user_answer to set
     */
    public void setUser_answer(String user_answer) {
	this.user_answer = user_answer;
    }
    
}
