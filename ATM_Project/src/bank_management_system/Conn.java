package bank_management_system;

import java.sql.*;

public class Conn {
    
    // create connection
	Connection c;
	// create statement
	Statement s;
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Note the quotes around the driver class name
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","1234");
            s =c.createStatement(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
