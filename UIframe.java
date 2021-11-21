
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

 class myFrame extends JFrame implements ActionListener {

	 public JLabel imgLabel;
	 public ImageIcon bgImage;
	 public JPanel panel;
	 public JTextField username;
	 public JPasswordField password;
	 public JButton signIn;
	 public JButton signUp;
	 myFrame(){
			

			
			Border border= BorderFactory.createRaisedSoftBevelBorder();


			this.setLayout(null);
			this.setTitle("CovidLess");
			this.setSize(375,612);
			this.setLocation(300,15);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			this.setResizable(false);
			this.setBackground(new Color(255, 255, 255));

			
			panel= new JPanel();
			panel.setBounds(0,0,375,612);
			panel.setBackground(new Color(109,151,233));
			this.add(panel);
			
			
			bgImage= new ImageIcon( "C:\\Users\\Lenovo\\Desktop\\g4.jpg");
			imgLabel= new JLabel();
			imgLabel.setForeground(new Color(122,12,32));
			imgLabel.setBounds(0,-0,375,612);
			imgLabel.setIcon(bgImage);
	    	imgLabel.setOpaque(true);
			panel.add(imgLabel);
			
			
			JLabel logo= new JLabel("CovidLess.");
			logo.setBounds(116, 80, 300, 50);
			logo.setForeground(new Color(255,255,255));
			logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 48));
			imgLabel.add(logo);
			
			
			JLabel usernameLabel= new JLabel("Username");
			usernameLabel.setBounds(116, 200, 300, 50);
			usernameLabel.setForeground(new Color(255,255,255));
			usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			imgLabel.add(usernameLabel);
			
			username= new JTextField();
			username.setBounds(116, 240, 200, 30);
			username.setBackground(new Color(255,255,255));
			username.setBorder(border);
			imgLabel.add(username);
			
			
			JLabel passLabel= new JLabel("Password");
			passLabel.setBounds(116, 270, 300, 50);
			passLabel.setForeground(new Color(255,255,255));
			passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			imgLabel.add(passLabel);
			
			password= new JPasswordField();
			password.setBounds(116, 310, 200, 30);
			password.setBackground(new Color(255,255,255));
			password.setBorder(border);
			imgLabel.add(password);
			
			signIn= new JButton("Sign In");
			signIn.setBounds(160,380,100,40);
			signIn.setBackground(new Color(109,151,233));
			signIn.setHorizontalTextPosition(JButton.CENTER);
			signIn.setFocusable(false);
			signIn.setFont(new Font("Arial", Font.BOLD,16));
			signIn.setForeground(new Color(255, 255, 255));
			signIn.setBorder(border);
			signIn.addActionListener(this);
			imgLabel.add(signIn);
			
			
			signUp= new JButton("Sign Up");
			signUp.setBounds(160,440,100,40);
			signUp.setBackground(new Color(255, 255, 255));
			signUp.setHorizontalTextPosition(JButton.CENTER);
			signUp.setFocusable(false);
			signUp.setFont(new Font("Arial", Font.BOLD,16));
			signUp.setForeground(new Color(109,151,233));
			signUp.setBorder(border);
			signUp.addActionListener(this);
			imgLabel.add(signUp);
			
			
			this.setVisible(true);
	
}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==signUp) {
			new SignUpFrame();
			this.dispose();
		}
		else if (e.getSource()==signIn) {
			new AccountPageFrame();
			this.dispose();
		}
		
	}

 }
 
 
 class SignUpFrame extends JFrame implements ActionListener{
	 public  JTextField nameField;
	 public  JTextField emailField;
	 public  JTextField usernameField;
	 public  JPasswordField passwordField;
	 public JFileChooser fileChooser;
	 public FileDialog fd;
	 public JButton loadButton;
	 public String userImagePath;
	 public BufferedImage userImage;
	 public JLabel imagePathLabel;
	 public JMenu vaccinationStatus;
	 public JLabel vaccinatedFormLabel;
	 public JRadioButton bVaccinated;
	 public JRadioButton bNotVaccinated;
	 public String  vaccStatus;
	 public JButton CertificateButton;
	 public String certificatePath;
	 public JLabel certificateLabel;
	 public JPanel panel;
	 public JButton submit;
	 public JButton back;
	 
	 SignUpFrame(){
			

			
			Border border= BorderFactory.createRaisedSoftBevelBorder();


			this.setLayout(null);
			this.setTitle("CovidLess");
			this.setSize(385,640);
			this.setLocation(300,15);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			this.setResizable(false);
			this.setBackground(new Color(255, 255, 255));
		
			
			panel= new JPanel();
			panel.setBounds(0,0,385,640);
			
			panel.setBackground(new Color(255,255,255));
			panel.setLayout(null);
			 panel.setPreferredSize(new Dimension(375, 770));
				this.add(panel);

			    JScrollPane pane = new JScrollPane(panel);
						this.setContentPane(pane);
			
		
		

					
			JPanel headerShadow= new JPanel();
			headerShadow.setBounds(0,0,375,48);
			headerShadow.setBackground(new Color(220,220,220));
			headerShadow.setOpaque(true);
			headerShadow.setLayout(null);
			panel.add(headerShadow);
			
			JPanel header= new JPanel();
			header.setBounds(0,0,375,45);
			header.setBackground(new Color(109,151,233));
			header.setLayout(null);
			headerShadow.add(header);
			
			
			JLabel logo= new JLabel("CovidLess.");
			logo.setBounds(10, 2,180, 40);
			logo.setForeground(new Color(255,255,255));
			logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 32));
			header.add(logo);
			
			JPanel SignUpShadow= new JPanel();
			SignUpShadow.setBounds(87,85,180,48);
			SignUpShadow.setBackground(new Color(210,210,210));
			SignUpShadow.setOpaque(true);
			SignUpShadow.setLayout(null);
			panel.add(SignUpShadow);
			
			
			JPanel SignUpBackground= new JPanel();
			SignUpBackground.setBounds(2,0,176,46);
			SignUpBackground.setBackground(new Color(255,255,255));
			SignUpBackground.setOpaque(true);
			SignUpBackground.setLayout(null);
			SignUpBackground.setBorder(null);
			SignUpShadow.add(SignUpBackground);
			
			JLabel SignUpLabel= new JLabel("Sign Up Form");
			SignUpLabel.setFont(new Font("Arial", Font.BOLD,20));
			SignUpLabel.setForeground(new Color(109,151,233));
			SignUpLabel.setBounds(30,0,170,40);
			SignUpBackground.add(SignUpLabel);
			
			JLabel nameLabel= new JLabel("Name: ");
			nameLabel.setFont(new Font("Arial", Font.BOLD,14));
			nameLabel.setForeground(new Color(109,151,233));
			nameLabel.setBounds(10,180,70,30);
			panel.add(nameLabel);
			
			nameField= new JTextField();
			nameField.setBounds(130,180,200,30);
			nameField.setBackground(new Color(250,250,250));
			nameField.setBorder(border);
			panel.add(nameField);

			JLabel emailLabel= new JLabel("Email: ");
			emailLabel.setFont(new Font("Arial", Font.BOLD,14));
			emailLabel.setForeground(new Color(109,151,233));
			emailLabel.setBounds(10,230,70,30);
			panel.add(emailLabel);
			
			
			emailField= new JTextField();
			emailField.setBounds(130,230,200,30);
			emailField.setBackground(new Color(250,250,250));
			emailField.setBorder(border);
			panel.add(emailField);
			
			JLabel usernameLabel= new JLabel("Username: ");
			usernameLabel.setFont(new Font("Arial", Font.BOLD,14));
			usernameLabel.setForeground(new Color(109,151,233));
			usernameLabel.setBounds(10,280,120,30);
			panel.add(usernameLabel);
			
			

			usernameField= new JTextField();
			usernameField.setBounds(130,280,200,30);
			usernameField.setBackground(new Color(250,250,250));
			usernameField.setBorder(border);
			panel.add(usernameField);
			
			
			JLabel passwordLabel= new JLabel("Password: ");
			passwordLabel.setFont(new Font("Arial", Font.BOLD,14));
			passwordLabel.setForeground(new Color(109,151,233));
			passwordLabel.setBounds(10,330,120,30);
			panel.add(passwordLabel);
			
			
			
			passwordField= new JPasswordField();
			passwordField.setBounds(130,330,200,30);
			passwordField.setBackground(new Color(250,250,250));
			passwordField.setBorder(border);
			panel.add(passwordField);
			
			
			
			JLabel fileLabel= new JLabel("Upload photo: ");
			fileLabel.setFont(new Font("Arial", Font.BOLD,14));
			fileLabel.setForeground(new Color(109,151,233));
			fileLabel.setBounds(10,380,120,30);
			panel.add(fileLabel);
			
			
			imagePathLabel=new JLabel();
			imagePathLabel.setFont(new Font("Arial", Font.BOLD,7));
			imagePathLabel.setForeground(new Color(109,151,233));
			imagePathLabel.setBounds(260,380,120,30);
			imagePathLabel.setText("tesT");
	           panel.add(imagePathLabel);

				
		   loadButton = new JButton("Choose Photo");
		   loadButton.setBounds(130,380,115,30);
		   loadButton.setForeground(new Color(255,255,255));
		   loadButton.setBackground( new Color(109,151,233));
		   loadButton.setFocusable(false);
           
		   panel.add(loadButton);

			  loadButton.addActionListener(ev -> {
			      JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
			      fc.addChoosableFileFilter(new FileNameExtensionFilter("Image files",
			          new String[] { "png", "jpg", "jpeg", "gif" }));
			      if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			        
			          try {
						BufferedImage userImage = ImageIO.read(fc.getSelectedFile());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			          userImagePath=fc.getSelectedFile().toString();
			     
			            imagePathLabel.setText(userImagePath);
			          
			        
			      }
			    });

			  
				
			  JLabel vaccinationLabel= new JLabel("Vaccination Status: ");
			  vaccinationLabel.setBounds(10,430,125,30);
			  vaccinationLabel.setFont(new Font("Arial", Font.BOLD,12));
			  vaccinationLabel.setForeground(new Color(109,151,233));
			  panel.add(vaccinationLabel);
				
		 bVaccinated= new JRadioButton("Vaccinated");
		 bNotVaccinated= new JRadioButton("Not Vaccinated");
		  ButtonGroup group1=new ButtonGroup(); 
		  group1.add(bNotVaccinated);
		  group1.add(bVaccinated);
		  bVaccinated.setBounds(140,430,140,30);
		  bVaccinated.setBackground(new Color(255,255,255));
		  bNotVaccinated.setBackground(new Color(255,255,255));
		  
		  bVaccinated.addActionListener(this);
		  bNotVaccinated.addActionListener(this);
		  bNotVaccinated.setBounds(140,460,140,30);
		  panel.add(bNotVaccinated);
		  panel.add(bVaccinated);
		  
		  // to be added in case vaccinated is true
		  vaccinatedFormLabel= new JLabel();
		  vaccinatedFormLabel.setForeground(new Color(109,151,233));
		  vaccinatedFormLabel.setBounds(10,500,300,30);
		  vaccinatedFormLabel.setFont(new Font("Arial", Font.BOLD,14));
		  vaccinatedFormLabel.setText(" ");

		  panel.add(vaccinatedFormLabel);
		 
	        
		  certificateLabel=new JLabel();
		  certificateLabel.setFont(new Font("Arial", Font.BOLD,12));
		  certificateLabel.setForeground(new Color(109,151,233));
		  certificateLabel.setBounds(80,590,200,30);
		  certificateLabel.setText(" ");
		  
		  CertificateButton = new JButton("Upload Certificate");
		  CertificateButton.setBounds(90,560,166,30);
		  CertificateButton.setForeground(new Color(255,255,255));
		  CertificateButton.setBackground( new Color(109,151,233));
		  CertificateButton.setFocusable(false);
           

		   CertificateButton.addActionListener(ev -> {
			      JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
			  //   fc.addChoosableFileFilter(new FileNameExtensionFilter("Image files",
			    //      new String[] { "png", "jpg", "jpeg", "gif" }));
			      if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      
			          certificatePath=fc.getSelectedFile().toString();
			          certificateLabel.setText(certificatePath);
			          
			        
			      }
			    });

		  
		   submit= new JButton("Sign In");
		   submit.setBounds(230,520,100,30);
		   submit.setForeground(new Color(255,255,255));
		   submit.setBackground(new Color(109,151,233));
		   panel.add(submit);
		   
		   back= new JButton("Back");
		   back.setBounds(10,520,100,30);
		   back.setForeground(new Color(255,255,255));
		   back.setBackground(new Color(109,151,233));
		   back.addActionListener(this);
		   panel.add(back);
		        
			this.setVisible(true);
			
			
			/*fileChooser = new JFileChooser();
			fileChooser.setBounds(130, 380, 200, 70);
			fileChooser.addChoosableFileFilter( new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);
			*/
			
			}  
			
	  
		
			
			
	  

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==bVaccinated) {
			vaccinatedFormLabel.setText("Please upload your vaccination certificate");
			vaccStatus="Vaccinated";
			panel.add(CertificateButton);
		   panel.add(certificateLabel);
		   CertificateButton.setVisible(true);
			certificateLabel.setVisible(true);
			   submit.setBounds(230,620,100,30);
			   back.setBounds(10, 620, 100,30);

		  

		}
		else
		{
			if(e.getSource()==bNotVaccinated) {
				vaccinatedFormLabel.setText(" ");
				vaccStatus="Not vaccinated";
				CertificateButton.setVisible(false);
				certificateLabel.setVisible(false);
				 submit.setBounds(230,520,100,30);
				   back.setBounds(10, 520, 100,30);

				


			}
			else
			{
				if(e.getSource()==back) {
					new myFrame();
					this.dispose();
				}
			}
		}
		
		
	}
	 
 }
 class AccountPageFrame  extends JFrame implements ActionListener{
	 public JLabel imgLabel;
	 public ImageIcon bgImage;
	 public JPanel panel;
	 public JButton ActiveCases;
	 public JButton ViewTrustedList;
	 public JButton CheckFriends;
	 public JButton SubmitPCR;
	 
	 AccountPageFrame(){
			

			
		 Border border= BorderFactory.createRaisedSoftBevelBorder();


			this.setLayout(null);
			this.setTitle("CovidLess");
			this.setSize(375,612);
			this.setLocation(300,15);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			this.setResizable(false);
			this.setBackground(new Color(255, 255, 255));


			
			panel= new JPanel();
			panel.setBounds(0,0,375,612);
			panel.setBackground(new Color(109,151,233));
			this.add(panel);
			
			
			bgImage= new ImageIcon( "C:\\Users\\Lenovo\\Desktop\\g4.jpg");
			imgLabel= new JLabel();
			imgLabel.setForeground(new Color(122,12,32));
			imgLabel.setBounds(0,-0,375,612);
			imgLabel.setIcon(bgImage);
	    	imgLabel.setOpaque(true);
			panel.add(imgLabel);
			
			
			JLabel logo= new JLabel("Welcome to CovidLess.");
			logo.setBounds(70, 80, 300, 50);
			logo.setForeground(new Color(255,255,255));
			logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 32));
			imgLabel.add(logo);
			
			
			
			ActiveCases= new JButton("Number of Active Cases");
			ActiveCases.setBounds(120,200,200,50);
			ActiveCases.setBackground(new Color(109,151,233));
			ActiveCases.setHorizontalTextPosition(JButton.CENTER);
			ActiveCases.setFocusable(false);
			ActiveCases.setFont(new Font("Arial", Font.BOLD,16));
			ActiveCases.setForeground(new Color(255, 255, 255));
			ActiveCases.setBorder(border);
			ActiveCases.addActionListener(this);
			imgLabel.add(ActiveCases);
			
			
			ViewTrustedList= new JButton("View Trusted People List");
			ViewTrustedList.setBounds(120,275,200,50);
			ViewTrustedList.setBackground(new Color(255, 255, 255));
			ViewTrustedList.setHorizontalTextPosition(JButton.CENTER);
			ViewTrustedList.setFocusable(false);
			ViewTrustedList.setFont(new Font("Arial", Font.BOLD,16));
			ViewTrustedList.setForeground(new Color(109,151,233));
			ViewTrustedList.setBorder(border);
			ViewTrustedList.addActionListener(this);
			imgLabel.add(ViewTrustedList);
			
			
			CheckFriends= new JButton("Check on My Friends");
			CheckFriends.setBounds(120,350,200,50);
			CheckFriends.setBackground(new Color(109,151,233));
			CheckFriends.setHorizontalTextPosition(JButton.CENTER);
			CheckFriends.setFocusable(false);
			CheckFriends.setFont(new Font("Arial", Font.BOLD,16));
			CheckFriends.setForeground(new Color(255, 255, 255));
			CheckFriends.setBorder(border);
			CheckFriends.addActionListener(this);
			imgLabel.add(CheckFriends);
			
			
			SubmitPCR= new JButton("Submit PCR Result");
			SubmitPCR.setBounds(120,425,200,50);
			SubmitPCR.setBackground(new Color(255, 255, 255));
			SubmitPCR.setHorizontalTextPosition(JButton.CENTER);
			SubmitPCR.setFocusable(false);
			SubmitPCR.setFont(new Font("Arial", Font.BOLD,16));
			SubmitPCR.setForeground(new Color(109,151,233));
			SubmitPCR.setBorder(border);
			SubmitPCR.addActionListener(this);
			imgLabel.add(SubmitPCR);
			
			this.setVisible(true);
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==ActiveCases) {
			new ActiveCasesFrame();
			this.dispose();
		}
		else if (e.getSource()==ViewTrustedList) {
			new ViewTrustedListFrame();
			this.dispose();
		}
		else if (e.getSource()==CheckFriends) {
			new CheckFriendsFrame();
			this.dispose();
		}
		else if (e.getSource()==SubmitPCR) {
			new SubmitPCRFrame();
			this.dispose();
		}
	}
 }
 
 class ActiveCasesFrame extends JFrame implements ActionListener{
	 public JLabel imgLabel;
	 public ImageIcon bgImage;
	 public JPanel panel;
	 public JButton back;
	
	 
	 ActiveCasesFrame(){
			

			
		 Border border= BorderFactory.createRaisedSoftBevelBorder();


			this.setLayout(null);
			this.setTitle("CovidLess");
			this.setSize(375,612);
			this.setLocation(300,15);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			this.setResizable(false);
			this.setBackground(new Color(255, 255, 255));


			
			panel= new JPanel();
			panel.setBounds(0,0,375,612);
			panel.setBackground(new Color(109,151,233));
			this.add(panel);
			
			
			bgImage= new ImageIcon( "C:\\Users\\Lenovo\\Desktop\\g4.jpg");
			imgLabel= new JLabel();
			imgLabel.setForeground(new Color(122,12,32));
			imgLabel.setBounds(0,-0,375,612);
			imgLabel.setIcon(bgImage);
	    	imgLabel.setOpaque(true);
			panel.add(imgLabel);
			
			
			JLabel logo= new JLabel("CovidLess.");
			logo.setBounds(116, 80, 300, 50);
			logo.setForeground(new Color(255,255,255));
			logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 48));
			imgLabel.add(logo);
			
			JLabel Number = new JLabel("Number of Active Cases to is ");
			Number.setBounds(70, 200, 300, 50);
			Number.setForeground(new Color(255,255,255));
			Number.setFont(new Font("Bodoni Mt", Font.BOLD, 25));
			imgLabel.add(Number);
			
			back= new JButton("Back");
		    back.setBounds(50,500,100,30);
	    	back.setForeground(new Color(255,255,255));
			back.setBackground(new Color(109,151,233));
			back.addActionListener(this);
			imgLabel.add(back);
			        
		    this.setVisible(true);
			
			
			
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back) {
			new AccountPageFrame();
			this.dispose();
		}
	}
}
 
 class ViewTrustedListFrame  extends JFrame implements ActionListener{
 	 
	 @Override
	 public void actionPerformed(ActionEvent e) {
	 	// TODO Auto-generated method stub
	 	
	 }
 }
 
 class CheckFriendsFrame  extends JFrame implements ActionListener{
 	 
	 @Override
	 public void actionPerformed(ActionEvent e) {
	 	// TODO Auto-generated method stub
	 	
	 }
 }
 
 class SubmitPCRFrame  extends JFrame implements ActionListener{
	 public JLabel imgLabel;
	 public ImageIcon bgImage;
	 public JPanel panel;
	 public JComboBox Result;
	 public JButton submit;
	 public JButton back;
	
	 
	 SubmitPCRFrame(){
			

			
		 Border border= BorderFactory.createRaisedSoftBevelBorder();


			this.setLayout(null);
			this.setTitle("CovidLess");
			this.setSize(375,612);
			this.setLocation(300,15);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			this.setResizable(false);
			this.setBackground(new Color(255, 255, 255));


			
			panel= new JPanel();
			panel.setBounds(0,0,375,612);
			panel.setBackground(new Color(109,151,233));
			this.add(panel);
			
			
			bgImage= new ImageIcon( "C:\\Users\\Lenovo\\Desktop\\g4.jpg");
			imgLabel= new JLabel();
			imgLabel.setForeground(new Color(122,12,32));
			imgLabel.setBounds(0,-0,375,612);
			imgLabel.setIcon(bgImage);
	    	imgLabel.setOpaque(true);
			panel.add(imgLabel);
			
			
			JLabel logo= new JLabel("CovidLess.");
			logo.setBounds(116, 80, 300, 50);
			logo.setForeground(new Color(255,255,255));
			logo.setFont(new Font("Bodoni Mt", Font.ITALIC, 48));
			imgLabel.add(logo);
			
			JLabel Result = new JLabel("Please type in your PCR Result");
			Result.setBounds(90, 200, 300, 50);
			Result.setForeground(new Color(255,255,255));
			Result.setFont(new Font("Bodoni Mt", Font.BOLD, 20));
			imgLabel.add(Result);
			
			JCheckBox Positive = new JCheckBox("Positive");
			JCheckBox Negative = new JCheckBox("Negative");

			  Positive.setBounds(150,280,140,30);
			  Negative.setBounds(150,310,140,30);
			  
			  Positive.setBackground(new Color(255,255,255));
			  Negative.setBackground(new Color(255,255,255));
			  
			  Positive.addActionListener(this);
			  Negative.addActionListener(this);
			  
			  imgLabel.add(Positive);
			  imgLabel.add(Negative);
		    
			 // If Positive it will show
			 JLabel PositiveFormLabel= new JLabel();
			 PositiveFormLabel.setForeground(new Color(109,151,233));
			 PositiveFormLabel.setBounds(10,500,300,30);
			 PositiveFormLabel.setFont(new Font("Arial", Font.BOLD,14));
			 PositiveFormLabel.setText(" ");

			 imgLabel.add(PositiveFormLabel);
	
			 // If Negative it will show
			 JLabel NegativeFormLabel= new JLabel();
			 NegativeFormLabel.setForeground(new Color(109,151,233));
			 NegativeFormLabel.setBounds(10,500,300,30);
			 NegativeFormLabel.setFont(new Font("Arial", Font.BOLD,14));
			 NegativeFormLabel.setText(" ");

			 imgLabel.add(NegativeFormLabel);
			 
			 
			 
			 
			submit= new JButton("Submit");
			submit.setBounds(250,500,100,30);
			submit.setForeground(new Color(255,255,255));
			submit.setBackground(new Color(109,151,233));
			submit.addActionListener(this);
			imgLabel.add(submit);
			
			
			back= new JButton("Back");
		    back.setBounds(50,500,100,30);
	    	back.setForeground(new Color(255,255,255));
			back.setBackground(new Color(109,151,233));
			back.addActionListener(this);
			imgLabel.add(back);
			        
		    this.setVisible(true);
			
			
			
	 }
	 @Override
	 public void actionPerformed(ActionEvent e) {
	 	// TODO Auto-generated method stub
		 if(e.getSource()==back) {
				new AccountPageFrame();
				this.dispose();
			}
		 else if(e.getSource()==submit) {
				new AccountPageFrame();
				this.dispose();
			}     
	 
 }
 
	 }

	
	 
	 
public class UIframe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new myFrame();
		System.out.println("hey");
	}

}



