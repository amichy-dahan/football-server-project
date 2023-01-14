////
//package com.dev.utils;
//
//import com.dev.objects.Game;
//import com.dev.objects.Team;
//import com.dev.objects.User;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class Persist {
//
//    private Connection connection;
//    private List<Team> allTeams = new ArrayList<>();
//    Team t1 = new Team("Arsenal", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t2 = new Team("Manchester City", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t3 = new Team("Newcastle United", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t4 = new Team("Tottenham Hotspur", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t5 = new Team("Manchester United", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t6 = new Team("Liverpool", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t7 = new Team("Brighton & Hove Albion", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t8 = new Team("Chelsea", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t9 = new Team("Fulham", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t10 = new Team("Brentford", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t11 = new Team("Crystal Palace", 0, 0, 0, 0, 0, 0, 0, false);
//    Team t12 = new Team("Aston Villa", 0, 0, 0, 0, 0, 0, 0, false);
//
//    public void addToTeamsArray() {
//        allTeams.add(t1);
//        allTeams.add(t2);
//        allTeams.add(t3);
//        allTeams.add(t4);
//        allTeams.add(t5);
//        allTeams.add(t6);
//        allTeams.add(t7);
//        allTeams.add(t8);
//        allTeams.add(t9);
//        allTeams.add(t10);
//        allTeams.add(t11);
//        allTeams.add(t12);
//    }
//
//
//    @PostConstruct
//    public void createConnectionToDatabase() {
//        try {
//            this.connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/football_project", "root", "1234");
//            System.out.println("Successfully connected to DB");
//            // --------
//            List<Team> teams = getAllTeams();
//            if (!checkIfTableIsEmpty()) {
//                for (Team team : teams) {
//                    System.out.println(team.getTeamname());
//                    addTeamToDataBase(
//                            team.getTeamname(),
//                            team.getPosition(),
//                            team.getPlayed(),
//                            team.getWon(),
//                            team.getDraw(),
//                            team.getLost(),
//                            team.getGoalsFor(),
//                            team.getGoalsAgainst(),
//                            team.isLive()
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean checkIfTableIsEmpty() {
//        boolean isEmpty = true;
//        ResultSet resultSet;
//        try {
//            resultSet =
//                    this.connection
//                            .createStatement()
//                            .executeQuery("SELECT team FROM league");
//            if (resultSet.next()) {
//                System.out.println("table already has teams in it");
//                isEmpty = true;
//            } else {
//                System.out.println("table is empty");
//                isEmpty = false;
//            }
//        } catch (SQLException e) {
//            new RuntimeException(e);
//        }
//        return isEmpty;
//    }
//
//
//    public void addTeamToDataBase(String team, int position, int played, int won, int draw, int lost, int goalsFor, int goalsAgainst, boolean live) throws SQLException {
//        try {
//            PreparedStatement preparedStatement = this.connection.prepareStatement(
//                    "INSERT INTO league(team, position, played, won, draw, lost, goals_for, goals_against, live) VALUE (?,?,?,?,?,?,?,?)");
//            preparedStatement.setString(1, team);
//            preparedStatement.setInt(2, position);
//            preparedStatement.setInt(3, played);
//            preparedStatement.setInt(4, won);
//            preparedStatement.setInt(5, draw);
//            preparedStatement.setInt(6, lost);
//            preparedStatement.setInt(7, goalsFor);
//            preparedStatement.setInt(8, goalsAgainst);
//            preparedStatement.setBoolean(9, live);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Team> getAllTeams() {
//        List<Team> teams = new ArrayList<>();
//        try {
//            ResultSet resultSet =
//                    this.connection
//                            .createStatement()
//                            .executeQuery("SELECT position,team, played, won, draw, lost, goals_for, goals_against, live FROM teams ");
//            while (resultSet.next()) {
//                String teamName = resultSet.getString("team");
//                int position = resultSet.getInt("position");
//                int played = resultSet.getInt("played");
//                int won = resultSet.getInt("won");
//                int draw = resultSet.getInt("draw");
//                int lost = resultSet.getInt("lost");
//                int goalsFor = resultSet.getInt("goals_for");
//                int goalsAgainst = resultSet.getInt("goals_against");
//                boolean live = resultSet.getBoolean("live");
//                Team team = new Team(teamName, position, played, won, draw, lost, goalsFor, goalsAgainst, live);
//                teams.add(team);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return teams;
//    }
//
//
//    public List<Team> getAvailableTeams() {
//        List<Team> availableTeams = new ArrayList<>();
//        try {
//            ResultSet resultSet =
//                    this.connection
//                            .createStatement()
//                            .executeQuery("SELECT position ,team, played, won, draw, lost, goals_for, goals_against, live , IF(live = true) FROM teams");
//            while (resultSet.next()) {
//                String teamName = resultSet.getString("team");
//                int position = resultSet.getInt("position");
//                int played = resultSet.getInt("played");
//                int won = resultSet.getInt("won");
//                int draw = resultSet.getInt("draw");
//                int lost = resultSet.getInt("lost");
//                int goalsFor = resultSet.getInt("goals_for");
//                int goalsAgainst = resultSet.getInt("goals_against");
//                boolean live = resultSet.getBoolean("live");
//                Team team = new Team(teamName, position, played, won, draw, lost, goalsFor, goalsAgainst, live);
//                availableTeams.add(team);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return availableTeams;
//    }
//
////    public void calculateLeagueTable(List<Team> teams) {
////        for (Team team: teams) {
////            int points = team.getPoints() + team.getWon()*3;
////        }
////    }
//
//
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        try {
//            ResultSet resultSet =
//                    this.connection
//                            .createStatement()
//                            .executeQuery("SELECT username, password FROM league");
//            while (resultSet.next()) {
//                String username = resultSet.getString("username");
//                String password = resultSet.getString("password");
//                User user = new User(username, password);
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return users;
//    }
//
//    public boolean usernameAvailable(String username) {
//        boolean available = false;
//        try {
//            PreparedStatement preparedStatement = this.connection.prepareStatement(
//                    "SELECT id " +
//                            "FROM users " +
//                            "WHERE username = ?");
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                available = false;
//            } else {
//                available = true;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return available;
//    }
//
//    public boolean liveGameAvailable(String teamA, String teamB) {
//        boolean available = false;
//        try {
//            PreparedStatement preparedStatement = this.connection.prepareStatement(
//                    "SELECT id " +
//                            "FROM games " +
//                            "WHERE teamA = ? AND teamB = ?");
//            preparedStatement.setString(1, teamA);
//            preparedStatement.setString(2, teamB);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                available = false;
//            } else {
//                available = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return available;
//    }
//
//
//    public User getUserByToken(String token) {
//        User user = null;
//        try {
//            PreparedStatement preparedStatement = this.connection
//                    .prepareStatement(
//                            "SELECT id, username FROM users WHERE token = ?");
//            preparedStatement.setString(1, token);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String username = resultSet.getString("username");
//                user = new User(username, token);
//                user.setId(id);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return user;
//    }
//
//    public String getUserByCreds(String username, String token) {
//        String response = null;
//        try {
//            PreparedStatement preparedStatement = this.connection.prepareStatement(
//                    "SELECT * FROM users WHERE username = ? AND token = ?");
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, token);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                response = token;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return response;
//    }
//
//
//    public List<Game> getTeamsInLiveGame() {
//        List<Game> allGames = new ArrayList<>();
//
//        try {
//            ResultSet resultSet =
//                    this.connection
//                            .createStatement()
//                            .executeQuery(
//                                    "SELECT teamA , teamB, goalsA , goalsB FROM games");
//            while (resultSet.next()) {
//                String teamA = resultSet.getString("teamA");
//                String teamB = resultSet.getString("teamB");
//                int goalsA = resultSet.getInt("goalsA");
//                int goalsB = resultSet.getInt("goalsB");
//                Game game = new Game(teamA, teamB, goalsA, goalsB);
//                allGames.add(game);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return allGames;
//    }
//
//        public List<Team> getNamesTeam (){
//        List<Team> allTeams = new ArrayList<>();
//         try {
//             ResultSet resultSet =
//                     this.connection
//                             .createStatement()
//                             .executeQuery("SELECT position , team, played, won, draw, lost, goals_for, goals_against, goal_difference FROM league");
//             while (resultSet.next()) {
//                 String teamname = resultSet.getString("team");
//                 int position = resultSet.getInt("position");
//                 int played = resultSet.getInt("played");
//                 int won = resultSet.getInt("won");
//                 int draw = resultSet.getInt("draw");
//                 int lost = resultSet.getInt("lost");
//                 int goalsFor = resultSet.getInt("goals_for");
//                 int goalsAgainst = resultSet.getInt("goals_against");
//                 int goalDifference = resultSet.getInt("goal_difference");
//
//                 Team team = new Team (teamname, position, played, won, draw, lost, goalsFor, goalsAgainst, goalDifference);
//                 allTeams.add(team);
//             }
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//
//        return allTeams;
//     }
//
//        public void addUser (String username, String token) {
//        try {
//            PreparedStatement preparedStatement =
//                    this.connection
//                            .prepareStatement("INSERT INTO users (username, token) VALUE (?,?)");
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, token);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//
//
//}
//
//
//
//
//
//
//
//

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//




