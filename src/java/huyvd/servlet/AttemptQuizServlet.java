/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.servlet;

import huyvd.tblQuestion.TblQuestionDAO;
import huyvd.tblQuestion.TblQuestionDTO;
import huyvd.tblQuiz.QuizModel;
import huyvd.tblSubject.TblSubjectDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "AttemptQuizServlet", urlPatterns = {"/AttemptQuizServlet"})
public class AttemptQuizServlet extends HttpServlet {

    private final String DO_QUIZ_PAGE = "do-quiz";

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
	String url = DO_QUIZ_PAGE;
	String subjectId = request.getParameter("subjectId");
	String subName = request.getParameter("subName");

	try {
	    //load quiz setting
	    TblSubjectDAO subjectDAO = new TblSubjectDAO();
	    subjectDAO.loadQuizSetting(subjectId);
	    int num_of_ques = subjectDAO.getNum_of_question();
	    int time_take = subjectDAO.getTime_take();

	    //load question
	    TblQuestionDAO questionDAO = new TblQuestionDAO();
	    List<TblQuestionDTO> questionList = questionDAO.getRandomQuestion(subjectId, num_of_ques);
	    //A map for storing question id and correct answer
	    Map<String, String> questionMap = questionDAO.getQuestionMap();

	    //create quiz model
	    QuizModel quiz = new QuizModel();
	    Calendar time = Calendar.getInstance();
	    quiz.setStartTime(time.getTime());
	    time.add(Calendar.MINUTE, time_take);
	    quiz.setEndTime(time.getTime());
	    quiz.setQuestionList(questionList);
	    quiz.setSubjectId(subjectId);
	    quiz.setTimeTake(time_take);
	    quiz.setCreateDate(new Date());

	    //create quiz session
	    HttpSession session = request.getSession(false);
	    if (session != null) {
		session.setAttribute("QUIZ", quiz);
		session.setAttribute("ANSWER_OF_QUESTION_LIST", questionMap);
		session.setAttribute("TOTAL_PAGES", num_of_ques);
		session.setAttribute("SUB_NAME", subName);
	    }
	} catch (SQLException e) {
	    log("AttemptQuizServlet _ SQLException: " + e.getMessage());
	} catch (NamingException e) {
	    log("AttemptQuizServlet _ NamingException: " + e.getMessage());
	} finally {
//	    request.getRequestDispatcher(url).forward(request, response);
	    response.sendRedirect(url);
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
