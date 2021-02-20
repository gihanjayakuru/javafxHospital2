/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reception;

import Admin.AddEmployeeController;
import Admin.Employee;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import hospitalaa.mysqlconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class AddClinicController implements Initializable {
    @FXML
    private JFXTextField PID;
    @FXML
    private JFXComboBox<String> Clinic;
    @FXML
    private JFXDatePicker Date;
    @FXML
    private JFXComboBox<String> TimeS;
    @FXML
    private TableView<clinic> ClinicTable;
    @FXML
    private TableColumn<clinic, String> columnPid;
    @FXML
    private TableColumn<clinic, String> columnName;
    @FXML
    private TableColumn<clinic, String> columnDate;
    @FXML
    private TableColumn<clinic, String> columnTime;
    
    //Initialize observable list to hold out database 
    private ObservableList<clinic>data;
    
    private ObservableList<String> clinic = FXCollections.observableArrayList("Clinic 1","Clinic 2","Clinic 3","Clinic 4");
    private ObservableList<String> time = FXCollections.observableArrayList("8-10","10-12","1-3");
    
     private mysqlconnect dc;
     
    
    int index = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Clinic.setItems(clinic);
        TimeS.setItems(time);
        
        dc= new mysqlconnect();
        populateTableView();
    }    

    @FXML
    private void AddClinic(ActionEvent event) {
        mysqlconnect sqlcon=new mysqlconnect();
        
        String pid = PID.getText().toString();
        String clinic = Clinic.getValue().toString();
        String date = Date.getValue().toString();
        
        String time = TimeS.getValue().toString();
        
        
        SaveDB(pid,clinic,date,time);
        
         populateTableView();
    }

    @FXML
    private void EditClinic(ActionEvent event) {
        
        String pid = PID.getText().toString();
        String clinic = Clinic.getValue().toString();
        //String date = Date.getValue().toString();
        
        String time = TimeS.getValue().toString();
        
        EditDB(pid,clinic,time);
        populateTableView();
        
    }

    @FXML
    private void RemoveClinic(ActionEvent event) {
        
        String pid = PID.getText().toString();
        DeleteDB(pid);
        
        populateTableView();
        
    }
    
    private void SaveDB(String pid,String clinic,String date,String time){
        mysqlconnect myconnection =new mysqlconnect();
        
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `clinic`(`pid`, `clinic`, `date`, `time`) VALUES (?,?,?,?)";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, pid);
            st.setString(2, clinic);
            st.setString(3, date);   
            st.setString(4, time);
            
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
            alert.setContentText(pid+"\n"+clinic+"\n"+"\n"+"Fail to ADD Employee!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }
    }
    
    private void EditDB(String pid, String clinic, String time){
      
      
      mysqlconnect myconnection =new mysqlconnect();
      PreparedStatement st;
        ResultSet rs;
        String addQuery="UPDATE `clinic` SET `clinic`=?,`time`=? WHERE `pid`=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, clinic);
            //st.setString(2, date);
            st.setString(2, time);
            st.setString(3, pid);
               
            
            
            if(st.executeUpdate()>0)
            {
              
            }else{
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            
        }

  
  }
    
    private void DeleteDB(String pid){
  
      mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="DELETE FROM `clinic` WHERE `pid`=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, pid);
            
            
            if(st.executeUpdate()>0)
            {
             System.out.print("DeleteDB success!");  
            }else{
                System.out.print("DeleteDB ....unsuccess!"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
            //return false;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(pid+"\n"+"Fail to Delete Employee!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }  
}
    
    
    private void populateTableView(){
        
        try {
            Connection conn=dc.createConnection();
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `clinic`");
            
            while(rs.next()){
                //get String from db,
                data.add(new clinic(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        //set cell values
        columnPid.setCellValueFactory(new PropertyValueFactory<>("PId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("clinic"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        ClinicTable.setItems(null);
        ClinicTable.setItems(data);
    
    }
    
    
//    

    @FXML
    private void TableClickData(MouseEvent event) {
        
        index = ClinicTable.getSelectionModel().getSelectedIndex();
        if(index <=-1){
            return;
        }
   
        PID.setText(columnPid.getCellData(index).toString());
        Clinic.setValue(columnName.getCellData(index).toString());
        //Date.setValue(columnDate.getCellData(index).toString());
        TimeS.setValue(columnTime.getCellData(index).toString());
        
        
    }
    
}
