import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SubmitPCRFrame extends JFrame implements ActionListener {
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;

    public JLabel imgLabel;
    public ImageIcon bgImage;
    public JPanel panel;
    public JComboBox Result;
    public JButton submit;
    public JButton back;


    public SubmitPCRFrame(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {

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

        JLabel Result = new JLabel("Please type in your PCR Result");
        Result.setBounds(90, 200, 300, 50);
        Result.setForeground(new Color(255, 255, 255));
        Result.setFont(new Font("Bodoni Mt", Font.BOLD, 20));
        imgLabel.add(Result);

        JCheckBox Positive = new JCheckBox("Positive");
        JCheckBox Negative = new JCheckBox("Negative");

        Positive.setBounds(150, 280, 140, 30);
        Negative.setBounds(150, 310, 140, 30);

        Positive.setBackground(new Color(255, 255, 255));
        Negative.setBackground(new Color(255, 255, 255));

        Positive.addActionListener(this);
        Negative.addActionListener(this);

        imgLabel.add(Positive);
        imgLabel.add(Negative);

        // If Positive it will show
        JLabel PositiveFormLabel = new JLabel();
        PositiveFormLabel.setForeground(new Color(109, 151, 233));
        PositiveFormLabel.setBounds(10, 500, 300, 30);
        PositiveFormLabel.setFont(new Font("Arial", Font.BOLD, 14));
        PositiveFormLabel.setText(" ");

        imgLabel.add(PositiveFormLabel);

        // If Negative it will show
        JLabel NegativeFormLabel = new JLabel();
        NegativeFormLabel.setForeground(new Color(109, 151, 233));
        NegativeFormLabel.setBounds(10, 500, 300, 30);
        NegativeFormLabel.setFont(new Font("Arial", Font.BOLD, 14));
        NegativeFormLabel.setText(" ");

        imgLabel.add(NegativeFormLabel);


        submit = new JButton("Submit");
        submit.setBounds(250, 500, 100, 30);
        submit.setForeground(new Color(255, 255, 255));
        submit.setBackground(new Color(109, 151, 233));
        submit.addActionListener(this);
        imgLabel.add(submit);


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
        } else if (e.getSource() == submit) {
            new AccountPageFrame(dataInputStream, dataOutputStream);
            this.dispose();
        }

    }

}
