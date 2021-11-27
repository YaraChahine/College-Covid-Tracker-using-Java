import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

class AccountPageFrame extends JFrame implements ActionListener {

    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;

    public JLabel imgLabel;
    public ImageIcon bgImage;
    public JPanel panel;
    public JButton ActiveCases;
    public JButton ViewTrustedList;
    public JButton CheckFriends;
    public JButton SubmitPCR;

    public AccountPageFrame(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {

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
        this.setSize(375, 612);
        this.setLocation(300, 15);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(new Color(255, 255, 255));


        panel = new JPanel();
        panel.setBounds(0, 0, 375, 612);
        panel.setBackground(new Color(109, 151, 233));
        this.add(panel);

        URL url = getClass().getResource("/g4.jpeg");
        bgImage = new ImageIcon(url);
        imgLabel = new JLabel();
        imgLabel.setForeground(new Color(122, 12, 32));
        imgLabel.setBounds(0, -0, 375, 612);
        imgLabel.setIcon(bgImage);
        imgLabel.setOpaque(true);
        panel.add(imgLabel);


        JLabel logo = new JLabel("Welcome to CovidLess.");
        logo.setBounds(70, 80, 300, 50);
        logo.setForeground(new Color(255, 255, 255));
        logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 32));
        imgLabel.add(logo);


        ActiveCases = new JButton("Number of Active Cases");
        ActiveCases.setBounds(120, 200, 200, 50);
        ActiveCases.setBackground(new Color(109, 151, 233));
        ActiveCases.setHorizontalTextPosition(JButton.CENTER);
        ActiveCases.setFocusable(false);
        ActiveCases.setFont(new Font("Arial", Font.BOLD, 16));
        ActiveCases.setForeground(new Color(255, 255, 255));
        ActiveCases.setBorder(border);
        ActiveCases.addActionListener(this);
        imgLabel.add(ActiveCases);


        ViewTrustedList = new JButton("View Trusted People List");
        ViewTrustedList.setBounds(120, 275, 200, 50);
        ViewTrustedList.setBackground(new Color(255, 255, 255));
        ViewTrustedList.setHorizontalTextPosition(JButton.CENTER);
        ViewTrustedList.setFocusable(false);
        ViewTrustedList.setFont(new Font("Arial", Font.BOLD, 16));
        ViewTrustedList.setForeground(new Color(109, 151, 233));
        ViewTrustedList.setBorder(border);
        ViewTrustedList.addActionListener(this);
        imgLabel.add(ViewTrustedList);


        CheckFriends = new JButton("Check on My Friends");
        CheckFriends.setBounds(120, 350, 200, 50);
        CheckFriends.setBackground(new Color(109, 151, 233));
        CheckFriends.setHorizontalTextPosition(JButton.CENTER);
        CheckFriends.setFocusable(false);
        CheckFriends.setFont(new Font("Arial", Font.BOLD, 16));
        CheckFriends.setForeground(new Color(255, 255, 255));
        CheckFriends.setBorder(border);
        CheckFriends.addActionListener(this);
        imgLabel.add(CheckFriends);


        SubmitPCR = new JButton("Submit PCR Result");
        SubmitPCR.setBounds(120, 425, 200, 50);
        SubmitPCR.setBackground(new Color(255, 255, 255));
        SubmitPCR.setHorizontalTextPosition(JButton.CENTER);
        SubmitPCR.setFocusable(false);
        SubmitPCR.setFont(new Font("Arial", Font.BOLD, 16));
        SubmitPCR.setForeground(new Color(109, 151, 233));
        SubmitPCR.setBorder(border);
        SubmitPCR.addActionListener(this);
        imgLabel.add(SubmitPCR);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == ActiveCases) {
                dataOutputStream.writeUTF("NumberOfActiveCases");
                new ActiveCasesFrame(dataInputStream, dataOutputStream);
                this.dispose();
            } else if (e.getSource() == ViewTrustedList) {
                System.out.println("you clicked on view trusted");
                String str="ViewTrustedPeopleList\n";
                try {
                    dataOutputStream.writeUTF(str);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                new ViewTrustedListFrame(dataInputStream, dataOutputStream);
                this.dispose();
            } else if (e.getSource() == CheckFriends) {
                new CheckFriendsFrame(dataInputStream, dataOutputStream);
                this.dispose();
            } else if (e.getSource() == SubmitPCR) {
                new SubmitPCRFrame(dataInputStream, dataOutputStream);
                this.dispose();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
