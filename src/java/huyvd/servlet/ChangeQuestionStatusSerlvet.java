/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.servlet;

import huyvd.tblQuestion.TblQuestionDAO;
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
@WebServlet(name = "ChangeQuestionStatusSerlvet", urlPatterns = {"/ChangeQuestionStatusSerlvet"})
public class ChangeQuestionStatusSerlvet extends HttpServlet {

    private final String ADMIN_HOME_CONTROLLER = "adminHome";

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

	String quesId = request.getParameter("id");
	String status = request.getParameter("status");

	String content = request.getParameter("txtSearch");
	String subject = request.getParameter("cmbSubject");
	String searchStatus = request.getParameter("cmbStatus");

	try {
	    if (status.equals("active")) {
		status = "deactive";
	    } else {
		status = "active";
	    }
	    TblQuestionDAO dao = new TblQuestionDAO();
	    boolean result = dao.changeQuestionStatus(quesId, status);
	    if (result) {
		url = "search-question?txtSearch=" + content
			+ "&cmbSubject=" + subject
			+ "&cmbStatus=" + searchStatus;
	    }
	} catch (SQLException e) {
	    log("ChangeQuestionStatusSerlvet_SQLException: " + e.getMessage());
	} catch (NamingException e) {
	    log("ChangeQuestionStatusSerlvet_NamingException: " + e.getMessage());
	} finally {
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
