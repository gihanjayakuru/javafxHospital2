/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Jayakuru
 */
public class EmployeeStatusController implements Initializable {
    @FXML
    private TableView<?> StatusTable;
    @FXML
    private TableColumn<?, ?> columnId;
    @FXML
    private TableColumn<?, ?> columnName;
    @FXML
    private TableColumn<?, ?> columnActive;
    @FXML
    private JFXTextField Search;
    @FXML
    private JFXRadioButton Active;
    @FXML
    private JFXRadioButton NotActive;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
