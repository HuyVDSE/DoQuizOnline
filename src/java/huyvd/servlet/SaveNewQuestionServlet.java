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
import java.util.Date;
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
@WebServlet(name = "SaveNewQuestionServlet", urlPatterns = {"/SaveNewQuestionServlet"})
public class SaveNewQuestionServlet extends HttpServlet {
//    private final String CREATE_QUESTION_PAGE = "create-question.jsp";
    private final String CREATE_QUESTION_CONTROLLER = "LoadCreateQuestionServlet";

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
	String url = CREATE_QUESTION_CONTROLLER;

	String questionId = request.getParameter("txtQuestionId");
	String content = request.getParameter("txtContent");
	String optionA = request.getParameter("txtOptionA");
	String optionB = request.getParameter("txtOptionB");
	String optionC = request.getParameter("txtOptionC");
	String optionD = request.getParameter("txtOptionD");
	String correct_option = request.getParameter("cmbCorrectOption");
	String subject = request.getParameter("cmbSubject");
	String status = request.getParameter("cmbStatus");

	boolean foundErr = false;
	QuestionError errors = new QuestionError();

	try {
	    if (optionA.equals(optionB) || optionA.equals(optionC) || optionA.equals(optionD)) {
		foundErr = true;
		errors.setOptionEqualErr("Content option A is same of option B or C or D!!");
	    }

	    if (optionB.equals(optionC) || optionB.equals(optionD)) {
		foundErr = true;
		errors.setOptionEqualErr("Content option B is same of option C or D!!");
	    }

	    if (optionC.equals(optionD)) {
		foundErr = true;
		errors.setOptionEqualErr("Content option C is same of option D!!");
	    }

	    if (foundErr) {
		request.setAttribute("ERRORS", errors);
	    } else {
		TblQuestionDAO dao = new TblQuestionDAO();
		Date currentDate = new Date();
		TblQuestionDTO dto = new TblQuestionDTO(questionId, content, optionA, optionB, optionC, optionD, correct_option, currentDate, status, subject);
		boolean success = dao.insertQuestion(dto);
		if (success) {
		    request.setAttribute("SUCCESS", success);
		}
	    }
	}catch (SQLException e) {
	    log("SaveNewQuestionServlet _ SQLException" + e.getMessage());
	    if (e.getMessage().contains("duplicate")) {
		errors.setQuestionIdExistErr("Question ID is existed!!");
		request.setAttribute("ERRORS", errors);
	    }
	} catch (NamingException e) {
	    log("SaveNewQuestionServlet _ NamingException" + e.getMessage());
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
