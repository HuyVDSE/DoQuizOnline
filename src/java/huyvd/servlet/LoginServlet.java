/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.servlet;

import huyvd.tblUser.TblUserDAO;
import huyvd.tblUser.TblUserDTO;
import huyvd.tblUser.UserErrors;
import huyvd.util.HashUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String ERROR_PAGE = "login.jsp";
    private final String USER_PAGE_CONTROLLER = "userHome";
    private final String ADMIN_PAGE_CONTROLLER = "adminHome";

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

	String userId = request.getParameter("txtEmail");
	String password = request.getParameter("txtPassword");

	boolean loginSuccess = false;

	try {
	    //convert to SHA-256 string
	    password = HashUtils.createHashString(password);
	    //call DAO
	    TblUserDAO dao = new TblUserDAO();
	    TblUserDTO curUser = dao.checkLogin(userId, password);

	    if (curUser != null) {
		String role = curUser.getRole();

		switch (role) {
		    case "student":
			url = USER_PAGE_CONTROLLER;
			break;
		    case "admin":
			url = ADMIN_PAGE_CONTROLLER;
			break;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("ACCOUNT", curUser);

		loginSuccess = true;
	    } else {
		UserErrors errors = new UserErrors();
		errors.setInvalidUserErr("Invalid Email or Password!!");
		request.setAttribute("ERRORS", errors);
	    }
	} catch (SQLException e) {
	    log("LoginServlet_SQLException: " + e.getMessage());
	} catch (NamingException e) {
	    log("LoginServlet_NamingException: " + e.getMessage());
	} catch (UnsupportedEncodingException e) {
	    log("LoginServlet_UnsupportedEncodingException: " + e.getMessage());
	} catch (NoSuchAlgorithmException e) {
	    log("LoginServlet_NoSuchAlgorithmException: " + e.getMessage());
	} finally {
	    if (loginSuccess) {
		response.sendRedirect(url);
	    } else {
		request.getRequestDispatcher(url).forward(request, response);
	    }
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
