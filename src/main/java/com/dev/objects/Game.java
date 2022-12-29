package com.dev.objects;

import org.springframework.stereotype.Component;


public class Game {

    private Team teamA;
    private Team teamB;

    private Score score;

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score liveGame (Team teamA , Team teamB){





        return score;
    }
}
