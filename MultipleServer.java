package Server;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.*;
import java.util.*;
import java.net.*;

public class MultipleServer {

	public static Connection con;

	public static void main(String[] args) throws IOException {
		String url ="jdbc:mysql://localhost:3306/COVID_Project";
		String userName ="root";
		String password ="root@MySQLv8.0.27";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		}catch (Exception e){
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

	public ClientHandler(Socket clientSocket, Connection connection,DataInputStream inputFromClient, DataOutputStream outputToClient) {
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
		Boolean done = false;
		Boolean clientClosed = false;
		while (!done){
			try {
				received = inputFromClient.readUTF();
				String[] strings = received.split("\n");
				/**
				 * _> Login: .Login
				 * 			 .Username
				 * 			 .Password
				 *
				 * _> Signup: .Name
				 * 			  .Email
				 * 			  .Username
				 * 			  .Password
				 * 			  .Photo
				 * 			  .Vaccination Status
				 * 			  .Vaccination Card
				 *
				 */

				switch (strings[0]) {
					case "Login":
						UserLogin userLogin = new UserLogin(strings[1], strings[2]);
						boolean bool = userLogin.authenticateUser(connection);
						if (bool) {
							outputToClient.writeUTF("correct");
							done = true;
						} else {
							outputToClient.writeUTF("wrong password or username!");
						}
						break;
					case "Signup":
						System.out.println("Hello from signup!");
						boolean userDoneSignup = false;
						while(!userDoneSignup) {
							received = inputFromClient.readUTF();
							String[] userInfo = received.split("\n");

							if (received.equalsIgnoreCase("back")){
								break;
							}
							if (received.equals("Exit")) {
								System.out.println("-----------------------------------------------------------------------------------");
								System.out.println("Client " + this.clientSocket + " sends exit...");
								System.out.println("Closing this connection.");
								this.clientSocket.close();
								clientClosed = true;
								System.out.println("Connection closed");
								break;
							}

							String firstName = userInfo[0];
							String lastName = userInfo[1];
							String email = userInfo[2];
							String username = userInfo[3];
							String password = userInfo[4];
							String photo = userInfo[5];
							Boolean vaccinated = false;
							if (userInfo[6].equalsIgnoreCase("Vaccinated")) {
								vaccinated = true;
							}
							String vaccinationCard = userInfo[7];
							CreateNewAccount createNewAccount = new CreateNewAccount(photo, firstName, lastName, email, username, password, vaccinated, vaccinationCard);
							userDoneSignup = createNewAccount.setToDB(connection);
							if (userDoneSignup){
								done = true;
							}
							outputToClient.writeUTF("Already exists");
						}
						break;
					default:
						System.out.println("[ERROR]: I am in Client Handler, switch part!");
				}

				if (done){
					break;
				}

				if (received.equalsIgnoreCase("Exit")) {
					System.out.println("-----------------------------------------------------------------------------------");
					System.out.println("Client " + this.clientSocket + " sends exit...");
					System.out.println("Closing this connection.");
					this.clientSocket.close();
					clientClosed = true;
					System.out.println("Connection closed");
					break;
				}

			}catch (Exception e){
				e.printStackTrace();
			}
		}

		while (true && !clientClosed) {
			System.out.println("helllooo");
			try {
				// Initiate communication with Client
				outputToClient.writeUTF("What do you want?[Date | Time]..\n" + "Type Exit to terminate connection.");
				// Receive the answer from Client
				received = inputFromClient.readUTF();
				System.out.println(received);


				// Receiving Exit closes the connection and breaks the loop
				if (received.equals("Exit")) {
					System.out.println("-----------------------------------------------------------------------------------");
					System.out.println("Client " + this.clientSocket + " sends exit...");
					System.out.println("Closing this connection.");
					this.clientSocket.close();
					clientClosed = true;
					System.out.println("Connection closed");
					break;
				}

				// Creating and formatting Date object
				Date date = new Date();
				DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
				DateFormat fortime = new SimpleDateFormat("hh:mm:ss");

				// Send to Client what is requested
				switch (received) {

				case "Date":
					toReturn = fordate.format(date);
					outputToClient.writeUTF(toReturn);
					break;

				case "Time":
					toReturn = fortime.format(date);
					outputToClient.writeUTF(toReturn);
					break;

				default:
					outputToClient.writeUTF("Invalid input");
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
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