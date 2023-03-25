package com.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;

        HttpSession session = rq.getSession();

        if (session.getAttribute("user") == null) {

            rq.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;

        } else {
            chain.doFilter(request, response);
        }

    }
}
