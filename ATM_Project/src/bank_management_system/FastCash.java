package bank_management_system;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

 
	private static final long serialVersionUID = -1200419035096276745L;
	JLabel l1, l2,l3;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pin;

    FastCash(String pin) {
        this.pin = pin;
		ImageIcon i1 = new ImageIcon("src/icons/atm.jpg");
		Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		l3 = new JLabel(i3);
		l3.setBounds(0,0,900,900);
		add(l3);

        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("100");
        b2 = new JButton("500");
        b3 = new JButton("1000");
        b4 = new JButton("2000");
        b5 = new JButton("5000");
        b6 = new JButton("10000");
        b7 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(235,300,700,30);
        l3.add(l1);

        b1.setBounds(170,415,150,30);
        l3.add(b1);

        b2.setBounds(370,415,150,30);
        l3.add(b2);

        b3.setBounds(170,450,150,30);
        l3.add(b3);

        b4.setBounds(370,450,150,30);
        l3.add(b4);

        b5.setBounds(170,485,150,30);
        l3.add(b5);

        b6.setBounds(370,485,150,30);
        l3.add(b6);

        b7.setBounds(370,520,150,30);
        l3.add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(960, 1080);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
    	 try {
             String amount = ((JButton)ae.getSource()).getText(); 
             Conn c = new Conn();
             ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pin+"'");
             int balance = 0;
             while (rs.next()) {
                 if (rs.getString("type").equals("Deposit")) {
                     balance += Integer.parseInt(rs.getString("amount"));
                 } else {
                     balance -= Integer.parseInt(rs.getString("amount"));
                 }
             } String num = "17";
             if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                 JOptionPane.showMessageDialog(null, "Insuffient Balance");
                 return;
             }

             if (ae.getSource() == b7) {
                 this.setVisible(false);
                 new Transactions(pin).setVisible(true);
             }else{
                 Date date = new Date();
                 c.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                 JOptionPane.showMessageDialog(null, " "+amount+" Debited Successfully");
                     
                 setVisible(false);
                 new Transactions(pin).setVisible(true);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

            
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}