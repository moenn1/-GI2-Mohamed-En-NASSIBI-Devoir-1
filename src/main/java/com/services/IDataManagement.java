package com.services;

import com.pojos.*;

import java.sql.SQLException;
import java.util.List;

public interface IDataManagement {
        public List<User> getAll() throws Exception;

        public void updateScore(User user) throws Exception;

        public void insertUser(User user) throws Exception;

        public User getUserByLogin(String login) throws Exception;

        public User getUserByLastName(String lastName) throws Exception;
}
