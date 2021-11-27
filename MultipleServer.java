package Server;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class MultipleServer {

    public static Connection con;

    public static void main(String[] args) throws IOException {
        String url = "jdbc:mysql://localhost:3306/COVID_Project";
        String userName = "root";
        String password = "root@MySQLv8.0.27";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServerSocket serverSocket = new ServerSocket(5056);

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();

                System.out.println("A new client is connected : " + clientSocket);

                DataInputStream inputFromClient = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(clientSocket.getOutputStream());

                System.out.println("Assigning new thread for this client");

                System.out.println("-----------------------------------------------------------------------------------");

                Thread thread = new ClientHandler(clientSocket, con, inputFromClient, outputToClient);
                thread.start();

            } catch (Exception e) {
                clientSocket.close();
                e.printStackTrace();
            }
        }
    }
}

// Server.ClientHandler class
class ClientHandler extends Thread {
    final Socket clientSocket;
    Connection connection;
    final DataInputStream inputFromClient;
    final DataOutputStream outputToClient;
    Thread thread;
    InsertLocation insertLocation;
    DatabaseInfo databaseInfo;

    public ClientHandler(Socket clientSocket, Connection connection, DataInputStream inputFromClient, DataOutputStream outputToClient) {
        this.clientSocket = clientSocket;
        this.connection = connection;
        this.inputFromClient = inputFromClient;
        this.outputToClient = outputToClient;
    }

    @Override
    public void run() {
        // Variables
        String received;
        String toReturn;
        boolean done = false;
        boolean clientClosed = false;
        String username = "";
        while (!done) {
            try {
                System.out.println("Waiting input from the Client!");
                received = inputFromClient.readUTF();

                if (received.equalsIgnoreCase("Exit")) {
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Client " + this.clientSocket + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.clientSocket.close();
                    clientClosed = true;
                    System.out.println("Connection closed");
                    if (insertLocation != null) {
                        insertLocation.timer.cancel();
                    }
                    break;
                }

                String[] strings = received.split("\n");

                switch (strings[0]) {

                    case "Login":
                        System.out.println(strings[2]);
                        UserLogin userLogin = new UserLogin(strings[1], strings[2]);
                        boolean bool = userLogin.authenticateUser(connection);
                        if (bool) {
                            outputToClient.writeUTF("correct");
                            username = userLogin.username;
                            insertLocation = new InsertLocation(userLogin.username, connection);
                            thread = insertLocation;
                            thread.start();
                            databaseInfo = new DatabaseInfo(username, connection);
                            done = true;
                        } else {
                            outputToClient.writeUTF("wrong password or username!");
                        }
                        break;

                    case "Signup":
                        System.out.println("Hello from signup!");
                        boolean userDoneSignup = false;
                        while (!userDoneSignup) {
                            received = inputFromClient.readUTF();
                            String[] userInfo = received.split("\n");

                            if (received.equalsIgnoreCase("back")) {
                                break;
                            }
                            if (received.equals("Exit")) {
                                System.out.println("-----------------------------------------------------------------------------------");
                                System.out.println("Client " + this.clientSocket + " sends exit...");
                                System.out.println("Closing this connection.");
                                this.clientSocket.close();
                                clientClosed = true;
                                System.out.println("Connection closed");
                                insertLocation.timer.cancel();
                                break;
                            }

                            String firstName = userInfo[0];
                            String lastName = userInfo[1];
                            String email = userInfo[2];
                            username = userInfo[3];
                            String password = userInfo[4];
                            String photo = userInfo[5];
                            Boolean vaccinated = false;
                            if (userInfo[6].equalsIgnoreCase("Vaccinated")) {
                                vaccinated = true;
                            }
                            String vaccinationCard = userInfo[7];
                            CreateNewAccount createNewAccount = new CreateNewAccount(photo, firstName, lastName, email, username, password, vaccinated, vaccinationCard);
                            userDoneSignup = createNewAccount.setToDB(connection);
                            if (userDoneSignup) {
                                done = true;
                            } else {
                                outputToClient.writeUTF("Already exists");
                            }
                        }
                        break;
                    default:
                        System.out.println("[ERROR]: I am in Client Handler, switch part!");
                }

                if (done || clientClosed) {
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        while (!clientClosed) {
            try {
                received = inputFromClient.readUTF();
                System.out.println(received);
                String[] userInfo = received.split("\n");

                if (received.equals("Exit")) {
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Client " + this.clientSocket + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.clientSocket.close();
                    System.out.println("Connection closed");
                    insertLocation.timer.cancel();
                    break;
                }


                switch (userInfo[0]) {
                    case "NumberOfActiveCases":
                        outputToClient.writeUTF("" + databaseInfo.getActiveCases());
                        break;
                    case "ViewTrustedPeopleList":
                        outputToClient.writeUTF(databaseInfo.getUsernameNotListedTrust());
                        outputToClient.writeUTF(databaseInfo.getUsernameListedTrust());
                        while(true) {
                            try {
                                received = inputFromClient.readUTF();
                                userInfo = received.split("\n");
                                if(userInfo[0].equalsIgnoreCase("Exit")) {
                                    System.out.println("-----------------------------------------------------------------------------------");
                                    System.out.println("Client " + this.clientSocket + " sends exit...");
                                    System.out.println("Closing this connection.");
                                    this.clientSocket.close();
                                    System.out.println("Connection closed");
                                    insertLocation.timer.cancel();
                                    break;
                                }else if(userInfo[0].equalsIgnoreCase("Add")){
                                    databaseInfo.insertFriendToMyTrustedList(userInfo[1]);
                                }else {
                                    databaseInfo.removeFriendFromTrustedList(userInfo[1]);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    case "CheckOnMyFriends":
                        outputToClient.writeUTF(databaseInfo.getFriendsListedMeTrust());
                        break;
                    case "SubmitPCRResult":

                        break;
                    default:
                        outputToClient.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            // Closing resources
            this.inputFromClient.close();
            this.outputToClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}