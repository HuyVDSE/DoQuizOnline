/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuiz;

import huyvd.util.DBUtil;
import huyvd.util.DateHandle;
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
public class TblQuizDAO implements Serializable {

    public boolean createQuiz(TblQuizDTO quiz) throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "INSERT tblQuiz(quizId, userId, subId, num_of_question, num_of_correct, score, create_date, time_take)\n"
			+ "VALUES(?,?,?,?,?,?,?,?)";
		pst = cn.prepareStatement(sql);
		pst.setString(1, quiz.getQuizId());
		pst.setString(2, quiz.getUserId());
		pst.setString(3, quiz.getSubId());
		pst.setInt(4, quiz.getNum_of_question());
		pst.setInt(5, quiz.getNum_of_correct());
		pst.setFloat(6, quiz.getScore());
		pst.setString(7, DateHandle.convertDateToString(quiz.getCreate_date()));
		pst.setInt(8, quiz.getTime_take());
		
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
}
