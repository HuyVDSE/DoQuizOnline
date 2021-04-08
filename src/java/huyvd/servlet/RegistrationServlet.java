/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.servlet;

import huyvd.tblUser.TblUserDAO;
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

/**
 *
 * @author BlankSpace
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    private final String RESIGTRATION_PAGE = "registration.jsp";

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
	String email = request.getParameter("txtEmail");
	String username = request.getParameter("txtUsername");
	String password = request.getParameter("txtPassword");
	String confirmPwd = request.getParameter("txtConfirmPwd");

	UserErrors errors = new UserErrors();
	boolean foundErr = false;

	try {
	    //check user error

	    //check email format
	    if (!email.matches("(\\w*\\d*)+@(\\w+\\.\\w+){1}(\\.\\w+)?")) {
		foundErr = true;
		errors.setEmailFormatErr("Invalid Email!!");
	    }

	    //check username length
	    if (username.length() < 6 || username.length() > 50) {
		foundErr = true;
		errors.setUsernameLengthErr("Username must from 6 - 50 chars!!");
	    }

	    //check password 
	    if (password.length() < 6 || password.length() > 50) {
		foundErr = true;
		errors.setPasswordLengthErr("Password must from 6 - 50 chars!!");
	    } else if (!confirmPwd.equals(password)) {
		foundErr = true;
		errors.setPasswordConfirmErr("Confirm password not matched!!");
	    }

	    //check email exist in DB
	    TblUserDAO dao = new TblUserDAO();
	    boolean isExist = dao.checkEmailExist(email);
	    if (isExist) {
		foundErr = true;
		errors.setEmailExistedErr(email + " is existed!!");
	    }

	    //found error -> notify for user
	    if (foundErr) {
		request.setAttribute("ERRORS", errors);
	    } else {
		//create hash password
		password = HashUtils.createHashString(password);
		//set default properties
		String status = "new";
		String role = "student";
		//insert to DB
		boolean result = dao.createAccount(email, username, password, status, role);

		if (result) {
		    request.setAttribute("SUCCESS", result);
		}
	    }
	} catch (SQLException e) {
	    log("RegistrationServlet_SQLException: " + e.getMessage());
	} catch (NamingException e) {
	    log("RegistrationServlet_NamingException: " + e.getMessage());
	} catch (NoSuchAlgorithmException e) {
	    log("RegistrationServlet_NoSuchAlgorithmException: " + e.getMessage());
	} catch (UnsupportedEncodingException e) {
	    log("RegistrationServlet_UnsupportedEncodingException: " + e.getMessage());
	} finally {
	    request.getRequestDispatcher(RESIGTRATION_PAGE).forward(request, response);
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
