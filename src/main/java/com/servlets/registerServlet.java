package com.servlets;

import com.pojos.User;
import com.services.IDataManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("create") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorPage = "/WEB-INF/views/error.jsp";
        String okPage = "/WEB-INF/views/operationOK.jsp";
        String loginForm = "/WEB-INF/views/login.jsp";
        ServletContext cntx = getServletContext();

        String nom = request.getParameter("lastName");
        String prenom = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String cfpassword = request.getParameter("confirm-password");

        User user = new User(nom, prenom, login, password, 0, 0);

        IDataManagement gameContext = (IDataManagement) getServletContext().getAttribute("gameData");

        List<String> messages = new ArrayList<String>();

        // On teste si un utilisateur existe avec le login choisit

        try {
            if (gameContext.getUserByLogin(login) != null) {

                // Ajouter des message d'erreur dans la requete
                messages.add("Login already exists");
                request.setAttribute("messages", "messages");

                cntx.getRequestDispatcher(errorPage).forward(request, response);
                return;
            }
            if (!password.equals(cfpassword)) {
                messages.add("Passwords do not match");
                request.setAttribute("messages", "messages");

                cntx.getRequestDispatcher(errorPage).forward(request, response);
                return;
            }
            gameContext.insertUser(user);

            messages.add("User successfully added");
            request.setAttribute("messages", messages);
            cntx.getRequestDispatcher(loginForm).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
