/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
    private TableView<MngDoctors> ManageTable;
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
    private TableColumn<MngDoctors, String> columnId;
    @FXML
    private TableColumn<MngDoctors, String> columnName;
    @FXML
    private TableColumn<MngDoctors, String> columnOpdIpd;
    @FXML
    private TableColumn<MngDoctors, String> columnWord;
    @FXML
    private TableColumn<MngDoctors, String> columnPosition;
    @FXML
    private JFXTextField search;

    //Initialize observable list to hold out database 
    private ObservableList<Employee> data;
    private ObservableList<MngDoctors> data2;
    //combobox item list Initialize
    private ObservableList<String> doc1 = FXCollections.observableArrayList("OPD", "IPD");
    private ObservableList<String> doc2 = FXCollections.observableArrayList("word1", "word2", "word3", "word4");
    private ObservableList<String> doc3 = FXCollections.observableArrayList("Head", "junior", "Helper");
    //connection mysql
    private mysqlconnect dc;
    //table
    int index = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new mysqlconnect();
        populateTableView();
        populateTableView2();

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
        String oi = DocOpdIpd.getValue().toString();
        String word = DocWord.getValue().toString();
        String position = DocPosition.getValue().toString();

        Savedb(id, name, oi, word, position);

        populateTableView2();
    }

    private void populateTableView() {
//       
        try {
            Connection conn = dc.createConnection();
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `employees` WHERE position='Doctor'");//SELECT * FROM employee WHERE position='Doctor'

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

    private void populateTableView2() {
//       
        try {
            Connection conn = dc.createConnection();
            data2 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `managedoctors`");

            while (rs.next()) {
                //get String from db,
                data2.add(new MngDoctors(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        //set cell values
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnOpdIpd.setCellValueFactory(new PropertyValueFactory<>("oi"));
        columnWord.setCellValueFactory(new PropertyValueFactory<>("word"));
        columnPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));

        ManageTable.setItems(null);
        ManageTable.setItems(data2);

        System.out.print("populate table Manage");
    }

    

    @FXML
    private void getDocTbData(MouseEvent event) {
        index = doctorTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        DocId.setText(docIdcol.getCellData(index).toString());
        DocName.setText(docNamecol.getCellData(index).toString());

    }

    @FXML
    private void getMngDocTbData(MouseEvent event) {
        index = ManageTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        DocId.setText(columnId.getCellData(index).toString());
        DocName.setText(columnName.getCellData(index).toString());
        DocOpdIpd.setValue(columnOpdIpd.getCellData(index).toString());
        DocWord.setValue(columnWord.getCellData(index).toString());
        DocPosition.setValue(columnPosition.getCellData(index).toString());
    }

    @FXML
    private void EditManage(ActionEvent event) {
        
        String id = DocId.getText().toString();
        //String name = DocName.getText().toString();
        String oi = DocOpdIpd.getValue().toString();
        String word = DocWord.getValue().toString();
        String position = DocPosition.getValue().toString();

        Editdb(id, oi, word, position);

        populateTableView2();

    }

    @FXML
    private void RemoveManage(ActionEvent event) {
        String id = DocId.getText().toString();
        

        Removedb(id);

        populateTableView2();
    }
    
    private void Savedb(String id,String name,String oi,String word,String position){
  
      mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `managedoctors`(`id`, `name`, `oi`, `word`, `position`) VALUES (?,?,?,?,?)";
        
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
            Logger.getLogger(ManageDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
            
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(id+"\n"+name+"\n"+"\n"+"Fail to SaveDB!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }
   
       
    }

    
    private void Removedb(String id) {
        mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="DELETE FROM `managedoctors` WHERE `id`=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, id);
            
            
            if(st.executeUpdate()>0)
            {
             System.out.print("savedb success!");  
            }else{
                System.out.print("savedb ....unsuccess!"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
            
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(id+"\n"+"Fail to Delete managedoctors!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }

    }

    private void Editdb(String id, String oi, String word, String position) {
        mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="UPDATE `managedoctors` SET `oi`=?,`word`=?,`position`=? WHERE id=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            
            st.setString(1, oi);
            st.setString(2, word);
            st.setString(3, position);
            st.setString(4, id); 
           
            
            if(st.executeUpdate()>0)
            {
             System.out.print("Editdb success!");  
            }else{
                System.out.print("Editdb ....unsuccess!"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageDoctorsController.class.getName()).log(Level.SEVERE, null, ex);
  
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(id+"\n"+"Fail to Editdb!"+"\n"+"Check Valid ID");
            alert.show();
   
        }
        
    }

}
