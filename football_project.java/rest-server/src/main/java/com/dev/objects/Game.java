package com.dev.objects;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class Game {



    private String teamA;
    private String teamB;

    private int goalsA;
    private int goalsB;
    private int live;

    public Game(String teamA, String teamB, int goalsA, int goalsB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.goalsA = goalsA;
        this.goalsB = goalsB;

    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getGoalsA() {
        return goalsA;
    }

    public void setGoalsA(int goalsA) {
        this.goalsA = goalsA;
    }

    public int getGoalsB() {
        return goalsB;
    }

    public void setGoalsB(int goalsB) {
        this.goalsB = goalsB;
    }

    public int isLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }
}
