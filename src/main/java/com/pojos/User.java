package com.pojos;

public class User {
    private String lastName;

    private String name;

    private String login;

    private String password;

    private double score;

    private double bestScore;


    public User() {
    }

    public User(String lastName, String name, String login, String password, double score, double bestScore) {
        this.lastName = lastName;
        this.name = name;
        this.login = login;
        this.password = password;
        this.score = score;
        this.bestScore = bestScore;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getBestScore() {
        return bestScore;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }


    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", bestScore=" + bestScore +
                '}';
    }
}
