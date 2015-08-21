package payme.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import payme.model.ImportEmployees;
 
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
     
 

	 

 
}
