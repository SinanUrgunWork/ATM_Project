package bank_management_system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;



public class Login extends JFrame implements ActionListener{

	private static final long serialVersionUID = 4970701102627233818L;
	JButton login , signUp,clear;
	JTextField cardTextField;
	JPasswordField pinTextField;
	Login (){
		setTitle("Machine");
		
		setLayout(null);
		// logo icon 
		ImageIcon i1 = new ImageIcon("src/icons/logo.jpg");
		Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel label = new JLabel(i3);
		label.setBounds(70,10,100,100);
		add(label);
		// welcome text
		JLabel text = new JLabel("Welcome to ATM");
		text.setFont(new Font("Osward",Font.BOLD,38));
		text.setBounds(200,40,400,40);
		add(text);
		// card no text
		JLabel cardno = new JLabel("Card No:");
		cardno.setFont(new Font("Raleway",Font.BOLD,28));
		cardno.setBounds(120,150,150,40);
		add(cardno);
		// Card Text Field
		 cardTextField = new JTextField();
		cardTextField.setBounds(300,150,230,30);
		cardTextField.setFont(new Font("Arial",Font.BOLD,14));
		add(cardTextField);
		// pin text
		JLabel pin = new JLabel("PIN:");
		pin.setFont(new Font("Raleway",Font.BOLD,28));
		pin.setBounds(120,220,150,40);
		add(pin);
		// Pin Text Field
		 pinTextField = new JPasswordField();
		pinTextField.setBounds(300,220,230,30);
		pinTextField.setFont(new Font("Arial",Font.BOLD,14));
		add(pinTextField);
		// Sign in Button 
		 login = new JButton("SIGN IN");
		login.setBounds(300,300,100,30);
		login.setBackground(Color.BLACK);
		login.setForeground(Color.WHITE);
		login.addActionListener(this);
		add(login);
		// Clear Button 
		 clear = new JButton("Clear");
		clear.setBounds(430,300,100,30);
		clear.setBackground(Color.BLACK);
		clear.setForeground(Color.WHITE);
		clear.addActionListener(this);
		add(clear);
		// Sign up Button 
		 signUp = new JButton("Sign Up");
		signUp.setBounds(300,350,230,30);
		signUp.setBackground(Color.BLACK);
		signUp.setForeground(Color.WHITE);
		signUp.addActionListener(this);
		add(signUp);
		
		getContentPane().setBackground(Color.WHITE);
		
		setSize(800,500);
		setVisible(true);
		setLocation(350,200);
	}
	
	public static void main(String[] args) {

		new Login();
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == clear) {
			cardTextField.setText("");
			pinTextField.setText("");
		}else if (e.getSource() == login) {
			Conn conn = new Conn();
			String cardnumber = cardTextField.getText();
			String pinnumber= pinTextField.getText();
			String query  = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
			try {
				ResultSet rs= conn.s.executeQuery(query);
				if(rs.next()) {
					setVisible(false);
					new Transactions(pinnumber).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
				}
			}catch(Exception es) {
				System.out.println(es);
			}
		}else if (e.getSource() == signUp) {
			setVisible(false);
			new SignupOne().setVisible(true);
		}
	}

}
