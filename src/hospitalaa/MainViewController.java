/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalaa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class MainViewController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void expand_sidebar(MouseEvent event) {
    }

    @FXML
    private void Open_StaffStatus(MouseEvent event) {
    }

    @FXML
    private void OPen_AddEmployee(MouseEvent event) {
    }

    @FXML
    private void Open_ManageDoctors(MouseEvent event) {
    }

    @FXML
    private void Open_ManageNurses(MouseEvent event) {
    }

    @FXML
    private void log_out(MouseEvent event) {
    }
    
}
