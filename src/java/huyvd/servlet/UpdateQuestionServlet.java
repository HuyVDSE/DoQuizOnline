/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.servlet;

import huyvd.tblQuestion.QuestionError;
import huyvd.tblQuestion.TblQuestionDAO;
import huyvd.tblQuestion.TblQuestionDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BlankSpace
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {
    private final String ADMIN_HOME_CONTROLLER = "LoadAdminHomeServlet";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	String url = ADMIN_HOME_CONTROLLER;
	
	String questionId = request.getParameter("txtQuestionId");
	String content = request.getParameter("txtContent");
	String optionA = request.getParameter("txtOptionA");
	String optionB = request.getParameter("txtOptionB");
	String optionC = request.getParameter("txtOptionC");
	String optionD = request.getParameter("txtOptionD");
	String correct_option = request.getParameter("cmbCorrectOption");
	String subject = request.getParameter("cmbSubject");
	
	boolean foundErr = false;
	QuestionError errors = new QuestionError();
	
	try {
	    TblQuestionDAO dao = new TblQuestionDAO();
	    boolean exist = dao.checkQuestionExistInQuiz(questionId);
	    if (exist) {
		foundErr = true;
		errors.setQuestionIdExistErr("Can't update question: " + questionId 
			+ ". Because this question has been existed in quiz!!");
	    }
	    
	    if (foundErr) {
		request.setAttribute("ERRORS", errors);
	    } else {
		TblQuestionDTO question = new TblQuestionDTO();
		question.setQuestionId(questionId);
		question.setContent(content);
		question.setOptionA(optionA);
		question.setOptionB(optionB);
		question.setOptionC(optionC);
		question.setOptionD(optionD);
		question.setCorrect_answer(correct_option);
		question.setSubId(subject);
		boolean success = dao.updateQuestion(question);
		if (success) {
		    request.setAttribute("SUCCESS", success);
		    request.setAttribute("QUES_ID", questionId);
		}
	    }
	} catch (SQLException e) {
	    log("UpdateQuestionServlet _ SQLException: " + e.getMessage());
	} catch (NamingException e) {
	    log("UpdateQuestionServlet _ NamingException: " + e.getMessage());
	} finally {
	    request.getRequestDispatcher(url).forward(request, response);
	}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
