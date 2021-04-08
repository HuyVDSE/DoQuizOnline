/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.servlet;

import huyvd.tblQuestionInQuiz.QuestionInQuizModel;
import huyvd.tblQuestionInQuiz.TblQuestionInQuizDAO;
import huyvd.tblQuiz.QuizModel;
import huyvd.tblQuiz.TblQuizDAO;
import huyvd.tblQuiz.TblQuizDTO;
import huyvd.tblUser.TblUserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BlankSpace
 */
@WebServlet(name = "SubmitQuizServlet", urlPatterns = {"/SubmitQuizServlet"})
public class SubmitQuizServlet extends HttpServlet {

    private final String RESULT_PAGE = "result.jsp";
    private final String ERROR_PAGE = "error.jsp";

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
	String url = ERROR_PAGE;

	try {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
		List<QuestionInQuizModel> answerList = new ArrayList<>();
		int totalQuestions = (int) session.getAttribute("TOTAL_PAGES");
		//get list question and answer of user
		for (int i = 1; i <= totalQuestions; i++) {
		    String questionId = request.getParameter("questionId" + i);
		    String userOption = request.getParameter("txtAnswer" + i);
		    if (userOption == null) {
			userOption = "";
		    }
		    QuestionInQuizModel model = new QuestionInQuizModel(questionId, userOption);
		    answerList.add(model);
		}

		//calculate score and number of correct answer
		int totalCorrect = 0;
		float totalScore = 10;
		float scoreOfQuestion = 10 / totalQuestions;
		Map<String, String> questionMap
			= (Map<String, String>) session.getAttribute("ANSWER_OF_QUESTION_LIST");
		for (QuestionInQuizModel model : answerList) {
		    String correctAnswer = questionMap.get(model.getQuestionId());
		    if (model.getUser_answer().equals(correctAnswer)) {
			totalCorrect++;
		    } else {
			totalScore = totalScore - scoreOfQuestion;
		    }
		}

		TblUserDTO curUser = (TblUserDTO) session.getAttribute("ACCOUNT");
		QuizModel model = (QuizModel) session.getAttribute("QUIZ");
		
		TblQuizDTO dto = new TblQuizDTO();
		String quizId = dto.generateQuizId();
		dto.setQuizId(quizId);
		dto.setUserId(curUser.getUserId());
		dto.setSubId(model.getSubjectId());
		dto.setNum_of_question(totalQuestions);
		dto.setNum_of_correct(totalCorrect);
		dto.setScore(totalScore);
		dto.setTime_take(model.getTimeTake());
		dto.setCreate_date(model.getCreateDate());
		TblQuizDAO quizDAO = new TblQuizDAO();
		boolean success = quizDAO.createQuiz(dto);
		if (success) {
		    TblQuestionInQuizDAO questionInQuizDAO = new TblQuestionInQuizDAO();
		    success = questionInQuizDAO.insertQuestion(quizId, answerList);
		    if (success) {
			url = RESULT_PAGE;
			request.setAttribute("RESULT", dto);
			String subName = (String) session.getAttribute("SUB_NAME");
			request.setAttribute("SUB_NAME", subName);
			session.removeAttribute("QUIZ");
		    }
		}
	    }
	} catch (SQLException e) {
	    log("SubmitQuizServlet _ SQLException: " + e.getMessage());
	} catch (NamingException e) {
	    log("SubmitQuizServlet _ NamingException: " + e.getMessage());
	}  finally {
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
