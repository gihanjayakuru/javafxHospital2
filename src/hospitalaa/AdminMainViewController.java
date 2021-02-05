/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalaa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jayakuru
 */
public class AdminMainViewController implements Initializable {
    
    private Label label;
    @FXML
    private HBox parent;
    @FXML
    private VBox side_bar;
    @FXML
    private VBox sidebar;
    
    boolean isExpand=false;
    @FXML
    private AnchorPane contentPane;
    
    public static AnchorPane temporaryPane;
    @FXML
    private BorderPane borderpane;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        temporaryPane = contentPane;
        // TODO
    }    

    @FXML
    private void expand_sidebar(MouseEvent event) {
        if(isExpand){
            side_bar.setPrefWidth(250);
            isExpand=false;
        }else{
            side_bar.setPrefWidth(50);
            isExpand=true;
        }
    }

    @FXML
    private void log_out(MouseEvent event) {
    }

    @FXML
    private void Open_StaffStatus(MouseEvent event) {
        //switchPane("/hospitalaa/EmployeeStatus");
        loadui("/Admin/EmployeeStatus");
    }

    @FXML
    private void OPen_AddEmployee(MouseEvent event) {
        loadui("/Admin/AddEmployee");
    }

    @FXML
    private void Open_ManageDoctors(MouseEvent event) {
        //ManageDoctors
        //root =FXMLLoader.load(getClass().getResource(ui+".fxml"));
        loadui("/Admin/ManageDoctors");
    }

    @FXML
    private void Open_ManageNurses(MouseEvent event) {
         loadui("/Admin/ManageNurses");
    }
    
    
    
//    private void switchPane(String pane){
//        try {
//            AdminMainViewController.temporaryPane.getChildren().clear();
//            StackPane pane2= FXMLLoader.load(getClass().getResource(pane));
//            
//            ObservableList<Node> elements = pane2.getChildren();
//            AdminMainViewController.temporaryPane.getChildren().setAll(elements);
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(AdminMainViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    private void loadui(String ui){
        Parent root=null;
        
        try {
            root =FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
        
    }
//    
    
    
    
}
