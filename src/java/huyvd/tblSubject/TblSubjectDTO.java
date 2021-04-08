/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblSubject;

import java.io.Serializable;

/**
 *
 * @author BlankSpace
 */
public class TblSubjectDTO implements Serializable{
    private String subId;
    private String subName;

    public TblSubjectDTO() {
    }

    public TblSubjectDTO(String subId, String subName) {
	this.subId = subId;
	this.subName = subName;
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
     * @return the subName
     */
    public String getSubName() {
	return subName;
    }

    /**
     * @param subName the subName to set
     */
    public void setSubName(String subName) {
	this.subName = subName;
    }
    
}
