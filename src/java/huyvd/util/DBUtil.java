/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author BlankSpace
 */
public class DBUtil implements Serializable{
    public static Connection makeConnection()
	    throws SQLException, NamingException {
	//1. get current context
	Context context = new InitialContext();
	//2. get server context
	Context tomcatContext = (Context) context.lookup("java:comp/env");
	//3. get data source
	DataSource ds = (DataSource) tomcatContext.lookup("QuizBank");
	//4. make connection
	Connection cn = ds.getConnection();
	
	return cn;
    }
    
    public static void closeConnection(Connection cn, PreparedStatement pst, ResultSet rs) 
	throws SQLException{
	if (rs != null) {
	    rs.close();
	}
	if (pst != null) {
	    pst.close();
	}
	if (cn != null) {
	    cn.close();
	}
    }
}
