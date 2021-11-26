import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MyFrame extends JFrame implements ActionListener {

    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    public String receivedData;

    public JLabel imgLabel;
    public ImageIcon bgImage;
    public JPanel panel;
    public JTextField username;
    public JPasswordField password;
    public JButton signIn;
    public JButton signUp;

    public MyFrame(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    System.out.println("Closed!!!!!!!!!!");
                    dataOutputStream.writeUTF("Exit");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        Border border = BorderFactory.createRaisedSoftBevelBorder();

        this.setLayout(null);
        this.setTitle("CovidLess");
        this.setSize(375, 612);
        this.setLocation(300, 15);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(new Color(255, 255, 255));


        panel = new JPanel();
        panel.setBounds(0, 0, 375, 612);
        panel.setBackground(new Color(109, 151, 233));
        this.add(panel);


        bgImage = new ImageIcon("C:\\Users\\lenovo\\IdeaProjects\\JavaSocket\\Client_Server_GUI\\assists\\g4.jpeg");
        imgLabel = new JLabel();
        imgLabel.setForeground(new Color(122, 12, 32));
        imgLabel.setBounds(0, -0, 375, 612);
        imgLabel.setIcon(bgImage);
        imgLabel.setOpaque(true);
        panel.add(imgLabel);


        JLabel logo = new JLabel("CovidLess.");
        logo.setBounds(116, 80, 300, 50);
        logo.setForeground(new Color(255, 255, 255));
        logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 48));
        imgLabel.add(logo);


        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(116, 200, 300, 50);
        usernameLabel.setForeground(new Color(255, 255, 255));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        imgLabel.add(usernameLabel);

        username = new JTextField();
        username.setBounds(116, 240, 200, 30);
        username.setBackground(new Color(255, 255, 255));
        username.setBorder(border);
        imgLabel.add(username);


        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(116, 270, 300, 50);
        passLabel.setForeground(new Color(255, 255, 255));
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        imgLabel.add(passLabel);

        password = new JPasswordField();
        password.setBounds(116, 310, 200, 30);
        password.setBackground(new Color(255, 255, 255));
        password.setBorder(border);
        imgLabel.add(password);

        signIn = new JButton("Sign In");
        signIn.setBounds(160, 380, 100, 40);
        signIn.setBackground(new Color(109, 151, 233));
        signIn.setHorizontalTextPosition(JButton.CENTER);
        signIn.setFocusable(false);
        signIn.setFont(new Font("Arial", Font.BOLD, 16));
        signIn.setForeground(new Color(255, 255, 255));
        signIn.setBorder(border);
        signIn.addActionListener(this);
        imgLabel.add(signIn);


        signUp = new JButton("Sign Up");
        signUp.setBounds(160, 440, 100, 40);
        signUp.setBackground(new Color(255, 255, 255));
        signUp.setHorizontalTextPosition(JButton.CENTER);
        signUp.setFocusable(false);
        signUp.setFont(new Font("Arial", Font.BOLD, 16));
        signUp.setForeground(new Color(109, 151, 233));
        signUp.setBorder(border);
        signUp.addActionListener(this);
        imgLabel.add(signUp);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signUp) {
                dataOutputStream.writeUTF("Signup");
                SignUpFrame signUpFrame = new SignUpFrame(dataInputStream, dataOutputStream);
                this.dispose();
            } else if (e.getSource() == signIn) {
                if(username.getText().equals("") || password.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Set Password or Username!");
                }else {
                    String strPassword = password.getText();
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hashedPassword = digest.digest(strPassword.getBytes(StandardCharsets.UTF_8));

                    String str1 = "";
                    for (int i=0; i<hashedPassword.length; i++){
                        str1 += hashedPassword[i];
                    }

                    String str = "Login\n" + username.getText() + "\n" + str1;
                    dataOutputStream.writeUTF(str);
                    receivedData = dataInputStream.readUTF();
                    System.out.println(receivedData);
                    if (receivedData.equalsIgnoreCase("correct")) {
                        AccountPageFrame accountPageFrame = new AccountPageFrame(dataInputStream, dataOutputStream);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Wrong Password or Username!");
                    }
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

}


