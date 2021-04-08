/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuestion;

import java.io.Serializable;

/**
 *
 * @author BlankSpace
 */
public class QuestionError implements Serializable{
    private String questionIdExistErr;
    private String optionEqualErr;

    /**
     * @return the questionIdExistErr
     */
    public String getQuestionIdExistErr() {
	return questionIdExistErr;
    }

    /**
     * @param questionIdExistErr the questionIdExistErr to set
     */
    public void setQuestionIdExistErr(String questionIdExistErr) {
	this.questionIdExistErr = questionIdExistErr;
    }

    /**
     * @return the optionEqualErr
     */
    public String getOptionEqualErr() {
	return optionEqualErr;
    }

    /**
     * @param optionEqualErr the optionEqualErr to set
     */
    public void setOptionEqualErr(String optionEqualErr) {
	this.optionEqualErr = optionEqualErr;
    }
    
}
