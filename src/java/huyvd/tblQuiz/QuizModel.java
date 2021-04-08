/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuiz;

import huyvd.tblQuestion.TblQuestionDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author BlankSpace
 */
public class QuizModel implements Serializable{
    private Date startTime;
    private Date endTime;
    private String subjectId;
    private List<TblQuestionDTO> questionList;
    private int timeTake;
    private Date createDate;

    public QuizModel() {
    }

    public QuizModel(Date startTime, Date endTime, String subjectId, 
	    List<TblQuestionDTO> questionList, int timeTake, Date createDate) {
	this.startTime = startTime;
	this.endTime = endTime;
	this.subjectId = subjectId;
	this.questionList = questionList;
	this.timeTake = timeTake;
	this.createDate = createDate;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
	return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
	return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    /**
     * @return the subjectId
     */
    public String getSubjectId() {
	return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
	this.subjectId = subjectId;
    }

    /**
     * @return the questionList
     */
    public List<TblQuestionDTO> getQuestionList() {
	return questionList;
    }

    /**
     * @param questionList the questionList to set
     */
    public void setQuestionList(List<TblQuestionDTO> questionList) {
	this.questionList = questionList;
    }

    /**
     * @return the timeTake
     */
    public int getTimeTake() {
	return timeTake;
    }

    /**
     * @param timeTake the timeTake to set
     */
    public void setTimeTake(int timeTake) {
	this.timeTake = timeTake;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
	return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }
    
}
