/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuiz;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author BlankSpace
 */
public class TblQuizDTO implements Serializable{
    private String quizId;
    private String subId;
    private String userId;
    private int num_of_question;
    private int num_of_correct;
    private float score;
    private Date create_date;
    private int time_take;

    public TblQuizDTO() {
    }

    public TblQuizDTO(String quizId, String subId, String userId, int num_of_question, int num_of_correct, float score, Date create_date, int time_take) {
	this.quizId = quizId;
	this.subId = subId;
	this.userId = userId;
	this.num_of_question = num_of_question;
	this.num_of_correct = num_of_correct;
	this.score = score;
	this.create_date = create_date;
	this.time_take = time_take;
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

    /**
     * @return the userId
     */
    public String getUserId() {
	return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
	this.userId = userId;
    }

    /**
     * @return the num_of_question
     */
    public int getNum_of_question() {
	return num_of_question;
    }

    /**
     * @param num_of_question the num_of_question to set
     */
    public void setNum_of_question(int num_of_question) {
	this.num_of_question = num_of_question;
    }

    /**
     * @return the num_of_correct
     */
    public int getNum_of_correct() {
	return num_of_correct;
    }

    /**
     * @param num_of_correct the num_of_correct to set
     */
    public void setNum_of_correct(int num_of_correct) {
	this.num_of_correct = num_of_correct;
    }

    /**
     * @return the score
     */
    public float getScore() {
	return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
	this.score = score;
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
     * @return the time_take
     */
    public int getTime_take() {
	return time_take;
    }

    /**
     * @param time_take the time_take to set
     */
    public void setTime_take(int time_take) {
	this.time_take = time_take;
    }
    
    //create cart id with format ddmmyy-hhmmss-xxxxxx(x is random digit)
    public String generateQuizId() {
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-hhmmss");
	Date currentDate = new Date();

	String datePart = sdf.format(currentDate);
	String randomPart = "";

	for (int i = 0; i < 6; i++) {
	    int randomNum = new Random().nextInt(10);
	    randomPart += randomNum;
	}

	return datePart + "-" + randomPart;
    }
}
