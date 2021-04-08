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
public class TblQuestionInQuizDTO implements Serializable{
    private String quesId;
    private String quizId;
    private String answer_choose;

    public TblQuestionInQuizDTO() {
    }

    public TblQuestionInQuizDTO(String quesId, String quizId, String answer_choose) {
	this.quesId = quesId;
	this.quizId = quizId;
	this.answer_choose = answer_choose;
    }

    /**
     * @return the quesId
     */
    public String getQuesId() {
	return quesId;
    }

    /**
     * @param quesId the quesId to set
     */
    public void setQuesId(String quesId) {
	this.quesId = quesId;
    }

    /**
     * @return the quizId
     */
    public String getQuizId() {
	return quizId;
    }

    /**
     * @param quizId the quizId to set
     */
    public void setQuizId(String quizId) {
	this.quizId = quizId;
    }

    /**
     * @return the answer_choose
     */
    public String getAnswer_choose() {
	return answer_choose;
    }

    /**
     * @param answer_choose the answer_choose to set
     */
    public void setAnswer_choose(String answer_choose) {
	this.answer_choose = answer_choose;
    }
    
}
