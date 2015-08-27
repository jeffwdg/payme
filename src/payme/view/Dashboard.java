package payme.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.GroupLayout.Group;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import payme.model.DBConnection;
import payme.model.ImportEmployees;
import payme.model.Payroll;
 
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class Dashboard {
	
	public TableView<Payroll> table;
	public final ObservableList<Payroll> tabledata =
    		 FXCollections.observableArrayList(
    	            new Payroll("July 20", "07/1/2015","07/15/2015", "jacob.smith@example.com"),
    	            new Payroll("August 5", "07/16/2015", "07/31/2015","isabella.johnson@example.com"),
    	            new Payroll("August 20", "08/1/2015","08/15/2015", "ethan.williams@example.com"),
    	            new Payroll("September 5", "08/16/2015","08/31/2015", "emma.jones@example.com"),
    	            new Payroll("September 20", "09/1/2015","09/15/2015", "michael.brown@example.com")
    	     );
 
	 //sets the dashboard page right after logging in
	public void setDashboard(final BorderPane root, final GridPane grid, final Stage stage){
		 VBox topContainer = new VBox();
		 GridPane tab2grid = new GridPane();
		 tab2grid.setAlignment(Pos.TOP_LEFT);
		 tab2grid.setHgap(10);
		 tab2grid.setVgap(10);
		 tab2grid.setPadding(new Insets(25, 25, 25, 25));
	    	
	     BorderPane tab3pane = new BorderPane();
	     tab3pane.setPadding(new Insets(5, 5, 5, 5)); 	
		  
		 MenuBar mainMenu = new MenuBar();   
		 ToolBar toolBar = new ToolBar();  
		 topContainer.getChildren().add(mainMenu);
		 topContainer.getChildren().add(toolBar);
		 
		 
		 root.setTop(topContainer);
		 Menu file = new Menu("File");
		 Menu edit = new Menu("Edit");
		 Menu help = new Menu("Help");
		  
		 mainMenu.getMenus().addAll(file, edit, help);
		 
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
         
         //tab 1 contents
         tab1.setContent(new Text("Welcome!"));
         
         //tab 2 contents
         TextField searchUser = new TextField();
     	 searchUser.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
     	 Button searchUserBtn = new Button("Search");
         final Button importCSV = new Button("Import CSV");
         
         //tab 3 contents
         TextField searchTime = new TextField();
     	 searchTime.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
     	 Button searchTimeBtn = new Button("Search");
     	 Button generatePayroll = new Button("Generate Payroll");
         final Button importTimeCard = new Button("Import Timecard");
         
         importCSV.addEventHandler(MouseEvent.MOUSE_CLICKED,
        	        new EventHandler<MouseEvent>() {
        	 
             @Override
             public void handle(MouseEvent e) {
            	 ImportEmployees form = new ImportEmployees();
     			 form.setVisible(true);
             }
         });
         
         importTimeCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
     	        new EventHandler<MouseEvent>() {
        	 
	          @Override
	          public void handle(MouseEvent e) {
	        	  final Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.setTitle("Import Time Card");
	                dialog.initOwner(stage);
	                VBox dialogVbox = new VBox(20);
	                GridPane importTimeGrid = new GridPane();
	                importTimeGrid.setAlignment(Pos.TOP_LEFT);
	                importTimeGrid.setHgap(4);
	                importTimeGrid.setVgap(4);
	                importTimeGrid.setPadding(new Insets(2, 2, 2, 2));
	            	
	                Label timeStartperiod = new Label("Timecard Start Period:");
	                final DatePicker tcStartDatePicker = new DatePicker();
	                tcStartDatePicker.setVisible(true);
	                
	                Label timeEndperiod = new Label("Timecard End Period:");
	                final DatePicker tcEndDatePicker = new DatePicker();
	                tcEndDatePicker.setVisible(true);
	                
	                Button importTimeCSV = new Button("Import Timecard");
	                
	                importTimeGrid.add(timeStartperiod, 0, 0);
	                importTimeGrid.add(tcStartDatePicker, 1,0);
	                importTimeGrid.add(timeEndperiod, 0, 1);
	                importTimeGrid.add(tcEndDatePicker, 1,1);
	                importTimeGrid.add(importTimeCSV, 1,2);
	                LocalDate sdate = null;
	                
	                if (tcStartDatePicker != null  ) {
	                	sdate = tcStartDatePicker.getValue();
	                	LocalDate edate = tcEndDatePicker.getValue();
	                }  
	                
	                
	                
	              
	                final String startDate = sdate.toString();
		            //final String endDate  = edate.toString();
		               
	                dialogVbox.getChildren().add(importTimeGrid);
	                importTimeCSV.addEventHandler(MouseEvent.MOUSE_CLICKED,
	              	        new EventHandler<MouseEvent>() {
		                 
			          
	         	          @Override
	         	          public void handle(MouseEvent e) {
	         	        	  DBConnection db = new DBConnection();
	         	        	  try {
								 db.insetTimeCard(startDate, startDate);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	         	          }
	                  });
	                
	                Scene dialogScene = new Scene(dialogVbox, 300, 200);
	                dialog.setScene(dialogScene);
	                dialog.show();
	          }
         });
         
        
         

         
         table = new TableView<Payroll>();
         table.setEditable(false);
         
         TableColumn<Payroll, String>  payNumCol = new TableColumn<Payroll, String>("Payroll #");
         payNumCol.setCellValueFactory(new PropertyValueFactory<Payroll, String>("payNum"));
         TableColumn<Payroll, String> payPeriodCol = new TableColumn<Payroll, String>("Start Period");
         payPeriodCol.setCellValueFactory(new PropertyValueFactory<Payroll, String>("payPeriod"));
         TableColumn<Payroll, String> endPayPeriodCol = new TableColumn<Payroll, String>("End Period");
         endPayPeriodCol.setCellValueFactory(new PropertyValueFactory<Payroll, String>("endPayPeriod"));
         TableColumn<Payroll, String> employeeIDCol = new TableColumn<Payroll, String>("EmployeeID");
         employeeIDCol.setCellValueFactory(new PropertyValueFactory<Payroll, String>("employeeID"));

         
         table.setPrefWidth(800);
         table.setPrefHeight(350);
         table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
 
         table.setItems(tabledata);
		 table.getColumns().setAll(payNumCol, payPeriodCol, endPayPeriodCol, employeeIDCol);
  
         
		  TabPane payrolltabPane = new TabPane();
		  BorderPane mainPane = new BorderPane();
		  TableView tv1 = new TableView();
		  TableView tv2 = new TableView();
		  
		  //Create Tabs
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
         vboxtable.setPadding(new Insets(5, 5, 5, 5));
         vboxtable.setMinWidth(900);
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
	
	
 
	 
	  
}
	 
 
