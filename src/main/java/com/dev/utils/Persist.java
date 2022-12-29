
package com.dev.utils;

import com.dev.objects.Team;
import com.dev.objects.User;
import com.dev.objects.UserObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Persist {

    private Connection connection;

    private final SessionFactory sessionFactory;

    @Autowired
     public Persist (SessionFactory sf) {
        this.sessionFactory = sf;
    }




    @PostConstruct
    public void createConnectionToDatabase () {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/football_project", "root", "1234");
            System.out.println("Successfully connected to DB");

            chekDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void chekDb() {

        try {
            ResultSet resultSet =
                    this.connection
                            .createStatement()
                            .executeQuery("SELECT team FROM league");
            if (resultSet.next()) {
                System.out.println("table is full");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

     public Team getTeamForLiveGame (String teamToFind){
         Team team = null;

         try {
             PreparedStatement preparedStatement = this.connection
                     .prepareStatement(
                             "SELECT team FROM league WHERE team = ?");
             preparedStatement.setString(1, teamToFind);
             ResultSet resultSet = preparedStatement.executeQuery();
             if (resultSet.next()) {
                 String teamName = resultSet.getString("team");
                 team = new Team(teamName);
                 System.out.println(team.getTeamname());
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }


         return team;
     }





    public List<Team> getNamesTeam (){
        List<Team> allTeams = new ArrayList<>();
         try {
             ResultSet resultSet =
                     this.connection
                             .createStatement()
                             .executeQuery("SELECT position , team, played, won, draw, lost, goals_for, goals_against, goal_difference, points FROM league");
             while (resultSet.next()) {
                 String teamname = resultSet.getString("team");
                 int position = resultSet.getInt("position");
                 int played = resultSet.getInt("played");
                 int won = resultSet.getInt("won");
                 int draw = resultSet.getInt("draw");
                 int lost = resultSet.getInt("lost");
                 int goalsFor = resultSet.getInt("goals_for");
                 int goalsAgainst = resultSet.getInt("goals_against");
                 int goalDifference = resultSet.getInt("goal_difference");
                 int points = resultSet.getInt("points");
                 Team team = new Team (teamname, position, played, won, draw, lost, goalsFor, goalsAgainst, goalDifference, points);
                 allTeams.add(team);
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

        return allTeams;
     }


    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            ResultSet resultSet =
                    this.connection
                            .createStatement()
                            .executeQuery("SELECT username, token FROM users");
            while (resultSet.next()) {
                String token = resultSet.getString("token");
                String username = resultSet.getString("username");
                User user = new User(username, token);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public List<UserObject> getAllUsersHibernate () {
        Session session = sessionFactory.openSession();
        UserObject userObject = new UserObject();
        session.save(userObject);
        List<UserObject> userObjects = session.createQuery("FROM UserObject ").list();
        session.close();
        return userObjects;
    }

    public void addUser (String username, String token) {
        try {
            PreparedStatement preparedStatement =
                    this.connection
                            .prepareStatement("INSERT INTO users (username, token) VALUE (?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean usernameAvailable (String username) {
        boolean available = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT id " +
                    "FROM users " +
                    "WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                available = false;
            } else {
                available = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return available;
    }

    public User getUserByToken (String token) {
        User user = null;
        try {
            PreparedStatement preparedStatement = this.connection
                    .prepareStatement(
                            "SELECT id, username FROM users WHERE token = ?");
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                user = new User(username, token);
                user.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void addNote (int userId, String content) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO notes (content, user_id) VALUE (?, ?)");
            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        }
    }

    public List<String> getNotesByUserId (int userId) {
        List<String> notes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT content FROM notes WHERE user_id = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                notes.add(content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notes;
    }

    public String getUserByCreds (String username, String token) {
        String response = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND token = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response = token;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
