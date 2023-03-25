package com.pojos;

import java.util.*;

public class GameState {

    private User user;

    private boolean gameOver = false;

    private List<String> messages = new ArrayList<String>();
    int [] scoreDice = new int[3];

    boolean [] used = new boolean[3];

    public void reinit() {
        gameOver = false;
        messages = new ArrayList<String>();
        user.setScore(0);
        used = new boolean[3];
        int [] scoreDice = new int[3];
    }


    public void setScoreDice(int i, int score) {
        scoreDice[i] = score;
    }

    public boolean allDicesUsed(){
        return used[0] && used[1] && used[2];
    }

    public int getScoreDice(int i){
        return scoreDice[i];
    }

    public void setUsed(int i) {
        used[i] = true;
    }

    public boolean getUsed(int i){
        return used[i];
    }

    public String toString() {
        return "messages=" + messages;
    }

    public void addMessage(String ms) {
        messages.add(ms);
    }

    public GameState(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}