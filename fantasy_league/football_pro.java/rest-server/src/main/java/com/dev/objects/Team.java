package com.dev.objects;

public class Team {


    private String teamname;
    private int position;
    private int played;
    private int won;
    private int draw;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private int point;


    public Team(String teamname, int position, int played, int won, int draw, int lost, int goalsFor, int goalsAgainst,  int point) {
        this.teamname = teamname;
        this.position = position;
        this.played = played;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint() {
        this.point = point + 3;
    }

    public Team (String teamname){
        this.teamname = teamname;
    }


    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }


    @Override
    public String toString() {
        return "Team{" +
                "teamname='" + teamname + '\'' +
                ", position=" + position +
                ", played=" + played +
                ", won=" + won +
                ", draw=" + draw +
                ", lost=" + lost +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", goalDifference=" + goalDifference +
                ", point=" + point +
                '}';
    }
}
