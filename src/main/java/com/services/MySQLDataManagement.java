package com.services;

import com.pojos.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDataManagement implements IDataManagement{

    private static MySQLDataManagement instance;

    private String url = "jdbc:mysql://localhost:3306/jeudedes";

    //Connect to the database
    private MySQLDataManagement() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    synchronized public static final MySQLDataManagement getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySQLDataManagement();
        }
        return instance;
    }

    //Function to return connection
    public Connection connect(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    @Override
    public List<User> getAll() throws SQLException {
        Connection con = null;
        List<User> list = new ArrayList<User>();
            try {
                con = connect();
                PreparedStatement st = con.prepareStatement("select * from users");

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    User p = new User(rs.getString("lastName"), rs.getString("name"), rs.getString("login"), rs.getString("password"), rs.getDouble("score"), rs.getDouble("bestScore"));
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        return list;
    }

    @Override
    public void updateScore(User user) throws SQLException {
        try {
            Connection con = connect();
            PreparedStatement st = con.prepareStatement("update users set score = ? where login = ?");
            st.setDouble(1, user.getScore());
            st.setString(2, user.getLogin());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void insertUser(User user) throws SQLException {
        try {
            Connection con = connect();
            PreparedStatement st =con.prepareStatement("insert into users (lastName, name, login, password, score, bestScore) values (?,?,?,?,?,?)");
            st.setString(1, user.getLastName());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getPassword());
            st.setDouble(5, user.getScore());
            st.setDouble(6, user.getBestScore());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        User user = null;
        try {
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement("select * from users where login = ?");
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("lastName"), rs.getString("name"), rs.getString("login"), rs.getString("password"), rs.getDouble("score"), rs.getDouble("bestScore"));
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return user;
    }

    @Override
    public User getUserByLastName(String lastName) throws Exception{
        User user = null;
        try {
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement("select * from users where lastName = ?");
            pstmt.setString(1, lastName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("lastName"), rs.getString("name"), rs.getString("login"), rs.getString("password"), rs.getDouble("score"), rs.getDouble("bestScore"));
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return user;
    }
}
