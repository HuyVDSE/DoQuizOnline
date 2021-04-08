/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblUser;

import java.io.Serializable;

/**
 *
 * @author BlankSpace
 */
public class UserErrors implements Serializable{
    private String InvalidUserErr;
    private String emailExistedErr;
    private String usernameLengthErr;
    private String emailFormatErr;
    private String passwordLengthErr;
    private String passwordConfirmErr;
    
    /**
     * @return the InvalidUserErr
     */
    public String getInvalidUserErr() {
	return InvalidUserErr;
    }

    /**
     * @param InvalidUserErr the InvalidUserErr to set
     */
    public void setInvalidUserErr(String InvalidUserErr) {
	this.InvalidUserErr = InvalidUserErr;
    }

    /**
     * @return the emailExistedErr
     */
    public String getEmailExistedErr() {
	return emailExistedErr;
    }

    /**
     * @param emailExistedErr the emailExistedErr to set
     */
    public void setEmailExistedErr(String emailExistedErr) {
	this.emailExistedErr = emailExistedErr;
    }

    /**
     * @return the usernameLengthErr
     */
    public String getUsernameLengthErr() {
	return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLengthErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
	this.usernameLengthErr = usernameLengthErr;
    }

    /**
     * @return the emailFormatErr
     */
    public String getEmailFormatErr() {
	return emailFormatErr;
    }

    /**
     * @param emailFormatErr the emailFormatErr to set
     */
    public void setEmailFormatErr(String emailFormatErr) {
	this.emailFormatErr = emailFormatErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
	return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
	this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the passwordConfirmErr
     */
    public String getPasswordConfirmErr() {
	return passwordConfirmErr;
    }

    /**
     * @param passwordConfirmErr the passwordConfirmErr to set
     */
    public void setPasswordConfirmErr(String passwordConfirmErr) {
	this.passwordConfirmErr = passwordConfirmErr;
    }
    
}
