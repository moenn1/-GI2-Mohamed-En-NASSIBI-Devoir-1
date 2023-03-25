package com.services;

import com.pojos.*;
import jakarta.servlet.ServletContext;

import java.util.*;

public class ContextDataManagement implements IDataManagement{

    private ServletContext context;

    private static ContextDataManagement instance;

    private ContextDataManagement(ServletContext context) {
        this.context = context;
    }

    synchronized public static final ContextDataManagement getInstance(ServletContext context) {
        if (instance == null) {
            instance = new ContextDataManagement(context);
        }
        return instance;
    }


    @Override
    public List<User> getAll() throws Exception {
        return (List<User>) context.getAttribute("users");
    }

    public void updateScore(User user) throws Exception {
        List<User> users = getAll();
        for (User it : users) {
            if (it.getLogin().equals(user.getLogin())) {
                it.setBestScore(user.getBestScore());
                break;
            }
        }

    }

    public void insertUser(User user) {
        List<User> userList = (List<User>) context.getAttribute("users");

        userList.add(user);

    }

    public User getUserByLogin(String login) throws Exception {
        List<User> users = getAll();
        for (User it : users) {
            System.out.println(it);
            if (it.getLogin().equals(login)) {
                return it;
            }
        }

        return null;
    }

    @Override
    public User getUserByLastName(String lastName) throws Exception {
        List<User> users = getAll();
        for (User it : users) {
            System.out.println(it);
            if (it.getLastName().equals(lastName)) {
                return it;
            }
        }
        return null;
    }
}
