/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.tblQuestion;

import huyvd.util.DBUtil;
import huyvd.util.DateHandle;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author BlankSpace
 */
public class TblQuestionDAO implements Serializable {
    private Map<String, String> questionMap;

    public Map<String, String> getQuestionMap() {
	return questionMap;
    }
    
    public List<TblQuestionDTO> loadQuestion() throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<TblQuestionDTO> questionList = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, subName\n"
			+ "FROM tblQuestion q join tblSubject s on q.subId = s.subId "
			+ "GROUP BY questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, subName\n"
			+ "ORDER BY create_date DESC "
			+ "OFFSET 0 ROWS "
			+ "FETCH NEXT 5 ROWS ONLY ";
		pst = cn.prepareStatement(sql);
		rs = pst.executeQuery();

		while (rs.next()) {
		    TblQuestionDTO dto = new TblQuestionDTO();
		    dto.setQuestionId(rs.getString("questionId"));
		    dto.setContent(rs.getString("content"));
		    dto.setOptionA(rs.getString("optionA"));
		    dto.setOptionB(rs.getString("optionB"));
		    dto.setOptionC(rs.getString("optionC"));
		    dto.setOptionD(rs.getString("optionD"));
		    dto.setCorrect_answer(rs.getString("correct_answer"));
		    dto.setCreate_date(rs.getDate("create_date"));
		    dto.setStatus(rs.getString("status"));
		    dto.setSubId(rs.getString("subName"));

		    if (questionList == null) {
			questionList = new ArrayList<>();
		    }

		    questionList.add(dto);
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return questionList;
    }

    public int getTotalQuestion() throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int total = 0;
	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT COUNT(questionId) as total\n"
			+ "FROM tblQuestion";
		pst = cn.prepareStatement(sql);
		rs = pst.executeQuery();

		if (rs.next()) {
		    total = rs.getInt("total");
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return total;
    }

    public boolean changeQuestionStatus(String questionId, String status)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "UPDATE tblQuestion "
			+ "SET status = ? "
			+ "WHERE questionId = ? ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, status);
		pst.setString(2, questionId);

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

    public List<TblQuestionDTO> doSearch(String content, String subject, String status, int page)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<TblQuestionDTO> questionList = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, s.subName "
			+ "FROM tblQuestion q, tblSubject s "
			+ "WHERE q.subId = s.subId ";

		//checking searchContent exist
		if (!content.trim().isEmpty()) {
		    sql += "AND content like '%" + content + "%'";
		}

		//checking subject string exist
		if (!subject.trim().isEmpty()) {
		    sql += "AND s.subId = " + subject;
		}

		//checking status string exist
		if (!status.trim().isEmpty()) {
		    sql += "AND status = '" + status + "'";
		}

		sql += "GROUP BY questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, s.subName "
			+ "ORDER BY create_date DESC "
			+ "OFFSET ? ROWS "
			+ "FETCH NEXT 5 ROWS ONLY ";
		pst = cn.prepareStatement(sql);
		pst.setInt(1, (page - 1) * 5);

		//execute query
		rs = pst.executeQuery();
		while (rs.next()) {
		    TblQuestionDTO dto = new TblQuestionDTO();
		    dto.setQuestionId(rs.getString("questionId"));
		    dto.setContent(rs.getString("content"));
		    dto.setOptionA(rs.getString("optionA"));
		    dto.setOptionB(rs.getString("optionB"));
		    dto.setOptionC(rs.getString("optionC"));
		    dto.setOptionD(rs.getString("optionD"));
		    dto.setCorrect_answer(rs.getString("correct_answer"));
		    dto.setCreate_date(rs.getDate("create_date"));
		    dto.setStatus(rs.getString("status"));
		    dto.setSubId(rs.getString("subName"));

		    if (questionList == null) {
			questionList = new ArrayList<>();
		    }

		    questionList.add(dto);
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return questionList;
    }

    public int getTotalResult(String content, String subject, String status)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	int result = 0;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT COUNT(questionId) as total\n"
			+ "FROM tblQuestion q, tblSubject s\n"
			+ "WHERE q.subId = s.subId ";

