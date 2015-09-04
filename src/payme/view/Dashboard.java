package payme.view;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import payme.model.DBConnection;
import payme.model.ImportEmployees;
import payme.model.PayrollTime;
 
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

 
public class Dashboard{
	
	public TableView  table;
    private ObservableList<ObservableList> data;
    public TableView  tablehours;
    private ObservableList<ObservableList> datahours;
    String hoursFileName=""; 
    String employeeFileName=""; 
    
	final DBConnection dc = new DBConnection();

	 //sets the dashboard page right after logging in
	@SuppressWarnings("unchecked")
	public void setDashboard(final BorderPane root, final GridPane grid, final Stage stage) throws SQLException{
		
		 VBox topContainer = new VBox();
		 GridPane tab2grid = new GridPane();
		 tab2grid.setAlignment(Pos.TOP_LEFT);
		 tab2grid.setHgap(10);
		 tab2grid.setVgap(10);
		 tab2grid.setPadding(new Insets(5, 5, 5, 5));
	    	
	     BorderPane tab3pane = new BorderPane();
	     tab3pane.setPadding(new Insets(1, 1, 1, 1)); 	
		  
		 MenuBar mainMenu = new MenuBar();   
		 ToolBar toolBar = new ToolBar();  
		 topContainer.getChildren().add(mainMenu);
		 topContainer.getChildren().add(toolBar);
		 
		 root.setTop(topContainer);
		 Menu file = new Menu("Settings");
		 Menu help = new Menu("Help");
		  
		 mainMenu.getMenus().addAll(file, help);
		 
		 TabPane tabPane = new TabPane();
		 tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		 
		 Tab tab1 = new Tab();
         tab1.setText("Dashboard");
         Tab tab2 = new Tab();
         tab2.setText("Employee");
         Tab tab3 = new Tab();
         tab3.setText("Payroll");
         
         HBox hbox = new HBox();
         hbox.getChildren().add(new Label("Tabs"));
         hbox.setAlignment(Pos.CENTER);

         // level 1 tab 1 contents
         tab1.setContent(new Text("Welcome!"));
         
         //level 1 tab 2 contents
         TextField searchUser = new TextField();
     	 searchUser.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
     	 Button searchUserBtn = new Button("Search");
         final Button importCSV = new Button("Import CSV");
         
         //level 1 tab 3 contents
         TextField searchTime = new TextField();
     	 searchTime.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
     	 Button searchTimeBtn = new Button("Search");
     	 Button generatePayroll = new Button("Generate Payroll");
     	 Button computeTimeCard = new Button("Compute Timecard");
         final Button importTimeCard = new Button("Import Hours");
         
         importCSV.addEventHandler(MouseEvent.MOUSE_CLICKED,
        	        new EventHandler<MouseEvent>() {
        	 
             @Override
             public void handle(MouseEvent e) {
            	 final Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.setTitle("Import Employees");
	                dialog.setWidth(300);
	                dialog.setHeight(200);
	                dialog.setResizable(false);
	                dialog.initOwner(stage);
	                VBox dialogVbox = new VBox(20);
	                GridPane importTimeGrid = new GridPane();
	                importTimeGrid.setAlignment(Pos.TOP_LEFT);
	                importTimeGrid.setHgap(4);
	                importTimeGrid.setVgap(4);
	                importTimeGrid.setPadding(new Insets(2, 2, 2, 2));
	            
	                final FileChooser fileChooser = new FileChooser();
	                
	                Label selectEmp = new Label("Please import the employees file .txt file:");
	                final Button selectEmpbtn = new Button("Select File and Import");
	                Button importEmp = new Button("Import Employees");
	                
	                importTimeGrid.add(selectEmp, 3,0);
	                importTimeGrid.add(selectEmpbtn, 3,2);
	               
	                final Desktop desktop = Desktop.getDesktop();               
	                dialogVbox.getChildren().add(importTimeGrid);
	                
					
	                selectEmpbtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
	              	        new EventHandler<MouseEvent>() {

								@Override
								public void handle(MouseEvent arg0) {
									// TODO Auto-generated method stub
									File file = fileChooser.showOpenDialog(dialog);
									configureFileChooser(fileChooser);
									int timeecount = 0;
					            	final String[] col1;
									final String[] col2;
									final String[] col3;
									final String[] col4;
									final String[] col5;
											
									
				                    if (file != null) {
				                        //openFile(file);
				                    	employeeFileName = file.getAbsolutePath(); 
				                    	boolean isCSV = validateFileExtn(employeeFileName);
											
											if(isCSV){
 
												try {
													timeecount = (int) Files.newBufferedReader(Paths.get(employeeFileName)).lines().count();
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												col1 = new String[timeecount];
												col2 = new String[timeecount];
												col3 = new String[timeecount];
												col4 = new String[timeecount];
												col5 = new String[timeecount];
												
												try {
													BufferedReader br = new BufferedReader(new FileReader(file));
													String line;
													int row = 0;
													int y= 0;
													while ((line = br.readLine()) != null) {
														String[] arr = line.split(",");
														col1[y] = arr[0].replace("\"","");
														col2[y] = arr[1].replace("\"","");
														col3[y] = arr[2].replace("\"","");
														col4[y] = arr[3].replace("\"","");
														col5[y] = arr[4].replace("\"","");
														
														dc.importEmployees(col1[y],col2[y], col3[y],col4[y],col5[y]);
														
														row++;
														System.out.print(arr[0].replace("\"",""));
													}
	
													br.close();
												} catch (IOException | SQLException ex) {
													// TODO Auto-generated catch block
													ex.printStackTrace();
												}
												
											}else{
												JOptionPane.showMessageDialog(null,
														"File format is invalid. Make sure it is a .csv file.");
											} 
				                    }
				                    
								}
				 
				                
							 
								private void configureFileChooser(final FileChooser fileChooser) {      
					                    fileChooser.setTitle("View Files");
					                    fileChooser.setInitialDirectory(
					                        new File(System.getProperty("user.home"))
					                    );                 
					                    fileChooser.getExtensionFilters().addAll( 
					                    		new FileChooser.ExtensionFilter("TXT", "*.txt")
					                    );
								}	  
								
	                      
	                            
	               });
	                
	                
	                Scene dialogScene = new Scene(dialogVbox, 300, 200);
	                dialog.setScene(dialogScene);
	                dialog.show();
     			  
             }
         });
         
         importTimeCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
     	        new EventHandler<MouseEvent>() {
        	 
	          @Override
	          public void handle(MouseEvent e) {
	        	  	final Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.setTitle("Import Time Card");
	                dialog.setWidth(300);
	                dialog.setResizable(false);
	                dialog.initOwner(stage);
	                VBox dialogVbox = new VBox(20);
	                GridPane importTimeGrid = new GridPane();
	                importTimeGrid.setAlignment(Pos.TOP_LEFT);
	                importTimeGrid.setHgap(4);
	                importTimeGrid.setVgap(4);
	                importTimeGrid.setPadding(new Insets(2, 2, 2, 2));
	            
	                final FileChooser fileChooser = new FileChooser();
	                
	                Label selectTC = new Label("Please import the biometrics .txt file:");
	                final Button selectTimecard = new Button("Select File");
	                Button importTimeCSV = new Button("Import Timecard");
	                
	                importTimeGrid.add(selectTC, 1,0);
	                importTimeGrid.add(selectTimecard, 2,1);
	                importTimeGrid.add(importTimeCSV, 2,1);
	               
	                final Desktop desktop = Desktop.getDesktop();               
	                dialogVbox.getChildren().add(importTimeGrid);
	                
	                selectTimecard.addEventHandler(MouseEvent.MOUSE_CLICKED,
	              	        new EventHandler<MouseEvent>() {

								@Override
								public void handle(MouseEvent arg0) {
									// TODO Auto-generated method stub
									File file = fileChooser.showOpenDialog(dialog);
									configureFileChooser(fileChooser);
				                   
				                    if (file != null) {
				                        //openFile(file);
				                    	 hoursFileName = file.getAbsolutePath();
				                    	 
				                    }
				                    
								}

							 
								private void configureFileChooser(final FileChooser fileChooser) {      
					                    fileChooser.setTitle("View Files");
					                    fileChooser.setInitialDirectory(
					                        new File(System.getProperty("user.home"))
					                    );                 
					                    fileChooser.getExtensionFilters().addAll( 
					                    		new FileChooser.ExtensionFilter("TXT", "*.txt")
					                    );
								}	                 
	                      
	                            
	               });
	                
	                importTimeCSV.addEventHandler(MouseEvent.MOUSE_CLICKED,
	              	        new EventHandler<MouseEvent>() {	                 
			          
	         	          @Override
	         	          public void handle(MouseEvent e) {
	         	        	 
							String timecard_file = hoursFileName; //"C:/Users/IBM_ADMIN/Desktop/payroll/PayMe/bin/assets/GLG_001.txt";

	         	        	Scanner s = null;
							try {
								s = new Scanner(new FileReader(timecard_file));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int count = 0;
							
							boolean isTXT = validateFileExtnTxt(hoursFileName);
							
							if(isTXT){
								try {
									count = (int) Files.newBufferedReader(Paths.get(timecard_file)).lines().count();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		         	        	
								count-=1;
								
								String[] timeno = new String[count];
		         	        	String[] machine = new String[count];
		         	        	String[] EnNo = new String[count];
		         	        	String[] Name = new String[count];
		         	        	String[] Mode = new String[count];
		         	        	String[] IOMd = new String[count];
		         	        	String[] Date_Time = new String[count];

								//Reads time card text file
								s.nextLine();int y=0;
		         	        	while (s.hasNext()) {
		         	        	    timeno[y] = s.next();
		         	        	    machine[y] = s.next();
		         	        	    EnNo[y] = s.next();
		         	        	    Name[y] = s.next();
		         	        	    Mode[y] = s.next();
		         	        	    IOMd[y] =s.next();
		         	        	    Date_Time[y] = s.next();
		         	        	    
		         	        	    y++;
		         	        	}

		         	        	s.close();
	 
		         	        	DBConnection dt = new DBConnection();
		         	        	String msg = "Error adding timecard";
		         	        	//insert timecard to table timehours_tbl
		         	        	for(int t=0; t<count; t++){
		         	        	 
		         	        		String str = (IOMd[t] + " " + Date_Time[t] ).replace("/", "-");
		         	        		System.out.println(timeno[t]+str);
		         	        		 
		         	        		try {
										dt.insetTimeHours(timeno[t], machine[t], EnNo[t], Name[t], IOMd[t].replace("/", "-"), str);
										msg =  "Timecard added successfully";
										
										
									} catch (ClassNotFoundException | SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
		         	        	}
		         	        	//refresh table content
		         	        	tablehours.getColumns().clear();
		         	        	
								try {
									dt.listHours(tablehours, datahours);
								} catch (ClassNotFoundException | SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}else{
								JOptionPane.showMessageDialog(null,
										"File format is invalid. Make sure it is a .txt file.");
							}
	         	        	 
	         	          }
	                  });
	                
	                Scene dialogScene = new Scene(dialogVbox, 300, 200);
	                dialog.setScene(dialogScene);
	                dialog.show();
	          }
         });
         
       
         table = new TableView<PayrollTime>();
         table.setEditable(false);
         table.setPrefWidth(900); 
         table.setPrefHeight(350);
         table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
         
         tablehours = new TableView<PayrollTime>();
         tablehours.setEditable(true);
         tablehours.setPrefWidth(900); 
         tablehours.setPrefHeight(350);
         tablehours.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
         
         table.widthProperty().addListener(new ChangeListener<Number>()
        		 {
        		     @Override
        		     public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
        		     {	
        		    	 
        		         final TableHeaderRow header = (TableHeaderRow) table.lookup("TableHeaderRow");
        		         header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
        		             @Override
        		             public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        		                 header.setReordering(false);
        		                 
        		             }
        		         });
        		     }
        		 });
         
         stage.widthProperty().addListener(new ChangeListener<Number>() {
             @Override
             public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                 System.out.print(stage.getWidth());
                 table.setPrefWidth(stage.getWidth() / 1.5);
             }
         });
 
         
         try {
				DBConnection dt = new DBConnection();
				dt.listTimeHours(table,data);
				dt.listHours(tablehours,datahours);
				
				//table.setItems(tabledata2);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			}
         
          //table.setItems(tabledata);
         
		  TabPane payrolltabPane = new TabPane();
		  payrolltabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		  BorderPane mainPane = new BorderPane();
		  TableView tv0 = new TableView();
		  TableView tv1 = new TableView();
		  TableView tv2 = new TableView();
		  
		  HBox hbox_hrstab = new HBox(20);
		  hbox_hrstab.getChildren().addAll(computeTimeCard);
	      
		  VBox vbox_hrstab = new VBox(20);
	      vbox_hrstab.setPadding(new Insets(2, 0, 2, 0));
	      vbox_hrstab.setMinWidth(700);
	      vbox_hrstab.getChildren().add(tablehours);
	      
	      BorderPane hrstabpane = new BorderPane();
	      hrstabpane.setPadding(new Insets(1, 1, 1, 1)); 	
	      hrstabpane.setTop(hbox_hrstab);
	      hrstabpane.setLeft(vbox_hrstab);
	      
		  //Create Tabs
		  Tab tabAA = new Tab();
		  tabAA.setText("Hours");
		  tabAA.setContent(hrstabpane);
		  payrolltabPane.getTabs().add(tabAA);
		  
		  Tab tabA = new Tab();
		  tabA.setText("Timecard");
		  tabA.setContent(table);
		  payrolltabPane.getTabs().add(tabA);

		  Tab tabB = new Tab();
		  tabB.setText("Payroll");
		  tabB.setContent(tv2);
		  payrolltabPane.getTabs().add(tabB);
		  
		  
          HBox hboxmenu = new HBox(20);
          hboxmenu.getChildren().addAll(searchTime, searchTimeBtn, importTimeCard, generatePayroll);
         
         // Vbox
         VBox vboxtable = new VBox(20);
         vboxtable.setPadding(new Insets(0, 0, 0, 0));
         vboxtable.setMinWidth(700);
         vboxtable.getChildren().add(payrolltabPane);
         
         //Add contents to grid
         tab2grid.add(searchUser, 0,0);
         tab2grid.add(searchUserBtn, 1,0);
         tab2grid.add(importCSV, 2,0);
         
        
	     tab3pane.setTop(hboxmenu);
	     tab3pane.setLeft(vboxtable);
  
         tab2.setContent(tab2grid);
         tab3.setContent(tab3pane);
         tabPane.getTabs().addAll(tab1, tab2, tab3);
   
         root.setCenter(tabPane);
         root.getChildren().add(grid);

	 }
	private static Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(csv))$)");
	private static Pattern fileExtnPtrnTxt = Pattern.compile("([^\\s]+(\\.(?i)(txt|TXT))$)");
    public static boolean validateFileExtn(String FName){
         
        Matcher mtch = fileExtnPtrn.matcher(FName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
    public static boolean validateFileExtnTxt(String FName){
        
        Matcher mtch = fileExtnPtrnTxt.matcher(FName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
 
   
}
	 
 
