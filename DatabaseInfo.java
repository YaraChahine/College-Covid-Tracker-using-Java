package Server;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseInfo {

    public String username;
    public Connection connection;
    public String query;

    public DatabaseInfo(String username, Connection connection) {
        this.username = username;
        this.connection = connection;
    }

    public int getActiveCases() {
        try {
            query = "SELECT COUNT(*) from users where status = 'contagious'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String getUsernameNotListedTrust(){
        try {
            String query = "SELECT username from users where username not in (SELECT trusted_user from trustedFriends where username = ?)";
            PreparedStatement pStmt2 = connection.prepareStatement(query);
            pStmt2.setString(1, username);
            pStmt2.execute();
            ResultSet resultSet = pStmt2.getResultSet();
            String str = "";
            while (resultSet.next()) {
                str += resultSet.getString(1) + "\n";
            }
            pStmt2.close();
            return str;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public String getUsernameListedTrust(){
        try {
            String query = "SELECT trusted_user from trustedFriends where username = ?";
            PreparedStatement pStmt2 = connection.prepareStatement(query);
            pStmt2.setString(1, username);
            pStmt2.execute();
            ResultSet resultSet = pStmt2.getResultSet();
            String str = "";
            while (resultSet.next()) {
                str += resultSet.getString(1) + "\n";
            }
            pStmt2.close();
            return str;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public String getFriendsListedMeTrust(){
        try {
            String query = "SELECT username, status from users where username in (SELECT username from trustedFriends where trusted_user = ?)";
            PreparedStatement pStmt2 = connection.prepareStatement(query);
            pStmt2.setString(1, username);
            pStmt2.execute();
            ResultSet resultSet = pStmt2.getResultSet();
            String str = "";
            while (resultSet.next()) {
                str += resultSet.getString(1) + "\n" +
                        resultSet.getString(2) + "\n";
            }
            pStmt2.close();
            return str;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public boolean insertFriendToMyTrustedList(String friendUsername){
        try {
            String query = "INSERT into trustedFriends VALUES(?,?)";
            PreparedStatement pStmt2 = connection.prepareStatement(query);
            pStmt2.setString(1, username);
            pStmt2.setString(2, friendUsername);

            return pStmt2.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeFriendFromTrustedList(String friendUsername){
        try {
            String query = "DELETE FROM trustedFriends WHERE (username=? AND trusted_user = ?)";
            PreparedStatement pStmt2 = connection.prepareStatement(query);
            pStmt2.setString(1, username);
            pStmt2.setString(2, friendUsername);

            return pStmt2.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserStatus(){
        return false;
    }

}
