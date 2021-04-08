/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuestionInQuiz;

import huyvd.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author BlankSpace
 */
public class TblQuestionInQuizDAO implements Serializable {

    public boolean insertQuestion(String quizId, List<QuestionInQuizModel> answerList)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "INSERT tblQuestionInQuiz(quizId, questionId, answer_choose) "
			+ "VALUES(?,?,?)";
		pst = cn.prepareStatement(sql);
		
		//set auto-commit to false
		cn.setAutoCommit(false);
		
		int totalRow = 0;
		for (QuestionInQuizModel model : answerList) {
		    pst.setString(1, quizId);
		    pst.setString(2, model.getQuestionId());
		    pst.setString(3, model.getUser_answer());
		    pst.addBatch();
		    totalRow++;
		}
		
		int[] result = pst.executeBatch();
		cn.commit();
		
		if (result.length == totalRow) {
		    return true;
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, null);
	}
	return false;
    }
}
