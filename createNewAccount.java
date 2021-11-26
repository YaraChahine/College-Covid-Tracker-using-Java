package Server;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateNewAccount {

    String  photo;
    String firstName;
    String lastName;
    String email;
    String username;
    String password;

    Boolean vaccinated;
    String vaccinationCard;

    public CreateNewAccount(String photo, String firstName, String lastName, String email, String username, String password, Boolean vaccinated, String vaccinationCard) {
        this.photo = photo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.vaccinated = vaccinated;
        this.vaccinationCard = vaccinationCard;
        System.out.println(photo);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);
        System.out.println(vaccinated);
        System.out.println(vaccinationCard);
    }

    public boolean setToDB(Connection conn){
        String query = "INSERT INTO USERS VALUES (?,?,?,?,?,?,?,?,?)";
        String userNameQuery = "SELECT * from users where username = ?";

        try {
            PreparedStatement pStmt1 = conn.prepareStatement(userNameQuery);
            pStmt1.setString(1, username);
            pStmt1.execute();
            ResultSet resultSet = pStmt1.getResultSet();
            if(resultSet.next()){
                System.out.println("Username Already Exist, please choose another one!");
                return false;
            }else {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));


                PreparedStatement pStmt = conn.prepareStatement(query);
                pStmt.setString(1, photo);
                pStmt.setString(2, firstName);
                pStmt.setString(3, lastName);
                pStmt.setString(4, email);
                pStmt.setString(5, username);
                pStmt.setBytes(6, hash);
                pStmt.setBoolean(7, vaccinated);
                pStmt.setString(8, vaccinationCard);
                pStmt.setString(9, "safe");
                pStmt.execute();

                System.out.println("User added successfully!");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
