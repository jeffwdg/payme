package payme.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;

public class Payroll {
	
	String jdbcDriver = "com.mysql.jdbc.Driver";
    String dbName = "payme";
    private final SimpleStringProperty payNum;
    private final SimpleStringProperty payPeriod;
    private final SimpleStringProperty endPayPeriod;
    private final SimpleStringProperty employeeID;

    public Payroll(String pNum, String pPeriod,String epPeriod, String empID) {
        this.payNum = new SimpleStringProperty(pNum);
        this.payPeriod = new SimpleStringProperty(pPeriod);
        this.endPayPeriod = new SimpleStringProperty(epPeriod);
        this.employeeID = new SimpleStringProperty(empID);
    }

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

    public String getEmployeeID() {
        return employeeID.get();
    }

    public void setEmployeeID(String empID) {
    	employeeID.set(empID);
    }

	

	
}