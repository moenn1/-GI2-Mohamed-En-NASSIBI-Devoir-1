package com.servlets;

import com.pojos.GameState;
import com.pojos.User;
import com.services.IDataManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Random;

public class gameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        IDataManagement gameContext = (IDataManagement) getServletContext().getAttribute("gameData");

        GameState gameState = (GameState) session.getAttribute("gameState");

        int diceNumber = Integer.parseInt(request.getParameter("diceNumber"));
        if (gameState.isGameOver()){
            gameState.addMessage("Game Over");
            getServletContext().getRequestDispatcher("/WEB-INF/views/back/home.jsp").forward(request, response);
            return;
        }

        if (gameState.getScoreDice(1) == 6) {
            gameState.setGameOver(true);
            user.setScore(0);
            try {
                gameContext.updateScore(user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            gameState.addMessage("Score: 0, Game Over");
        }




        if (gameState.getUsed(diceNumber-1)) {
            gameState.addMessage("Ce dé a déjà été utilisé, Score: -1, Game Over");
            user.setScore(-1);
            getServletContext().getRequestDispatcher("/WEB-INF/views/back/home.jsp").forward(request, response);
            return;
        } else {
            gameState.setUsed(diceNumber - 1);
            gameState.setScoreDice(diceNumber - 1, new Random().nextInt(7));
            gameState.addMessage("Score du dé " + diceNumber + ": " + gameState.getScoreDice(diceNumber - 1));
        }

        if (gameState.allDicesUsed()) {
            gameState.addMessage("Tous les dés ont été utilisés");
            if (gameState.getScoreDice(0) > gameState.getScoreDice(1) && gameState.getScoreDice(1) > gameState.getScoreDice(2)) {
                user.setScore(gameState.getScoreDice(0) * gameState.getScoreDice(1) * gameState.getScoreDice(2));
                if (user.getScore() > user.getBestScore()) {
                    user.setBestScore(user.getScore());
                    try {
                        gameContext.updateScore(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                gameState.addMessage("Score: " + user.getScore());
            } else if (gameState.getScoreDice(0) < gameState.getScoreDice(1) && gameState.getScoreDice(1) < gameState.getScoreDice(2)) {
                user.setScore(gameState.getScoreDice(0) + gameState.getScoreDice(1) + gameState.getScoreDice(2));
                if (user.getScore() > user.getBestScore()) {
                    user.setBestScore(user.getScore());
                    try {
                        gameContext.updateScore(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                gameState.addMessage("Score: " + user.getScore());
            } else {
                gameState.setGameOver(true);
                user.setScore(0);
                gameState.addMessage("Score: 0, Game Over");
            }
            gameState.setUser(user);
            try {
                gameContext.updateScore(user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/views/back/home.jsp").forward(request, response);
            return;
        }

        session.setAttribute("gameState", gameState);
        getServletContext().getRequestDispatcher("/WEB-INF/views/back/home.jsp").forward(request, response);
        return;
    }
}