package com.dev.utils;

import com.dev.objects.Game;
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
import java.util.Objects;

@Component
public class Persist {

    private Connection connection;
        private List<Team> teamArrayList = new ArrayList<>();
    Team t1 = new Team("Arsenal", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t2 = new Team("Manchester City", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t3 = new Team("Newcastle United", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t4 = new Team("Tottenham Hotspur", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t5 = new Team("Manchester United", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t6 = new Team("Liverpool", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t7 = new Team("Brighton & Hove Albion", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t8 = new Team("Chelsea", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t9 = new Team("Fulham", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t10 = new Team("Brentford", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t11 = new Team("Crystal Palace", 0, 0, 0, 0, 0, 0, 0, 0);
    Team t12 = new Team("Aston Villa", 0, 0, 0, 0, 0, 0, 0, 0);


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
            List<Game> allMyGames = allGames();
            for (Game game: allMyGames) {
                System.out.println(game.getTeamA() + " - " + game.getTeamB() + " " + game.getGoalsA() + " " + game.getGoalsB());
            }

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
            }else{
                addTeamToDataBase("Aston Villa", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Crystal Palace", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Brentford", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Fulham", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Chelsea", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Brighton & Hove Albion", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Liverpool", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Manchester United", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Tottenham Hotspur", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Manchester City", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Newcastle United", 0, 0, 0, 0, 0, 0, 0, false);
                addTeamToDataBase("Arsenal", 0, 0, 0, 0, 0, 0, 0, false);





            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


        public void addTeamToDataBase(String team, int position, int played, int won, int draw, int lost, int goalsFor, int goalsAgainst, boolean live) throws SQLException {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO league(team, position, played, won, draw, lost, goals_for, goals_against, live) VALUE (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, team);
            preparedStatement.setInt(2, position);
            preparedStatement.setInt(3, played);
            preparedStatement.setInt(4, won);
            preparedStatement.setInt(5, draw);
            preparedStatement.setInt(6, lost);
            preparedStatement.setInt(7, goalsFor);
            preparedStatement.setInt(8, goalsAgainst);
            preparedStatement.setBoolean(9, live);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Game> getTeamsInLiveGame (){

        List<Game> allGames = new ArrayList<>();
        try {
            ResultSet resultSet =
                    this.connection
                            .createStatement()
                            .executeQuery(
                                    "SELECT teamA , teamB, goalsA , goalsB FROM games WHERE live = 1");
            while (resultSet.next()) {
                String teamA = resultSet.getString("teamA");
                String teamB = resultSet.getString("teamB");
                int goalsA = resultSet.getInt("goalsA");
                int goalsB = resultSet.getInt("goalsB");
                Game game = new Game(teamA,teamB,goalsA,goalsB);
                allGames.add(game);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allGames;
    }



    public List<Team> getNamesTeam (){
        List<Team> allTeams = new ArrayList<>();

        try {
            ResultSet resultSet =
                    this.connection
                            .createStatement()
                            .executeQuery("SELECT position , team, played, won, draw, lost, goals_for, goals_against,live FROM league");
            while (resultSet.next()) {
                String teamname = resultSet.getString("team");
                int position = resultSet.getInt("position");
                int played = resultSet.getInt("played");
                int won = resultSet.getInt("won");
                int draw = resultSet.getInt("draw");
                int lost = resultSet.getInt("lost");
                int goalsFor = resultSet.getInt("goals_for");
                int goalsAgainst = resultSet.getInt("goals_against");
                boolean live = resultSet.getBoolean("live");
                Team team = new Team (teamname);

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
    public void addGame(String teamA, String teamB, int goalsA, int goalsB, boolean live) {
        try {
            PreparedStatement preparedStatement =
                    this.connection
                            .prepareStatement("INSERT INTO games (teamA, teamB,goalsA ,goalsB,live) VALUE (?,?,?,?,?)");
            preparedStatement.setString(1, teamA);
            preparedStatement.setString(2, teamB);
            preparedStatement.setString(3, String.valueOf(goalsA));
            preparedStatement.setString(4, String.valueOf(goalsB));
            preparedStatement.setBoolean(5,live);
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
    public boolean liveGameAvailable (String teamA ,String teamB) {
        boolean available = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT id " +
                            "FROM games " +
                            "WHERE teamA = ? AND teamB = ? AND live = 1");
            preparedStatement.setString(1, teamA);
            preparedStatement.setString(2, teamB);
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


    public void updateDB(String updateTeamA,String updateTeamB,String updateGoalsA,String updateGoalsB) {


        System.out.println(updateTeamA);
        System.out.println(updateTeamB);
        System.out.println(updateGoalsA);
        System.out.println(updateGoalsB);

        try {
            PreparedStatement preparedStatement =
                    this.connection
                            .prepareStatement("UPDATE games SET goalsA = ? , goalsB = ?  WHERE teamA = ?  AND teamB = ? AND live =1;");
            preparedStatement.setInt(1, Integer.parseInt(updateGoalsA));
            preparedStatement.setInt(2, Integer.parseInt(updateGoalsB));
            preparedStatement.setString(3, updateTeamA);
            preparedStatement.setString(4, updateTeamB);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
// add to git
    public void finishGame(String team1toF, String team2toF , String goalsAf , String goalsBf) {

        try {
            PreparedStatement preparedStatement =
                    this.connection
                            .prepareStatement("UPDATE games SET live = 0 WHERE teamA=? AND teamB =? AND goalsA =? AND goalsB =? AND live=1;");
            preparedStatement.setString(1,team1toF);
            preparedStatement.setString(2, team2toF);
            preparedStatement.setString(3, goalsAf);
            preparedStatement.setString(4, goalsBf);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Game> allGames() {
        List<Game> allGames = new ArrayList<>();
        try {
            ResultSet resultSet =
                    this.connection
                            .createStatement()
                            .executeQuery("SELECT teamA, teamB, goalsA, goalsB  FROM games");
            while (resultSet.next()) {
                String teamA = resultSet.getString("teamA");
                String teamB = resultSet.getString("teamB");
                int goalsA = resultSet.getInt("goalsA");
                int goalsB = resultSet.getInt("goalsB");
                Game game = new Game(teamA, teamB, goalsA, goalsB);
                allGames.add(game);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allGames;
    }
}
