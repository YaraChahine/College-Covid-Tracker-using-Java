package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class UserStatus {

    public String status;

    public UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstruction(Connection con){
        String instruction;
        String instQuery;
        String countQuery;
        Statement stmt;
        ResultSet rs;
        int randomNumber;
        int count;
        int counter = 1;
        try {
            switch (status) {
                case "contagious":
                    instQuery = "SELECT * from contagiousInstructions";
                    countQuery = "SELECT COUNT(*) from contagiousInstructions";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(countQuery);
                    rs.next();
                    count = rs.getInt(1);
                    rs = stmt.executeQuery(instQuery);
                    randomNumber = generateIndex(count);
                    while (rs.next()){
                        if(counter == randomNumber) {
                            return rs.getString(1);
                        }
                        counter++;
                    }
                    break;
                case "risk":
                    return "Do PCR test! Since you were exposed to the virus.";
                case "safe":
                    instQuery = "SELECT * from safeInstructions";
                    countQuery = "SELECT COUNT(*) from safeInstructions";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(countQuery);
                    rs.next();
                    count = rs.getInt(1);
                    rs = stmt.executeQuery(instQuery);
                    randomNumber = generateIndex(count);
                    while (rs.next()){
                        if(counter == randomNumber) {
                            return rs.getString(1);
                        }
                        counter++;
                    }
                    break;
                default:
                    return "Wrong status!";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private int generateIndex(int max){
        Random random = new Random();
        return random.nextInt(max)+1;
    }

}
