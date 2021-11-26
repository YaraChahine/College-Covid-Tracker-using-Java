import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;


public class ActiveCasesFrame extends JFrame implements ActionListener {

    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;

    public JLabel imgLabel;
    public ImageIcon bgImage;
    public JPanel panel;
    public JButton back;


    public ActiveCasesFrame(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {

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


        JLabel logo = new JLabel("CovidLess.");
        logo.setBounds(116, 80, 300, 50);
        logo.setForeground(new Color(255, 255, 255));
        logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 48));
        imgLabel.add(logo);

        JLabel Number = new JLabel("Number of Active Cases to is ");
        Number.setBounds(70, 200, 300, 50);
        Number.setForeground(new Color(255, 255, 255));
        Number.setFont(new Font("Bodoni Mt", Font.BOLD, 25));
        imgLabel.add(Number);

        back = new JButton("Back");
        back.setBounds(50, 500, 100, 30);
        back.setForeground(new Color(255, 255, 255));
        back.setBackground(new Color(109, 151, 233));
        back.addActionListener(this);
        imgLabel.add(back);

        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == back) {
            new AccountPageFrame(dataInputStream, dataOutputStream);
            this.dispose();
        }
    }
}



