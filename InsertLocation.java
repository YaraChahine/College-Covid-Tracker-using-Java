package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class InsertLocation extends Thread {

    public Connection connection;
    public Date date;
    public DateFormat timeFormat;
    public String username;
    public String selectQuery;
    public String updateQuery;
    public String location;
    public String time;
    public Locations locations;
    public Timer timer;

    public InsertLocation(String username, Connection connection) throws SQLException {
        this.date = new Date();
        this.timeFormat = new SimpleDateFormat("hh:mm:ss");
        this.time = timeFormat.format(date);
        this.username = username;
        this.connection = connection;
        this.locations = new Locations();
        this.location = this.locations.getRandomLocation();
        this.selectQuery = "Select location from users where username = ?";
        this.updateQuery = "UPDATE users SET location = ?, time_arrived = ? where username = ?";
        PreparedStatement stmt2 = connection.prepareStatement(updateQuery);
        stmt2.setString(1, location);
        stmt2.setString(2, time);
        stmt2.setString(3, username);
        stmt2.execute();
        this.timer = new Timer();
    }

    @Override
    public void run() {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        PreparedStatement stmt1 = connection.prepareStatement(selectQuery);
                        stmt1.setString(1, username);
                        ResultSet rs1 = stmt1.executeQuery();
                        location = locations.getRandomLocation();
                        if(rs1.next()){
                            if(!rs1.getString(1).equalsIgnoreCase(location)){
                                date = new Date();
                                timeFormat = new SimpleDateFormat("hh:mm:ss");
                                time = timeFormat.format(date);
                                PreparedStatement stmt2 = connection.prepareStatement(updateQuery);
                                stmt2.setString(1, location);
                                stmt2.setString(2, time);
                                stmt2.setString(3, username);
                                stmt2.execute();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }, 5*1000, 5*1000);
    }

}
