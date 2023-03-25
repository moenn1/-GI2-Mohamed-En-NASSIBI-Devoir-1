package com.listeners;

import com.services.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.*;
import com.pojos.*;

@WebListener
public class Initializer implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public Initializer() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        String type = ctx.getInitParameter("type_stockage");

        IDataManagement gameData = null;
        try {
            gameData = DataManagementFactory.getFactory(type, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ctx.setAttribute("gameData", gameData);

        List<User> userList = new ArrayList<User>();

        ctx.setAttribute("users", userList);

    }

}
