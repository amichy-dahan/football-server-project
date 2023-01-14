package com.dev.controllers;

import com.dev.objects.Game;
import com.dev.objects.Team;
import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
public class TestController {


    private List<Game> liveGames = new ArrayList<>();
    List<Team> allTeams = new ArrayList<>();
    List<Team> teamChoose = new ArrayList<>();



    @Autowired
    public Utils utils;


    @Autowired
    private Persist persist;

    @PostConstruct
    public void init () {
    }

    @RequestMapping(value = "/get-all-users", method = {RequestMethod.GET, RequestMethod.POST})
    public List<User> getAllUsers () {
        List<User> allUsers = persist.getAllUsers();
        return allUsers;
    }
    @RequestMapping(value = "/get-all-teams", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Team> getAllTeams () {
        allTeams = persist.getNamesTeam();
        Team t1 = new Team("Arsenal", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t2 = new Team("Manchester City", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t3 = new Team("Newcastle United", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t4 = new Team("Tottenham Hotspur", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t5 = new Team("Manchester United", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t6 = new Team("Liverpool", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t7 = new Team("Brighton & Hove Albion", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t8 = new Team("Chelsea", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t9 = new Team("Fulham", 0, 0, 0, 0, 0, 0, 0,0);
        Team t10 = new Team("Brentford", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t11 = new Team("Crystal Palace", 0, 0, 0, 0, 0, 0, 0, 0);
        Team t12 = new Team("Aston Villa", 0, 0, 0, 0, 0, 0, 0, 0);
        teamChoose.add(t1);
        teamChoose.add(t2);
        teamChoose.add(t3);
        teamChoose.add(t4);
        teamChoose.add(t5);
        teamChoose.add(t6);
        teamChoose.add(t7);
        teamChoose.add(t8);
        teamChoose.add(t9);
        teamChoose.add(t10);
        teamChoose.add(t11);
        teamChoose.add(t12);
        return allTeams;
    }

    @RequestMapping(value = "/get-all-games", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Game> getAllGames () {
        liveGames = persist.getTeamsInLiveGame();
        return liveGames;
    }


    @RequestMapping(value = "/sign-in", method = {RequestMethod.GET, RequestMethod.POST})
    public BasicResponse userExist (String username1, String password1) {
        BasicResponse success = null;
        System.out.println(username1);
        System.out.println(password1);
        String token = createHash(username1, password1); //fsf454
        System.out.println(token);
        token = persist.getUserByCreds(username1, token);
        System.out.println(token);
        if (token == null){
             success = new BasicResponse(false);
            System.out.println(success.isSuccess());
        }else {
            User user = persist.getUserByToken(token);
            success = new BasicResponse(true);

            System.out.println(true);
        }

        return success;
    }
    @RequestMapping(value = "/newGame", method = {RequestMethod.GET, RequestMethod.POST})
    public Game newGameLive(String teamA , String teamB , int goalsA , int goalsB , boolean live) {
       Game newGame = null;

       int isAlive = 0;
       if (persist.liveGameAvailable(teamA ,teamB)){
           newGame = new Game(teamA ,teamB ,goalsA,goalsB);
           persist.addGame(teamA, teamB,goalsA,goalsB,live);
       }else {
           System.out.println("game already in live");
       }
       return newGame;
    }

//    @RequestMapping(value = "/finishGame", method = {RequestMethod.GET, RequestMethod.POST})
//    public Game finishGame (String teamA , String teamB , int goalsA , int goalsB , boolean live) {
//        Game newGame = null;
//        int isAlive = 0;
//        if (persist.liveGameAvailable(teamA ,teamB)) {
//            if (live){
//                isAlive =1;
//            }
//            newGame = new Game(teamA ,teamB ,goalsA,goalsB);
//            persist.addGame(teamA, teamB,goalsA,goalsB,isAlive);
//        }else {
//            System.out.println("game already in live");
//        }
//        return newGame;
//    }
//

        @RequestMapping(value = "/update-game", method = {RequestMethod.GET, RequestMethod.POST})
    public void updateGame (String team1Update , String team2Update , String goalsAUp , String goalsBUp) {
            persist.updateDB(team1Update,team2Update,goalsAUp,goalsBUp);
            calculateVictory();

    }

    private void calculateVictory() {
        List<Game> calculateTable = persist.allGames();


//        for (int i = 0; i < calculateTable.size(); i++) {
//                if (calculateTable.get(i).getGoalsA()>calculateTable.get(i).getGoalsB()){
//                    for (int j = 0; j < teamChoose.size(); j++) {
//                        if (Objects.equals(teamChoose.get(i).getTeamname(), calculateTable.get(i).getTeamA())){
//                            teamChoose.get(i).setPoint();
//                        }
//                    }
//                }
//             }




    }

    // add git
    @RequestMapping(value = "/finish-game", method = {RequestMethod.GET, RequestMethod.POST})
    public void finishGame (String team1toF , String  team2toF , String goalsAf ,String goalsBf) {
        persist.finishGame(team1toF,team2toF,goalsAf,goalsBf);


    }







    @RequestMapping(value = "/create-account", method = {RequestMethod.GET, RequestMethod.POST})
    public User createAccount (String username, String password) {

        User newAccount = null;
        if (utils.validateUsername(username)) {
            if (utils.validatePassword(password)) {
                if (persist.usernameAvailable(username)) {
                    String token = createHash(username, password);
                    newAccount = new User(username, token);
                    persist.addUser(username, token);
                } else {
                    System.out.println("username already exits");
                }
            } else {
                System.out.println("password is invalid");
            }
        } else {
            System.out.println("username is invalid");
        }
        return newAccount;
    }

    public String createHash (String username, String password) {
        String raw = String.format("%s_%s", username, password);
        String myHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return myHash;
    }


}
