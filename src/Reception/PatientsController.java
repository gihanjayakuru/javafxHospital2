/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reception;

import Admin.AddEmployeeController;
import Admin.Employee;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jayakuru
 */
public class PatientsController implements Initializable {
    @FXML
    private JFXTextField Pid;
    @FXML
    private JFXTextField Pname;
    @FXML
    private JFXTextField Gname;
    @FXML
    private JFXTextField gid;
    @FXML
    private JFXTextField Gcontact;
    @FXML
    private TableColumn<Patient, String> PatientIdCol;
    @FXML
    private TableColumn<Patient, String> PatientNameCol;
    @FXML
    private TableColumn<Patient, String> GardienNameCol;
    @FXML
    private TableColumn<Patient, String> GardienIdCol;
    @FXML
    private TableColumn<Patient, String> GardienContactCol;
    @FXML
    private TableColumn<Patient, String> AddressCol;
    @FXML
    private JFXTextArea Address;
    @FXML
    private TableView<Patient> PatientTable;
    
    //Initialize observable list to hold out database 
    private ObservableList<Patient>data;
    
    private mysqlconnect dc;
    
    static String gender;
    int index= -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc= new mysqlconnect();
        populateTableView();
       

       //populateTableView();
    }    

    @FXML
    private void AddPatients(ActionEvent event) {
        
        mysqlconnect sqlcon=new mysqlconnect();
        
         String Pa_id= Pid.getText().toString();
         String Pa_name=Pname.getText().toString();
         String Ga_name=Gname.getText().toString();
         String Ga_id =gid.getText().toString();
         String Ga_contact =Gcontact.getText().toString();
         String address =Address.getText().toString();
         
         SaveDB(Pa_id, Pa_name, Ga_name, Ga_id, Ga_contact, address);
         populateTableView();
         
    }

    @FXML
    private void EditPatient(ActionEvent event) {
        mysqlconnect sqlcon=new mysqlconnect();
        
        String Pa_id= Pid.getText().toString();
        String Pa_name=Pname.getText().toString();
        String Ga_name=Gname.getText().toString();
        String Ga_id =gid.getText().toString();
        String Ga_contact =Gcontact.getText().toString();
        String address =Address.getText().toString();
         
        EditDB(Pa_id, Pa_name, Ga_name, Ga_id, Ga_contact, address);
        populateTableView();
    }

    @FXML
    private void RemovePatient(ActionEvent event) {
        
        mysqlconnect sqlcon=new mysqlconnect();
        String Pa_id= Pid.getText().toString();
        DeleteDB(Pa_id);
        populateTableView();
        
    }
    
     private void EditDB(String Pa_id, String Pa_name, String Ga_name, String Ga_id, String Ga_contact, String address){
      
      
      mysqlconnect myconnection =new mysqlconnect();
      PreparedStatement st;
        ResultSet rs;
        String addQuery="UPDATE `patients` SET `Pa_name`=?,`Ga_name`=?,`Ga_id`=?,`Ga_contact`=?,`adress`=? WHERE `Pa_id`=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, Pa_name);
            st.setString(2, Ga_name);
            st.setString(3, Ga_id);
            st.setString(4, Ga_contact);
            st.setString(5, address);
            st.setString(6, Pa_id);    
            
            
            if(st.executeUpdate()>0)
            {
              
            }else{
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            
        }

  
  }
    private void populateTableView() {
//       
        try {
            Connection conn=dc.createConnection();
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `patients`");
            
            while(rs.next()){
                //get String from db,
                data.add(new Patient(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        //set cell values    
        PatientIdCol.setCellValueFactory(new PropertyValueFactory<>("PId"));
        PatientNameCol.setCellValueFactory(new PropertyValueFactory<>("PName"));
        GardienNameCol.setCellValueFactory(new PropertyValueFactory<>("GName"));
        GardienIdCol.setCellValueFactory(new PropertyValueFactory<>("GId"));
        GardienContactCol.setCellValueFactory(new PropertyValueFactory<>("GId"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        
        PatientTable.setItems(null);
        PatientTable.setItems(data);
        
        System.out.print("populate table");
   }
    
    
    public void SaveDB(String Pa_id, String Pa_name, String Ga_name, String Ga_id, String Ga_contact,String address){
  
      mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `patients`(`Pa_id`, `Pa_name`, `Ga_name`, `Ga_id`, `Ga_contact`, `adress`) VALUES (?,?,?,?,?,?)";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, Pa_id);
            st.setString(2, Pa_name);
            st.setString(3, Ga_name);   
            st.setString(4, Ga_id);
            st.setString(5, Ga_contact);
            st.setString(6, address);
            
            
            if(st.executeUpdate()>0)
            {
             System.out.print("savedb success!");  
            }else{
                System.out.print("savedb ....unsuccess!"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
            
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(Pa_id+"\n"+Pa_name+"\n"+"\n"+"Fail to ADD patients!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }
        
  
  }
    
    private void DeleteDB(String Pa_id){
  
      mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="DELETE FROM `patients` WHERE `Pa_id`=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, Pa_id);
            
            
            if(st.executeUpdate()>0)
            {
             System.out.print("savedb success!");  
            }else{
                System.out.print("savedb ....unsuccess!"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
            
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(Pa_id+"\n"+"Fail to Delete Employee!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }
    
    
}

    @FXML
    private void getTable(MouseEvent event) {
        index = PatientTable.getSelectionModel().getSelectedIndex();
        if(index <=-1){
            return;
        }
        
        Pid.setText(PatientIdCol.getCellData(index).toString());
        Pname.setText(PatientNameCol.getCellData(index).toString());
        Gname.setText(GardienNameCol.getCellData(index).toString());
        gid.setText(GardienIdCol.getCellData(index).toString());
        Gcontact.setText(GardienContactCol.getCellData(index).toString());
        Address.setText(AddressCol.getCellData(index).toString());
        
        
    }


}
