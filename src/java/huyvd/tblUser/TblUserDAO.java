/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblUser;

import huyvd.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author BlankSpace
 */
public class TblUserDAO implements Serializable {

    public TblUserDTO checkLogin(String userId, String password)
	    throws SQLException, NamingException {
	TblUserDTO dto = null;
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT userId, username, status, role "
			+ "FROM tblUser "
			+ "WHERE userId=? AND password=? ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, userId);
		pst.setString(2, password);

		rs = pst.executeQuery();

		if (rs.next()) {
		    String curUserId = rs.getString("userId");
		    String username = rs.getString("username");
		    String status = rs.getString("status");
		    String role = rs.getString("role");
		    if (curUserId.equals(userId)) {
			dto = new TblUserDTO(curUserId, username, status, role);
		    }
		}//end if rs
	    }//end if connection
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return dto;
    }

    public boolean checkEmailExist(String email)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT userId FROM tblUser WHERE userId = ? ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, email);

		rs = pst.executeQuery();
		if (rs.next()) {
		    return true;
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return false;
    }

    public boolean createAccount(String email, String username, String password,
	    String status, String role)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "INSERT tblUser(userId, username, password, role, status) "
			+ "VALUES(?,?,?,?,?) ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, email);
		pst.setString(2, username);
		pst.setString(3, password);
		pst.setString(4, role);
		pst.setString(5, status);

		int row = pst.executeUpdate();
		if (row > 0) {
		    return true;
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return false;
    }

}
