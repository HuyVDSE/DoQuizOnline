/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author BlankSpace
 */
public class ContextListener implements ServletContextListener {

    private final String FUNCTIONS_FILE = "/WEB-INF/functions.txt";
    private final String STUDENT_FUNCTION_FILE = "/WEB-INF/student_function.txt";
    private final String ADMIN_FUNCTION_FILE = "/WEB-INF/admin_function.txt";
    private final String GUEST_FUNCTION_FILE = "/WEB-INF/guest_function.txt";
    private final String FRONTEND_RESOURCE_FILE = "/WEB-INF/frontend_resource.txt";

    public Map<String, String> loadFunctionsMap(String path) {
	Map<String, String> functionsMap = new HashMap<>();
	FileReader fr = null;
	BufferedReader br = null;

	try {
	    fr = new FileReader(path);
	    br = new BufferedReader(fr);

	    while (br.ready()) {
		//read line and split to create key and value for mapping function
		String[] arr = br.readLine().split("=");
		functionsMap.put(arr[0], arr[1]);
	    }
	} catch (IOException e) {
	    System.out.println("IOException: " + e.getMessage());
	} finally {
	    try {
		if (br != null) {
		    br.close();
		}
		if (fr != null) {
		    fr.close();
		}
	    } catch (IOException e) {
		System.out.println("Close fileReader-bufferReader IOException: " + e.getMessage());
	    }
	}
	return functionsMap;
    }

    public List<String> loadFunctionsList(String path) {
	List<String> functionsList = new ArrayList<>();
	FileReader fr = null;
	BufferedReader br = null;
	
	try {
	    fr = new FileReader(path);
	    br = new BufferedReader(fr);

	    while (br.ready()) {
		//add every line to list
		functionsList.add(br.readLine());
	    }
	} catch (IOException e) {
	    System.out.println("IOException: " + e.getMessage());
	} finally {
	    try {
		if (br != null) {
		    br.close();
		}
		if (fr != null) {
		    fr.close();
		}
	    } catch (IOException e) {
		System.out.println("Close fileReader-bufferReader IOException: " + e.getMessage());
	    }
	}
	return functionsList;
    }
    @Override
    public void contextInitialized(ServletContextEvent event) {
	ServletContext context = event.getServletContext();
	String realPath = context.getRealPath("/");
	
	Map<String, String> functionsMap = loadFunctionsMap(realPath + FUNCTIONS_FILE);
	if (functionsMap != null) {
	    context.setAttribute("FUNCTIONS_MAP", functionsMap);
	}
	
	List<String> studentFunction = loadFunctionsList(realPath + STUDENT_FUNCTION_FILE);
	if (studentFunction != null) {
	    context.setAttribute("STUDENT_FUNCTION", studentFunction);
	}
	
	List<String> guestFunction = loadFunctionsList(realPath + GUEST_FUNCTION_FILE);
	if (guestFunction != null) {
	    context.setAttribute("GUEST_FUNCTION", guestFunction);
	}
	
	List<String> adminFunction = loadFunctionsList(realPath + ADMIN_FUNCTION_FILE);
	if (adminFunction != null) {
	    context.setAttribute("ADMIN_FUNCTION", adminFunction);
	}
	List<String> frontendResource = loadFunctionsList(realPath + FRONTEND_RESOURCE_FILE);
	if (frontendResource != null) {
	    context.setAttribute("FRONTEND_RESOURCE", frontendResource);
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