		//checking searchContent exist
		if (!content.trim().isEmpty()) {
		    sql += "AND content like '%" + content + "%'";
		}

		//checking subject string exist
		if (!subject.trim().isEmpty()) {
		    sql += "AND s.subId = " + subject;
		}

		//checking status string exist
		if (!status.trim().isEmpty()) {
		    sql += "AND status = '" + status + "'";
		}

		pst = cn.prepareStatement(sql);
		rs = pst.executeQuery();
		if (rs.next()) {
		    result = rs.getInt("total");
		}
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return result;
    }

    public boolean insertQuestion(TblQuestionDTO question) throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "INSERT tblQuestion(questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, subId) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?) ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, question.getQuestionId());
		pst.setString(2, question.getContent());
		pst.setString(3, question.getOptionA());
		pst.setString(4, question.getOptionB());
		pst.setString(5, question.getOptionC());
		pst.setString(6, question.getOptionD());
		pst.setString(7, question.getCorrect_answer());
		pst.setString(8, DateHandle.convertDateToString(question.getCreate_date()));
		pst.setString(9, question.getStatus());
		pst.setString(10, question.getSubId());

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

    public boolean checkQuestionExistInQuiz(String questionId)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT q.questionId "
			+ "FROM tblQuestion q join tblQuestionInQuiz qi on q.questionId = qi.questionId "
			+ "WHERE q.questionId = ? ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, questionId);
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

    public boolean updateQuestion(TblQuestionDTO question)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "UPDATE tblQuestion "
			+ "SET content = ?, optionA = ?, optionB = ?, optionC = ?, optionD = ?, correct_answer = ?, subId = ? "
			+ "WHERE questionId = ? ";
		pst = cn.prepareStatement(sql);
		pst.setString(1, question.getContent());
		pst.setString(2, question.getOptionA());
		pst.setString(3, question.getOptionB());
		pst.setString(4, question.getOptionC());
		pst.setString(5, question.getOptionD());
		pst.setString(6, question.getCorrect_answer());
		pst.setString(7, question.getSubId());
		pst.setString(8, question.getQuestionId());

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

    public List<TblQuestionDTO> getRandomQuestion(String subjectId, int num_of_question)
	    throws SQLException, NamingException {
	Connection cn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	List<TblQuestionDTO> questionList = null;

	try {
	    cn = DBUtil.makeConnection();
	    if (cn != null) {
		String sql = "SELECT TOP(?) questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, s.subName "
			+ "FROM tblQuestion q join tblSubject s on q.subId = s.subId "
			+ "WHERE status = 'active' AND s.subId = ? "
			+ "ORDER BY NEWID() ";
		pst = cn.prepareStatement(sql);
		pst.setInt(1, num_of_question);
		pst.setString(2, subjectId);
		
		rs = pst.executeQuery();
		while (rs.next()) {		    
		    TblQuestionDTO dto = new TblQuestionDTO();
		    dto.setQuestionId(rs.getString("questionId"));
		    dto.setContent(rs.getString("content"));
		    dto.setOptionA(rs.getString("optionA"));
		    dto.setOptionB(rs.getString("optionB"));
		    dto.setOptionC(rs.getString("optionC"));
		    dto.setOptionD(rs.getString("optionD"));
		    dto.setCorrect_answer(rs.getString("correct_answer"));
		    dto.setCreate_date(rs.getDate("create_date"));
		    dto.setStatus(rs.getString("status"));
		    dto.setSubId(rs.getString("subName"));

		    if (questionList == null) {
			questionList = new ArrayList<>();
		    }

		    questionList.add(dto);
		    
		    if (this.questionMap == null) {
			this.questionMap = new HashMap<>();
		    }
		    
		    this.questionMap.put(rs.getString("questionId"), rs.getString("correct_answer"));
		}
	  
	    }
	} finally {
	    DBUtil.closeConnection(cn, pst, rs);
	}
	return questionList;
    }
}
