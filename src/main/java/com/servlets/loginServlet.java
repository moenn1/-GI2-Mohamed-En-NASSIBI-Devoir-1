package com.servlets;

import com.pojos.GameState;
import com.pojos.User;
import com.services.IDataManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");


        List<String> messages = new ArrayList<String>();

        IDataManagement gameContext = (IDataManagement) getServletContext().getAttribute("gameData");

        try{
            User user = gameContext.getUserByLogin(login);
            if (user != null) {
            if (user.getPassword() != null && user.getPassword().equals(password)) {

                GameState gameState = new GameState(user);
                request.getSession().setAttribute("gameState", gameState);
                request.getSession().setAttribute("user", user);

                getServletContext().getRequestDispatcher("/WEB-INF/views/back/home.jsp").forward(request, response);
                return;
            } else {

                messages.add("Incorrect login or password");
                request.setAttribute("messages", messages);
                getServletContext().getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(request, response);
                return;
            }

        } else {
            messages.add("Incorrect login or password");
            request.setAttribute("messages", messages);
            getServletContext().getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(request, response);
            return;
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }
}
