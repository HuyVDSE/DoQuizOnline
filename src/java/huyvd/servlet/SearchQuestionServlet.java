/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.servlet;

import huyvd.tblQuestion.TblQuestionDAO;
import huyvd.tblQuestion.TblQuestionDTO;
import huyvd.tblSubject.TblSubjectDAO;
import huyvd.tblSubject.TblSubjectDTO;
import huyvd.util.PageCalculate;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "SearchQuestionServlet", urlPatterns = {"/SearchQuestionServlet"})
public class SearchQuestionServlet extends HttpServlet {

    private final String ADMIN_HOME_PAGE = "admin.jsp";

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
	String url = ADMIN_HOME_PAGE;

	String content = request.getParameter("txtSearch");
	String subject = request.getParameter("cmbSubject");
	String status = request.getParameter("cmbStatus");
	int page = 1;//default page
	String pageString = request.getParameter("page");
	
	try {
	    if (pageString != null) {
		page = Integer.parseInt(pageString);
	    }
	    
	    //do search
	    TblQuestionDAO questionDAO = new TblQuestionDAO();
	    List<TblQuestionDTO> questionList = questionDAO.doSearch(content, subject, status, page);
	    request.setAttribute("QUESTION_LIST", questionList);
	    
	    //load subject for search combo box
	    TblSubjectDAO subjectDAO = new TblSubjectDAO();
	    List<TblSubjectDTO> subjectList = subjectDAO.loadSubject();
	    request.setAttribute("SUBJECT_LIST", subjectList);
	    
	    int totalItems = questionDAO.getTotalResult(content, subject, status);
	    int totalPages = PageCalculate.calculateNumOfPage(totalItems, 5);
	    request.setAttribute("TOTAL_PAGES", totalPages);
	} catch (SQLException e) {
	    log("SearchQuestionServlet_SQLException: " + e.getMessage());
	} catch (NamingException e) {
	    log("SearchQuestionServlet_NamingException: " + e.getMessage());
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
