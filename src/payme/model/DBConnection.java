package payme.model;

 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.Class;
import java.sql.DriverManager;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
 
 
 
public class DBConnection {
	
	private static Connection conn;
	static String jdbcDriver = "com.mysql.jdbc.Driver";
    static String dbName = "payme";
    private static String url = "jdbc:mysql://localhost/"+dbName;
    private static String user = "root";
    private static String pass = "root";
    
    
    static String[]  payrolltime_label = {"Num","Employee", "Rate", "Days",
    		"Reg OT", "Reg ND", "Reg OT ND",
    		"Sunday", "Sunday OT", "Sunday ND", "Sunday OT ND",
    		"Reg Holiday", "Reg Holiday OT", "Reg Holiday ND", "Reg Holiday OT ND",
    		"Special Holiday", "Special Holiday OT", "Special Holiday ND", "Special Holiday OT ND",
    		"13th Month", "Total Hours"};
    static String[]  timehours_label = {"Num","TimeNo", "Machine", "EmpNo","Name", "IOMD", "Date Time" };

  
    public static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }catch(InstantiationException ie){
            System.err.println("Error: "+ie.getMessage());
        }catch(IllegalAccessException iae){
            System.err.println("Error: "+iae.getMessage());
        }

        conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
    
	public boolean createEmployeeTable() throws SQLException, ClassNotFoundException {
		String EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS employee_tbl ( " + " id INT NOT NULL AUTO_INCREMENT, " +" fname VARCHAR(15) NOT NULL,lname VARCHAR(15) NOT NULL, position VARCHAR(50) NOT NULL, rate FLOAT NOT NULL,  status VARCHAR(50) ,PRIMARY KEY (id)"+")";
		 
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
	
	public boolean createAdminTable() throws SQLException, ClassNotFoundException {
		String ADMIN_TABLE = "CREATE TABLE IF NOT EXISTS admin_tbl ( " + " id int NOT NULL AUTO_INCREMENT , " +" username VARCHAR(15) NOT NULL, password VARCHAR(15) NOT NULL, type VARCHAR(50) NOT NULL, date_added TIMESTAMP NOT NULL ,PRIMARY KEY (id)"+")";
		 
	    boolean success = false;
	    
	    Class.forName(jdbcDriver);
	    Connection con = connect();
		 
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

	public boolean createTimePeriodTable() throws SQLException, ClassNotFoundException {
		String TIMECARD_TABLE = "CREATE TABLE IF NOT EXISTS payrolltime_tbl ( " + " time_id INT NOT NULL AUTO_INCREMENT, " 
	    +"emp_num VARCHAR(15) NOT NULL, " 
	    +"rate VARCHAR(15) NOT NULL, " 
	    +"days VARCHAR(15) NOT NULL, " 
	    +"reg_ot_hrs FLOAT DEFAULT '0', " + "reg_nd FLOAT DEFAULT '0', "+ "reg_ot_nd FLOAT DEFAULT '0', "
	    +"sunday_hrs FLOAT DEFAULT '0', " + "sunday_ot_hrs FLOAT DEFAULT '0', "+ "sunday_nd FLOAT DEFAULT '0', "+ "sunday_ot_nd FLOAT DEFAULT '0', "
	    +"reg_holiday FLOAT DEFAULT '0', " + "reg_holiday_ot FLOAT DEFAULT '0', "+ "reg_holiday_nd FLOAT DEFAULT '0', "+ "reg_holiday_ot_nd FLOAT DEFAULT '0', "
	    +"special_holiday FLOAT DEFAULT '0', " + "special_holiday_ot FLOAT DEFAULT '0', "+ "special_holiday_nd FLOAT DEFAULT '0', "+ "special_holiday_ot_nd FLOAT DEFAULT '0', " +"month_13th FLOAT DEFAULT '0',"  
	    +"total_days_rendered FLOAT DEFAULT '0',  PRIMARY KEY (time_id)"+")";
		 
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

	public boolean createTimeHoursTable() throws SQLException, ClassNotFoundException {
		String TIMEHRS_TABLE = "CREATE TABLE IF NOT EXISTS timehours_tbl ( " + " timehrs_id int NOT NULL AUTO_INCREMENT , " +" timeno VARCHAR(15), machine VARCHAR(15) , enno VARCHAR(50) NOT NULL, name VARCHAR(50), iomd VARCHAR(50), date_time DATETIME,PRIMARY KEY (timehrs_id)"+")";
		 
	    boolean success = false;
	    
	    Class.forName(jdbcDriver);
	    Connection con = connect();
		 
	    Statement st = null;
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			st.executeUpdate(TIMEHRS_TABLE);
			success= true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    return success;
	}

	public boolean insetTimeCard(String emp_num, float rate, float days,  
			float reg_ot_hrs, float reg_nd, float reg_ot_nd,
			float sunday_hrs, float sunday_ot_hrs, float sunday_nd,float sunday_ot_nd,
			float reg_holiday, float reg_holiday_ot, float reg_holiday_nd, float reg_holiday_ot_nd,
			float special_holiday, float special_holiday_ot, float special_holiday_nd, float special_holiday_ot_nd,
			float month_13th, float total_days_rendered) throws SQLException, ClassNotFoundException{

		String sql = "INSERT INTO payrolltime_tbl "
				+ "(emp_num, rate, days "
				+ ",reg_ot_hrs, reg_nd, reg_ot_nd "
				+ ",sunday_hrs, sunday_ot_hrs, sunday_nd,sunday_ot_nd "
				+ ",reg_holiday, reg_holiday_ot, reg_holiday_nd, reg_holiday_ot_nd"
				+ ",special_holiday, special_holiday_ot, special_holiday_nd, special_holiday_ot_nd"
				+ ",month_13th, total_days_rendered) "
				+ "VALUES ('" + emp_num + "','"+ rate  + "',' "+ days  + "',' "
				+ reg_ot_hrs + "',' " + reg_nd  + "',' "+ reg_ot_nd  + "',' " 
				+ sunday_hrs + "',' " + sunday_ot_hrs  + "',' "+ sunday_nd  + "',' " + sunday_ot_nd  + "',' "
				+ reg_holiday + "',' " + reg_holiday_ot  + "',' "+ reg_holiday_nd  + "',' " + reg_holiday_ot_nd  + "',' "
				+ special_holiday + "',' " + special_holiday_ot  + "',' "+ special_holiday_nd  + "',' " + special_holiday_ot_nd  + "',' "+  month_13th  + "',' "
				+ total_days_rendered + "') ";
		
		
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

	public void listTimeHours(TableView tableview, ObservableList<ObservableList> data) throws ClassNotFoundException, SQLException{
        
        data = FXCollections.observableArrayList();
        Class.forName(jdbcDriver);
	        Connection c = connect();
			
			try{
		           
	            String SQL = "SELECT * from payrolltime_tbl";
	            //ResultSet
	            ResultSet rs = c.createStatement().executeQuery(SQL);

	            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
	                //We are using non property style for making dynamic table
	                final int j = i;                
	                TableColumn col = new TableColumn(payrolltime_label[i]);
	                
	                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
	                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) { 
	                    	 
	                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
	                    }                    
	                });
	               
	                tableview.getColumns().addAll(col); 
	                //System.out.println("Column ["+i+"] ");
	            }


	            while(rs.next()){
	                //Iterate Row
	                ObservableList<String> row = FXCollections.observableArrayList();
	                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
	                    //Iterate Column
	                    row.add(rs.getString(i));
	                    //System.out.println("Row [1] added "+rs.getString(i));
	                }
	                
	                data.add(row);

	            }

	            tableview.setItems(data);
	          }catch(Exception e){
	              e.printStackTrace();
	              System.out.println("Error on Building Data");             
	          }
	        
        
    }
	
	public boolean insetTimeHours(String timeno, String machine, String enno, String name, String iomd, String date_time ) throws SQLException, ClassNotFoundException{

		String sql = "INSERT INTO timehours_tbl "
				+ "(timeno, machine, enno "
				+ ",name, iomd "
				+ ",date_time) "
				+ "VALUES ('" + timeno + "','"+ machine  + "',' "+ enno  + "',' "
				+ name + "',' " + iomd  + "',' " 
				 + date_time + "') ";
		
		
	    boolean success = false;
 
	    Connection con = connect();
 
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
	
	public void listHours(TableView tableview, ObservableList<ObservableList> data) throws ClassNotFoundException, SQLException{
        
        data = FXCollections.observableArrayList();
        Class.forName(jdbcDriver);
	        Connection c = connect();
			
			try{
		           
	            String SQL = "SELECT * from timehours_tbl";
	            //ResultSet
	            ResultSet rs = c.createStatement().executeQuery(SQL);

	            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
	                //We are using non property style for making dynamic table
	                final int j = i;                
	                TableColumn col = new TableColumn(timehours_label[i]);
	                
	                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
	                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) { 
	                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
	                    }                    
	                });
	               
	                tableview.getColumns().addAll(col); 
	                //System.out.println("Column ["+i+"] ");
	            }


	            while(rs.next()){
	                //Iterate Row
	                ObservableList<String> row = FXCollections.observableArrayList();
	                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
	                    //Iterate Column
	                    row.add(rs.getString(i));
	                    //System.out.println("Row [1] added "+rs.getString(i));
	                    
	                }
	                
	                data.add(row);

	            }
	            
	            tableview.setItems(data);
	            //tableview.getColumns().addAll(data);
	          }catch(Exception e){
	              e.printStackTrace();
	              System.out.println("Error on Building Data");             
	          }
	        
        
    }
	
	public void importEmployees(String fname, String lname, String position, String rate, String status) throws SQLException
	{
		 
		Statement s = null;
		// SQL Insert
		String sql = "INSERT INTO employee_tbl "
				+ "(fname,lname,position,rate,status) "
				+ "VALUES ('" +fname + "','"
				+ lname + "'" + ",'"
				+ position + "','"
				+ rate + "','"
				+ status + "') ";
		
		 Connection con = connect();
		 
		    Statement st = null;
		    
			try {
				st = con.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
		    	st.execute(sql); 
				 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		 
	}
}

  
 
