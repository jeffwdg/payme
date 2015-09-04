package payme.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Payroll {
	
 
    private final SimpleStringProperty payNum;
    private final SimpleStringProperty payPeriod;
    private final SimpleStringProperty endPayPeriod;
    //private final SimpleStringProperty employeeID;
    
   
    public Payroll(String pNum, String pPeriod,String epPeriod) {
    	 this.payNum = new SimpleStringProperty(pNum);
         this.payPeriod = new SimpleStringProperty(pPeriod);
         this.endPayPeriod = new SimpleStringProperty(epPeriod);
        //this.employeeID = new SimpleStringProperty(empID);
    }
	 
  
    /*
	public Payroll(String[] x) {
		// TODO Auto-generated constructor stub
		 this.payNum = new SimpleStringProperty(x[0]);
	        this.payPeriod = new SimpleStringProperty(x[1]);
	        this.endPayPeriod = new SimpleStringProperty(x[2]);
	}
     */


	public String getPayNum() {
        return payNum.get();
    }

    public void setPayNum(String pNum) {
    	payNum.set(pNum);
    }

    public String getPayPeriod() {
        return payPeriod.get();
    }

    public void setPayPeriod(String pPeriod) {
    	payPeriod.set(pPeriod);
    }
    public String getEndPayPeriod() {
        return endPayPeriod.get();
    }

    public void setEndPayPeriod(String epPeriod) {
    	endPayPeriod.set(epPeriod);
    }
    /*
    public String getEmployeeID() {
        return employeeID.get();
    }

    public void setEmployeeID(String empID) {
    	employeeID.set(empID);
    }*/

	

	
}