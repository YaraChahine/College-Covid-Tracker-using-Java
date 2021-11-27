import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ViewTrustedListFrame extends JFrame implements ActionListener {

    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    public JPanel panel;
    public JLabel imgLabel;
    public JComboBox friendsMenu;
    public JComboBox trustedFriendsMenu;
    public JButton addFriend;
    public JButton removeFriend;
    public String[] usersArray;
    public String[] usersTrustedArray;
    public boolean bool = false;

    public ViewTrustedListFrame(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    dataOutputStream.writeUTF("Exit");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Border border = BorderFactory.createRaisedSoftBevelBorder();

        this.setLayout(null);
        this.setTitle("CovidLess");
        this.setSize(385, 640);
        this.setLocation(300, 15);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(new Color(255, 255, 255));

        panel = new JPanel();
        panel.setBounds(0, 0, 385, 640);

        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(375, 770));
        this.add(panel);

        JScrollPane pane = new JScrollPane(panel);
        this.setContentPane(pane);

        JPanel headerShadow = new JPanel();
        headerShadow.setBounds(0, 0, 375, 48);
        headerShadow.setBackground(new Color(220, 220, 220));
        headerShadow.setOpaque(true);
        headerShadow.setLayout(null);
        panel.add(headerShadow);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 375, 45);
        header.setBackground(new Color(109, 151, 233));
        header.setLayout(null);
        headerShadow.add(header);

        JLabel logo = new JLabel("CovidLess.");
        logo.setBounds(10, 2, 180, 40);
        logo.setForeground(new Color(255, 255, 255));
        logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 32));
        header.add(logo);

        JPanel titleShadow = new JPanel();
        titleShadow.setBounds(87, 85, 180, 48);
        titleShadow.setBackground(new Color(210, 210, 210));
        titleShadow.setOpaque(true);
        titleShadow.setLayout(null);
        panel.add(titleShadow);

        JPanel TitleBackground = new JPanel();
        TitleBackground.setBounds(2, 0, 176, 46);
        TitleBackground.setBackground(new Color(255, 255, 255));
        TitleBackground.setOpaque(true);
        TitleBackground.setLayout(null);
        TitleBackground.setBorder(null);
        titleShadow.add(TitleBackground);

        JLabel trustedLabel = new JLabel("Trusted Friends");
        trustedLabel.setFont(new Font("Arial", Font.BOLD, 20));
        trustedLabel.setForeground(new Color(109, 151, 233));
        trustedLabel.setBounds(13, 0, 170, 40);
        TitleBackground.add(trustedLabel);

        JLabel label1 = new JLabel("Your trusted friends can see your health status.");
        label1.setFont(new Font("Arial", Font.BOLD, 14));
        label1.setForeground(new Color(120, 120, 120));
        label1.setBounds(10, 160, 350, 40);
        panel.add(label1);

        JLabel label2 = new JLabel("Click on \"add\" to add a trusted friend");
        label2.setFont(new Font("Arial", Font.BOLD, 13));
        label2.setForeground(new Color(120, 120, 120));
        label2.setBounds(65, 180, 260, 40);
        panel.add(label2);

        JLabel usersLabel = new JLabel("Users: ");
        usersLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usersLabel.setForeground(new Color(109, 151, 233));
        usersLabel.setBounds(10, 230, 90, 30);
        panel.add(usersLabel);

        try {
            String received = dataInputStream.readUTF();
            usersArray = received.split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        friendsMenu = new JComboBox(usersArray);
        friendsMenu.setBounds(60, 230, 190, 30);
        friendsMenu.setBackground(new Color(255, 255, 255));
        panel.add(friendsMenu);

        addFriend = new JButton("Add");
        addFriend.setBackground(new Color(109, 151, 233));
        addFriend.setBounds(265, 230, 70, 30);
        addFriend.setForeground(new Color(255, 255, 255));
        panel.add(addFriend);
        addFriend.addActionListener(this);


        JLabel usersTrustedLabel = new JLabel("Users: ");
        usersTrustedLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usersTrustedLabel.setForeground(new Color(109, 151, 233));
        usersTrustedLabel.setBounds(10, 350, 90, 30);
        panel.add(usersTrustedLabel);

        try {
            String received = dataInputStream.readUTF();
            usersTrustedArray = received.split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        trustedFriendsMenu = new JComboBox(usersTrustedArray);
        trustedFriendsMenu.setBounds(60, 350, 190, 30);
        trustedFriendsMenu.setBackground(new Color(255, 255, 255));
        panel.add(trustedFriendsMenu);

        removeFriend = new JButton("Remove");
        removeFriend.setBackground(new Color(109, 151, 233));
        removeFriend.setBounds(265, 350, 70, 30);
        removeFriend.setForeground(new Color(255, 255, 255));
        panel.add(removeFriend);
        removeFriend.addActionListener(this);

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addFriend) {
                String friendName = (String) friendsMenu.getSelectedItem();
                String str = "Add\n" + friendName;
                dataOutputStream.writeUTF(str);
                friendsMenu.removeItem(friendName);
                trustedFriendsMenu.addItem(friendName);
            } else if (e.getSource() == removeFriend) {
                String friendName = (String) trustedFriendsMenu.getSelectedItem();
                String str = "remove\n" + friendName;
                dataOutputStream.writeUTF(str);
                trustedFriendsMenu.removeItem(friendName);
                friendsMenu.addItem(friendName);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ;
    }
}






