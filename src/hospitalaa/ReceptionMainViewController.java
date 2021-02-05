/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalaa;

import static hospitalaa.AdminMainViewController.temporaryPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jayakuru
 */
public class ReceptionMainViewController implements Initializable {
    @FXML
    private HBox parent;
    @FXML
    private VBox side_bar;
    @FXML
    private VBox sidebar;
    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane contentPane;
    
    boolean isExpand=false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        temporaryPane = contentPane;
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
    private void Open_RegisterPatient(MouseEvent event) {
        loadui("/Reception/Patients");
    }

    @FXML
    private void OPen_Attendance(MouseEvent event) {
    }

    @FXML
    private void Open_AddToClinic(MouseEvent event) {
    }

    @FXML
    private void log_out(MouseEvent event) {
    }
    
    
    private void loadui(String ui){
        Parent root=null;
        
        try {
            root =FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ReceptionMainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
        
    }
    
}
