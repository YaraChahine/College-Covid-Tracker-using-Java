import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 5056);
            DataInputStream inputFromServer = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToServer = new DataOutputStream(socket.getOutputStream());

            MyFrame myFrame = new MyFrame(inputFromServer, outputToServer);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
