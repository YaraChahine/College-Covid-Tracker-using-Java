package Server;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLogin {

    public String username;
    public String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public boolean authenticateUser(Connection con){
        String query = "SELECT password from users where username = ?";

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                byte[] passwordDB = rs.getBytes(1);
                String str1 = "";
                for (int i=0; i<passwordDB.length; i++){
                    str1 += passwordDB[i];
                }

                if(str1.equals(password)){
                    System.out.println("Correct password!");
                    return true;
                }else{
                    System.out.println("Wrong password!");
                }

            }else{
                System.out.println("Wrong username!");
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
