package net.javaguidies.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javaguidies.registration.dao.LoginDao;
import net.javaguidies.registration.model.LoginEmployee;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispetcher=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispetcher.forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setUsername(username);
        loginEmployee.setPassword(password);

        try {
            if (loginDao.validate(loginEmployee)) {
            	HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect("RestaurantMainPage.jsp");
                RequestDispatcher dispetcher=request.getRequestDispatcher("/WEB-INF/views/RestaurantMainPage.jsp");
        		dispetcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                response.sendRedirect("login.jsp");
                RequestDispatcher dispetcher=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        		dispetcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}