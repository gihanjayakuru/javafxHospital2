/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hospitalaa.Employee;
import hospitalaa.mysqlconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jayakuru
 */
public class ManageDoctorsController implements Initializable {
    @FXML
    private TableView<Employee> doctorTable;
    @FXML
    private TableView<?> ManageTable;
    @FXML
    private JFXTextField DocId;
    @FXML
    private JFXTextField DocName;
    @FXML
    private JFXComboBox<?> DocOpdIpd;
    @FXML
    private JFXComboBox<?> DocWord;
    @FXML
    private JFXComboBox<?> DocPosition;
    @FXML
    private TableColumn<Employee, String> docIdcol;
    @FXML
    private TableColumn<Employee, ?> docNamecol;
    @FXML
    private TableColumn<?, ?> columnId;
    @FXML
    private TableColumn<?, ?> columnName;
    @FXML
    private TableColumn<?, ?> columnOpdIpd;
    @FXML
    private TableColumn<?, ?> columnWord;
    @FXML
    private TableColumn<?, ?> columnPosition;
    @FXML
    private JFXTextField search;
    
    //Initialize observable list to hold out database 
    private ObservableList<Employee>data;
    private mysqlconnect dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc= new mysqlconnect();
        populateTableView();
        // TODO
    }    

    @FXML
    private void AddManage(ActionEvent event) {
    }
    
    
    public void populateTableView() {
//       
        try {
            Connection conn=dc.createConnection();
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `employees`");
            
            while(rs.next()){
                //get String from db,
                data.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        //set cell values
        docIdcol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        docNamecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
       
        
        doctorTable.setItems(null);
        doctorTable.setItems(data);
        
        System.out.print("populate table");
   }
    
}
