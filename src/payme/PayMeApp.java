package payme;
 

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout.Group;

import payme.model.DBConnection;
import payme.view.Dashboard;
import payme.view.Dashboard.DashboardPanel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;


public class PayMeApp extends Application {

	JLabel statusbar;
	
    public static void main(String args[])
    {
    	 launch(args);
    }
    
    
    @Override
    public void start(final Stage primaryStage)  throws ClassNotFoundException, SQLException  {
    	primaryStage.setTitle("PayMe ");
    	
    	 Application.setUserAgentStylesheet(getClass().getResource("/assets/sample.css").toExternalForm());
    	 
    	Image icon = new Image(getClass().getResourceAsStream("/assets/tasktimer.png"));
    	primaryStage.getIcons().add(icon);
    	primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        
        final GridPane homegrid = new GridPane();
    	homegrid.setAlignment(Pos.TOP_LEFT);
    	homegrid.setHgap(4);
    	homegrid.setVgap(4);
    	homegrid.setPadding(new Insets(2, 2, 2, 2));
    	
    	final GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));
    	
 
    	Button btn = new Button("Sign in");
    	HBox hbBtn = new HBox(10);
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	hbBtn.getChildren().add(btn);
    	grid.add(hbBtn, 1, 4);
    	
    	Text forgotpass = new Text("Forgot your password?");
    	forgotpass.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
    	grid.add(forgotpass, 1, 5);
    	
    	final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
    	final Scene scene = new Scene(grid, 900, 625);
    	primaryStage.setScene(scene);
       	
       
    	Text scenetitle = new Text("PayME - Simple Payroll Application");
    	scenetitle.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 20));
    	grid.add(scenetitle, 0, 0, 2, 1);

    	Label userName = new Label("Username:");
    	userName.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
    	grid.add(userName, 0, 1);

    	final TextField userTextField = new TextField();
    	userTextField.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
    	grid.add(userTextField, 1, 1);

    	Label pw = new Label("Password:");
    	pw.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
    	grid.add(pw, 0, 2);

    	final PasswordField pwBox = new PasswordField();
    	pwBox.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
    	grid.add(pwBox, 1, 2);
    	
        
    	//login
    	btn.setOnAction(new EventHandler<ActionEvent>() {
    		
    	    @Override
    	    public void handle(ActionEvent e) {
    	        actiontarget.setFill(Color.FIREBRICK);
    	        DBConnection c = new DBConnection();

    	    	 boolean created = false;
    	    	 boolean loggedin=false;
    	    	 boolean admincreated = false;
    	    	 String userN = userTextField.getText(); 
    	         String pass = pwBox.getText();
    	    		try {
    					created = c.createEmployeeTable();
    					admincreated = c.createAdminTable();
    					
    						 loggedin = c.login(userN, pass);
 
    					
    				} catch (ClassNotFoundException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				
        	    	 if(created == true && admincreated == true){
            	    	 if(userN.isEmpty() && pass.isEmpty()){
        					 actiontarget.setText("Please enter username and password");
        				 } 
            	    	 else if(loggedin == true)
        	    		 {	
        	    			 actiontarget.setText("Login successful");
        	    			 //primaryStage.hide();
        	    			 BorderPane root = new BorderPane();
        	    			 Dashboard d = new Dashboard();

        	    			 Scene dscene = new Scene(root, 900, 625);
        	    		     primaryStage.setScene(dscene);
        	    		     d.setDashboard(root, homegrid, primaryStage);   
        	    		     
        	    		     
        	    			 /*
        	    			  JFrame mainJFrame = new JFrame("PayMe - Dashboard");
        	                  mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	                  mainJFrame.setTitle("PayMe - Dashboard");
        	                  mainJFrame.setSize(900, 625);
        	                  mainJFrame.setMinimumSize(new Dimension(800, 500));
        	                  mainJFrame.setLocationRelativeTo(null);
        	                  
        	                  JMenuBar menubar = new JMenuBar();
        	                  JMenu filemenu = new JMenu("File");
        	          	      JMenu settingsmenu = new JMenu("Settings");
        	          	      mainJFrame.setJMenuBar(menubar);
        	          	      menubar.add(filemenu);
        	          	      menubar.add(settingsmenu);
        	          	      
        	          	      JMenuItem adminmi= new JMenuItem("Admin");
        	        	      JMenuItem passwordmi = new JMenuItem("Reset Password");
        	        	      final JMenuItem aboutmi = new JMenuItem("About");
        	        	      filemenu.add(adminmi);
        	        	      filemenu.add(passwordmi);
        	        	      settingsmenu.add(aboutmi);
        	          	      
        	 
        	                  BufferedImage image;
  							  try {
  								image = ImageIO.read(getClass().getResource("/assets/tasktimer.png"));
  								mainJFrame.setIconImage(image);
  							  } catch (IOException e1) {
  								// TODO Auto-generated catch block
  								e1.printStackTrace();
  							  }
        	                  DashboardPanel myTabJPanel = new DashboardPanel();
        	                  mainJFrame.add(myTabJPanel, BorderLayout.CENTER);
        	                  mainJFrame.setVisible(true);
	 						*/
        	    		 }else{actiontarget.setText("Login unsuccessful");}  
        	    	 }else{ actiontarget.setText("Employees and Admin table created");} 
	
    	    	} 
    	   
    	});
    	
        primaryStage.show();
    }
    
    
     public void showDashboard(Stage primaryStage){
    	 
     }
 
    
    
}
