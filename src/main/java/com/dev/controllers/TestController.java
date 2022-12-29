package com.dev.controllers;

import com.dev.objects.Game;
import com.dev.objects.Team;
import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.responses.SignInReponse;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;


@RestController
public class TestController {

    private List<User> myUsers = new ArrayList<>();

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
        List<Team> allTeams = persist.getNamesTeam();
        return allTeams;
    }



    @RequestMapping(value = "/newGame", method = {RequestMethod.GET, RequestMethod.POST})
    public void newGameLive(String team1 , String team2) {
        Team teamA = persist.getTeamForLiveGame(team1);
        Team teamB = persist.getTeamForLiveGame(team2);



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
