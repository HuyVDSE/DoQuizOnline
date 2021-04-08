/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblSubject;

import huyvd.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author BlankSpace
 */
public class TblSubjectDAO implements Serializable {
    private int num_of_question;
    private int time_take;

    public int getNum_of_question() {
	return num_of_question;
    }

    public int getTime_take() {
	return time_take;
    }

    public List<TblSubjectDTO> loadSubject() throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<TblSubjectDTO> subjectList = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT subId, subName FROM tblSubject ";
		pst = cn.prepareStatement(sql);
		rs = pst.executeQuery();

		while (rs.next()) {
		    TblSubjectDTO dto = new TblSubjectDTO();
		    dto.setSubId(rs.getString("subId"));
		    dto.setSubName(rs.getString("subName"));

		    if (subjectList == null) {
			subjectList = new ArrayList<>();
		    }

		    subjectList.add(dto);
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return subjectList;
    }

    public boolean updateQuizSetting(String subId, String num_of_ques, String time_take)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "UPDATE tblSubject "
			+ "SET num_of_question = ?, time_take = ? "
			+ "WHERE subId = ? ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, num_of_ques);
		pst.setString(2, time_take);
		pst.setString(3, subId);
		int row = pst.executeUpdate();

		if (row > 0) {
		    return true;
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, null);
	}
	return false;
    }

    public void loadQuizSetting(String subjectId) throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT num_of_question, time_take "
			+ "FROM tblSubject "
			+ "WHERE subId = ? ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, subjectId);
		
		rs = pst.executeQuery();
		if (rs.next()) {
		    num_of_question = rs.getInt("num_of_question");
		    time_take = rs.getInt("time_take");
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
    }
}
