package payme.model;

 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.Class;
import java.sql.DriverManager;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
 
 
public class DBConnection {
	String jdbcDriver = "com.mysql.jdbc.Driver";
    String dbName = "payme";
	
	public void connect() throws ClassNotFoundException, SQLException{
	    	
	        Class.forName(jdbcDriver);
	        Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=root");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        Statement st = null;
			try {
				st = con.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
				st.executeUpdate("CREATE DATABASE " +dbName);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
  
	}
	
	public boolean createEmployeeTable() throws SQLException, ClassNotFoundException {
		String EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS employee_tbl ( " + " id INT NOT NULL AUTO_INCREMENT, " +" fname VARCHAR(15) NOT NULL,lname VARCHAR(15) NOT NULL, position VARCHAR(50) NOT NULL, hr_rate FLOAT NOT NULL,  ot_rate FLOAT NOT NULL,PRIMARY KEY (id)"+")";
		 
	    boolean success = false;
	    
	    Class.forName(jdbcDriver);
	    Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/payme?user=root&password=root");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			st.executeUpdate(EMPLOYEE_TABLE);
			success= true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    return success;
	}

	public boolean createAdminTable() throws SQLException, ClassNotFoundException {
		String ADMIN_TABLE = "CREATE TABLE IF NOT EXISTS admin_tbl ( " + " id int NOT NULL AUTO_INCREMENT , " +" username VARCHAR(15) NOT NULL, password VARCHAR(15) NOT NULL, type VARCHAR(50) NOT NULL, date_added TIMESTAMP NOT NULL ,PRIMARY KEY (id)"+")";
		 
	    boolean success = false;
	    
	    Class.forName(jdbcDriver);
	    Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/payme?user=root&password=root");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			st.executeUpdate(ADMIN_TABLE);
			success= true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    return success;
	}

	public boolean login( String userName,  String password) throws ClassNotFoundException, SQLException 
	{
	    
		String query = "SELECT username, password FROM admin_tbl WHERE username = ? AND password = ?";
		ResultSet rs = null;
		boolean success = false;
	    
	    Class.forName(jdbcDriver);
	    Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/payme?user=root&password=root");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PreparedStatement stmt = null;

        stmt = con.prepareStatement(query);
        stmt.setString(1, userName);
        stmt.setString(2, password);
        rs = stmt.executeQuery();
        if(rs.next()){
            success = true;                
        }
	    
		return success;
  
	}

	public boolean createTimePeriodTable() throws SQLException, ClassNotFoundException {
		String TIMECARD_TABLE = "CREATE TABLE IF NOT EXISTS payrolltime_tbl ( " + " id INT NOT NULL AUTO_INCREMENT, " +" payStartPeriod VARCHAR(15) NOT NULL, payEndPeriod VARCHAR(15) NOT NULL,  PRIMARY KEY (id)"+")";
		 
	    boolean success = false;
	    
	    Class.forName(jdbcDriver);
	    Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/payme?user=root&password=root");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			st.executeUpdate(TIMECARD_TABLE);
			success= true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    return success;
	}
	
	public boolean insetTimeCard(String p_startP, String p_endP) throws SQLException, ClassNotFoundException{

		String sql = "INSERT INTO payrolltime_tbl "
				+ "(payStartPeriod, payEndPeriod) "
				+ "VALUES ('" + p_startP + "','"
				+ p_endP + "') ";
		
		
	    boolean success = false;
	    
	    Class.forName(jdbcDriver);
	    Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/payme?user=root&password=root");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	    	st.execute(sql); 
			success= true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    return success;
	    
		
	}

}

  
 
