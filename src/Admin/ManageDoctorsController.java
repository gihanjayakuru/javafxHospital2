/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import hospitalaa.AddEmployeeController;
import hospitalaa.Employee;
import hospitalaa.mysqlconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private JFXComboBox<String> DocOpdIpd;
    @FXML
    private JFXComboBox<String> DocWord;
    @FXML
    private JFXComboBox<String> DocPosition;
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
    private ObservableList<Employee> data;
    //combobox item list Initialize
    private ObservableList<String> doc1 = FXCollections.observableArrayList("OPD", "IPD");
    private ObservableList<String> doc2 = FXCollections.observableArrayList("word1", "word2", "word3", "word4");
    private ObservableList<String> doc3 = FXCollections.observableArrayList("Head", "junior", "Helper");
    //connection mysql
    private mysqlconnect dc;
    //table
    int index= -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new mysqlconnect();
        populateTableView();

        //combobox items
        DocOpdIpd.setItems(doc1);
        DocWord.setItems(doc2);
        DocPosition.setItems(doc3);

        // TODO
    }

    @FXML
    private void AddManage(ActionEvent event) {
        
        String id = DocId.getText().toString();
        String name = DocName.getText().toString();  
        String oi= DocOpdIpd.getValue().toString();
        String word = DocWord.getValue().toString();
        String position = DocPosition.getValue().toString();
       
        Savadb(id,name, oi,word,position);
        populateTableView();
    }

    public void populateTableView() {
//       
        try {
            Connection conn = dc.createConnection();
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `employees`");

            while (rs.next()) {
                //get String from db,
                data.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        //set cell values
        docIdcol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        docNamecol.setCellValueFactory(new PropertyValueFactory<>("Name"));

        doctorTable.setItems(null);
        doctorTable.setItems(data);

        System.out.print("populate table");
    }

    public void Savadb(String id,String name,String oi,String word,String position) {
        mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `managedoctors`(`id`, `name`, `oi`,`word`,`position`) VALUES (?,?,?,?,?)";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, oi);   
            st.setString(4, word);
            st.setString(5, position);
            
            if(st.executeUpdate()>0)
            {
             System.out.print("savedb success!");  
            }else{
                System.out.print("savedb ....unsuccess!"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            //return false;         
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(id+"\n"+name+"\n"+"\n"+"Fail to Manage doc!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }
        

    }

    @FXML
    private void getDocTbData(MouseEvent event) {
        index = doctorTable.getSelectionModel().getSelectedIndex();
        if(index <=-1){
            return;
        }
    
        DocId.setText(docIdcol.getCellData(index).toString());
        DocName.setText(docNamecol.getCellData(index).toString());
       // DocOpdIpd.setValue(columnOpdIpd.getCellData(index).toString());
        //DocWord.setValue(columnWord.getCellData(index).toString());
        //DocPosition.setValue(columnPosition.getCellData(index).toString());
        
        
    }

    @FXML
    private void getMngDocTbData(MouseEvent event) {
    }

}
