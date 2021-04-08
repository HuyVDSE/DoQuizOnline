/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuestion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author BlankSpace
 */
public class TblQuestionDTO implements Serializable{
    private String questionId;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correct_answer;
    private Date create_date;
    private String status;
    private String subId;

    public TblQuestionDTO() {
    }

    public TblQuestionDTO(String questionId, String content, String optionA, 
	    String optionB, String optionC, String optionD, String correct_answer, 
	    Date create_date, String status, String subId) {
	this.questionId = questionId;
	this.content = content;
	this.optionA = optionA;
	this.optionB = optionB;
	this.optionC = optionC;
	this.optionD = optionD;
	this.correct_answer = correct_answer;
	this.create_date = create_date;
	this.status = status;
	this.subId = subId;
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
     * @return the content
     */
    public String getContent() {
	return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
	this.content = content;
    }

    /**
     * @return the optionA
     */
    public String getOptionA() {
	return optionA;
    }

    /**
     * @param optionA the optionA to set
     */
    public void setOptionA(String optionA) {
	this.optionA = optionA;
    }

    /**
     * @return the optionB
     */
    public String getOptionB() {
	return optionB;
    }

    /**
     * @param optionB the optionB to set
     */
    public void setOptionB(String optionB) {
	this.optionB = optionB;
    }

    /**
     * @return the optionC
     */
    public String getOptionC() {
	return optionC;
    }

    /**
     * @param optionC the optionC to set
     */
    public void setOptionC(String optionC) {
	this.optionC = optionC;
    }

    /**
     * @return the optionD
     */
    public String getOptionD() {
	return optionD;
    }

    /**
     * @param optionD the optionD to set
     */
    public void setOptionD(String optionD) {
	this.optionD = optionD;
    }

    /**
     * @return the correct_answer
     */
    public String getCorrect_answer() {
	return correct_answer;
    }

    /**
     * @param correct_answer the correct_answer to set
     */
    public void setCorrect_answer(String correct_answer) {
	this.correct_answer = correct_answer;
    }

    /**
     * @return the create_date
     */
    public Date getCreate_date() {
	return create_date;
    }

    /**
     * @param create_date the create_date to set
     */
    public void setCreate_date(Date create_date) {
	this.create_date = create_date;
    }

    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * @return the subId
     */
    public String getSubId() {
	return subId;
    }

    /**
     * @param subId the subId to set
     */
    public void setSubId(String subId) {
	this.subId = subId;
    }
    
    
}
