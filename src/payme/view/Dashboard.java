package payme.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.net.URL;

import javax.swing.GroupLayout.Group;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import payme.model.ImportEmployees;
 
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
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
import javafx.stage.Stage;
 

public class Dashboard {
	
	static public class DashboardPanel extends JPanel {
        
        public DashboardPanel(){
            super(new GridLayout(1, 1));
 
            JTabbedPane jTabbedPane = new JTabbedPane();
             
            JPanel jPaneA = new JPanel();
            JLabel jLabelA = new JLabel("Dashboard");
            jPaneA.add(jLabelA);
             
            JPanel jPaneB = new JPanel();
            JButton jButton = new JButton("Import CSV Employees");
            jPaneB.add(jButton);
            
            jButton.addActionListener(new ActionListener(){
 
                @Override
                public void actionPerformed(ActionEvent e) {
                	ImportEmployees form = new ImportEmployees();
        			form.setVisible(true);
                }
 
            });
            
            
             
            JPanel jPaneC = new JPanel();
            JButton buttonExit = new JButton("Exit");
            buttonExit.addActionListener(new ActionListener(){
 
                @Override
                public void actionPerformed(ActionEvent e) {
                   // System.exit(0);
                }
 
            });
            jPaneC.add(buttonExit);
             
            jTabbedPane.addTab("Dashboard", jPaneA);
            jTabbedPane.addTab("Employee", jPaneB);
            jTabbedPane.addTab("Payroll", jPaneC);
            
           
            
            add(jTabbedPane);
        }
         
    }
	
	
	 //sets the dashboard page right after logging in
	 public void setDashboard(final BorderPane root, final GridPane grid, final Stage stage){
		 VBox topContainer = new VBox();
		 GridPane tab2grid = new GridPane();
	    	grid.setAlignment(Pos.TOP_LEFT);
	    	grid.setHgap(10);
	    	grid.setVgap(10);
	    	grid.setPadding(new Insets(25, 25, 25, 25));
		  
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
         hbox.getChildren().add(new Label("Tab"));
         hbox.setAlignment(Pos.CENTER);
         tab1.setContent(new Text("Welcome!"));
         
         TextField searchUser = new TextField();
     	 searchUser.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
     	 Button searchUserBtn = new Button("Search");
         final Button importCSV = new Button("Import CSV");
         
         importCSV.addEventHandler(MouseEvent.MOUSE_CLICKED,
        	        new EventHandler<MouseEvent>() {
        	 
             @Override
             public void handle(MouseEvent e) {
            	 ImportEmployees form = new ImportEmployees();
     			 form.setVisible(true);
             }
        });
         
         tab2grid.add(searchUser, 0,0);
         tab2grid.add(searchUserBtn, 1,0);
         tab2grid.add(importCSV, 2,0);
         
         tab2.setContent(tab2grid);
         tab3.setContent(hbox);
         tabPane.getTabs().addAll(tab1, tab2, tab3);
   
         root.setCenter(tabPane);
         root.getChildren().add(grid);

         
 
	 }
	 

	 

 
}
