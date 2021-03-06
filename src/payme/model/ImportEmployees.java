package payme.model;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class ImportEmployees extends JFrame {
		
		private JTable table;
	    String dbName = "payme";

		public ImportEmployees() {

			// Create Form Frame
			super("Import CSV Employees");
			setSize(668, 345);
			setLocation(500, 280);
			setLocationRelativeTo(null);
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);

			// Label Result
			final JLabel lblResult = new JLabel("Result", JLabel.CENTER);
			lblResult.setBounds(150, 22, 370, 14);
			getContentPane().add(lblResult);

			// Table
			table = new JTable();
			getContentPane().add(table);
			//table.setMinimumSize(new Dimension(400,400));
			
			// Table Model
			final DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.addColumn("fname");
			model.addColumn("lname");
			model.addColumn("position");
			model.addColumn("hr_rate");
			model.addColumn("ot_rate");
 
			// ScrollPane
			JScrollPane scroll = new JScrollPane(table);
			scroll.setBounds(84, 98, 506, 79);
			scroll.setMinimumSize(new Dimension(600, 500));
			getContentPane().add(scroll);


			// Create Button Open JFileChooser
			JButton btnButton = new JButton("Open File Chooser");
			btnButton.setBounds(268, 47, 135, 23);
			btnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					JFileChooser fileopen = new JFileChooser();
					FileFilter filter = new FileNameExtensionFilter(
							"Text/CSV file", "txt", "csv");
					fileopen.addChoosableFileFilter(filter);

					int ret = fileopen.showDialog(null, "Choose file");

					if (ret == JFileChooser.APPROVE_OPTION) {

						// Read Text file
						File file = fileopen.getSelectedFile();
						boolean isCSV = validateFileExtn(fileopen.getSelectedFile().toString());
						
						if(isCSV){
					
							try {
								BufferedReader br = new BufferedReader(new FileReader(
										file));
								String line;
								int row = 0;
								while ((line = br.readLine()) != null) {
									String[] arr = line.split(",");
									model.addRow(new Object[0]);
									model.setValueAt(arr[0].replace("\"","") , row, 0);
									model.setValueAt(arr[1].replace("\"","") , row, 1);
									model.setValueAt(arr[2].replace("\"","") , row, 2);
									model.setValueAt(arr[3].replace("\"","") , row, 3);
									model.setValueAt(arr[4].replace("\"","") , row, 4);
									//model.setValueAt(arr[5], row, 5);
									row++;
								}
								br.close();
							} catch (IOException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
							
						}else{
							JOptionPane.showMessageDialog(null,
									"File format is invalid. Make sure it is a .csv file.");
						}

						//lblResult.setText(fileopen.getSelectedFile().toString());
					}

				}
			});
			getContentPane().add(btnButton);
			
			// Button Save
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SaveData(); // save Data
					 
				}
			});
			
			btnSave.setBounds(292, 228, 89, 23);
			getContentPane().add(btnSave);

		}
		
		private static Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(csv))$)");
	     
	    public static boolean validateFileExtn(String userName){
	         
	        Matcher mtch = fileExtnPtrn.matcher(userName);
	        if(mtch.matches()){
	            return true;
	        }
	        return false;
	    }
 
		private void SaveData()
		{
			Connection connect = null;
			Statement s = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");

				connect = DriverManager.getConnection(""
						+ "jdbc:mysql://localhost/"
						+ dbName
						+ "?user=root&password=root");

				s = connect.createStatement();
				
				for(int i = 0; i<table.getRowCount();i++)
				{
					//String d_id = table.getValueAt(i, 0).toString();
					String d_fname = table.getValueAt(i, 0).toString();
					String d_lname = table.getValueAt(i, 1).toString();
					String d_position = table.getValueAt(i, 2).toString();
					String d_hr_rate = table.getValueAt(i, 3).toString();
					String d_ot_rate = table.getValueAt(i, 4).toString();
					
					// SQL Insert

					String sql = "INSERT INTO employee_tbl "
							+ "(fname,lname,position,hr_rate,ot_rate) "
							+ "VALUES ('" + d_fname + "','"
							+ d_lname + "'" + ",'"
							+ d_position + "','"
							+ d_hr_rate + "','"
							+ d_ot_rate + "') ";
					s.execute(sql);
				}
					
				JOptionPane.showMessageDialog(null,
						"Import Data Successfully");


			} catch (Exception ex) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, ex.getMessage());
				
				ex.printStackTrace();
			}

			try {
				if (s != null) {
					s.close();
					connect.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		/*
		 * 
	                Label payDatelbl = new Label("Pay Date:");
	                final TextField payDate = new TextField();
	            	final ObservableList<String> images=FXCollections.observableArrayList("assets/tasktimer.png","tasktimer.png","tasktimer.png","tasktimer.png","tasktimer.png");

	            	final Color bg = Color.web("00aba9");
	                Label timeStartperiod = new Label("Start Period:");
	                final DatePicker tcStartDatePicker = new DatePicker();
	                tcStartDatePicker.setShowWeekNumbers(false);
	                tcStartDatePicker.setDayCellFactory(new Callback<DatePicker,DateCell>(){

	    	           @Override
	    	           public DateCell call(DatePicker param) {
	    	           
	    	             return new DateCell(){
	    	             @Override
	    	             public void updateItem(LocalDate item, boolean empty){
	    	             super.updateItem(item, empty);
	    	       
	    	             if (empty || item == null) {
	    	                 setText(null);
	    	                 setGraphic(null);
	    	                
	    	                 } else {
	    	                
	    	                StackPane cell_pane = new StackPane();
	    	               
	    	                Random r=new Random();
	    	               
	    	                ImageView image_view=new ImageView("file:"+images.get(r.nextInt(images.size())));
	    	               
	    	                Circle circle=new Circle(20);
	    	                circle.setFill(Color.WHITE);
	    	                Label label=new Label();
	    	                label.setText(getText());
	    	                label.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12)); 
	    	                label.setTextFill(bg);
	    	                label.setLabelFor(circle);
	    	               
	    	                StackPane.setAlignment(image_view, Pos.CENTER_RIGHT);
	    	                StackPane.setMargin(image_view,new Insets(0,20,55,45));

	    	                cell_pane.getChildren().addAll(circle,label,image_view);

	    	                //item.get(ChronoField.DAY_OF_WEEK) returns an int from 1 to 7(Monday-Sunday)
	    	                //DayOfWeek.of(int) return the name of the day of week. type ENUM.

	    	                DayOfWeek day=DayOfWeek.of(item.get(ChronoField.DAY_OF_WEEK));
	    	               
	    	               
	    	                if(day.equals(DayOfWeek.SATURDAY)){
	    	                    setStyle("-fx-background-color:white;");//saturday cells blue background
	    	                }else if(day.equals(DayOfWeek.SUNDAY)){
	    	                    setStyle("-fx-background-color:#00aba9; -fx-text-fill: white;");//sunday cells green background
	    	                }else{
	    	                   setStyle("-fx-background-color:white;"); //weekdays grey
	    	                }
	    	                setGraphic(cell_pane);
	    	                setText("");//dont show any text in the cells
	    	                
	    	                 }

	    	            }
	    	             };
	    	       };
	    	       });

	                tcStartDatePicker.setVisible(true);
	                
	                Label timeEndperiod = new Label("End Period:");
	                final DatePicker tcEndDatePicker = new DatePicker();
	                tcEndDatePicker.setShowWeekNumbers(false);
	                tcEndDatePicker.setDayCellFactory(new Callback<DatePicker,DateCell>(){

	    	           @Override
	    	           public DateCell call(DatePicker param) {
	    	           
	    	             return new DateCell(){
	    	             @Override
	    	             public void updateItem(LocalDate item, boolean empty){
	    	             super.updateItem(item, empty);
	    	       
	    	             if (empty || item == null) {
	    	                 setText(null);
	    	                 setGraphic(null);
	    	                
	    	                 } else {
	    	                
	    	                StackPane cell_pane = new StackPane();
	    	               
	    	                Random r=new Random();
	    	               
	    	                ImageView image_view=new ImageView("file:"+images.get(r.nextInt(images.size())));
	    	               
	    	                Circle circle=new Circle(20);
	    	                circle.setFill(Color.WHITE);
	    	                
	    	                Label label=new Label();
	    	                label.setText(getText());
	    	                label.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 12)); 
	    	                label.setTextFill(bg);
	    	                label.setLabelFor(circle);
	    	               
	    	                StackPane.setAlignment(image_view, Pos.CENTER_RIGHT);
	    	                StackPane.setMargin(image_view,new Insets(0,20,55,45));

	    	                cell_pane.getChildren().addAll(circle,label,image_view);

	    	                //item.get(ChronoField.DAY_OF_WEEK) returns an int from 1 to 7(Monday-Sunday)
	    	                //DayOfWeek.of(int) return the name of the day of week. type ENUM.

	    	                DayOfWeek day=DayOfWeek.of(item.get(ChronoField.DAY_OF_WEEK));
	    	               
	    	               
	    	                if(day.equals(DayOfWeek.SATURDAY)){
	    	                    setStyle("-fx-background-color:white;");//saturday cells blue background
	    	                }else if(day.equals(DayOfWeek.SUNDAY)){
	    	                    setStyle("-fx-background-color:#00aba9; -fx-text-fill: white;");//sunday cells green background
	    	                }else{
	    	                   setStyle("-fx-background-color:white;"); //weekdays grey
	    	                }
	    	                setGraphic(cell_pane);
	    	                setText("");//dont show any text in the cells
	    	                
	    	                 }

	    	            }
	    	             };
	    	       };
	    	       });

	                tcEndDatePicker.setVisible(true);
		 * */

	}
