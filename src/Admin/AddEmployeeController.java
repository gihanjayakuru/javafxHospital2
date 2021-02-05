/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import hospitalaa.mysqlconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jayakuru
 */
public class AddEmployeeController implements Initializable {
    @FXML
    private JFXTextField Empid;
    @FXML
    private JFXTextField EmpName;
    @FXML
    private ComboBox EmpPosition;
    @FXML
    private JFXRadioButton Male;
    @FXML
    private JFXRadioButton Female;
    
    private ToggleGroup ToggleGroup1;
    
    
   //Initialize observable list to hold out database 
    private ObservableList<Employee>data;
    
    private mysqlconnect dc;
    
    static String gender;
    int index= -1;
    @FXML
    private TableView<Employee> EmployeeTable;
    @FXML
    private TableColumn<Employee, String> columnId;
    @FXML
    private TableColumn<Employee, String> columnName;
    @FXML
    private TableColumn<Employee, String> columnPosition;
    @FXML
    private TableColumn<Employee, String> columnGender;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
        dc= new mysqlconnect();
        
        //
        ToggleGroup1 = new ToggleGroup();
       this.Female.setToggleGroup(ToggleGroup1);
       this.Male.setToggleGroup(ToggleGroup1);
       
       EmpPosition.getItems().add("Doctor");
       EmpPosition.getItems().addAll("Nurse","Pharmecist","Manager");
       
       Male.setToggleGroup(ToggleGroup1);

       Female.setToggleGroup(ToggleGroup1);
       populateTableView();
       
    }    

    @FXML
    private void AddEmployee(ActionEvent event) {
        
        
        
        mysqlconnect sqlcon=new mysqlconnect();
        
        if(this.ToggleGroup1.getSelectedToggle().equals(this.Male)){
            gender ="Male";
        }
        if(this.ToggleGroup1.getSelectedToggle().equals(this.Female)){
            gender = "Femal";
        }
     

        
        String position = EmpPosition.getValue().toString();
        String id = Empid.getText().toString();
        String name = EmpName.getText().toString();
        SaveDB(id,name, position,gender);
        populateTableView();
  
        
    }
    
    public void EditDB(String id, String name, String position,String gender){
      
      
      mysqlconnect myconnection =new mysqlconnect();
      PreparedStatement st;
        ResultSet rs;
        String addQuery="UPDATE `employees` SET `name`=?,`position`=?,`gender`=? WHERE `id`=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, name);
            st.setString(2, position);
            st.setString(3, gender);
            st.setString(4, id);    
            
            
            if(st.executeUpdate()>0)
            {
              
            }else{
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            
        }

  
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
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        
        EmployeeTable.setItems(null);
        EmployeeTable.setItems(data);
        
        System.out.print("populate table");
   }
    
    
    public void SaveDB(String id, String name, String position,String gender){
  
      mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `employees`(`id`, `name`, `position`,`gender`) VALUES (?,?,?,?)";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, position);   
            st.setString(4, gender);
            
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
            alert.setContentText(id+"\n"+name+"\n"+"\n"+"Fail to ADD Employee!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }
        
  
  }

   

    @FXML
    private void loadTable(ActionEvent event) {
        
        
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
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        
        EmployeeTable.setItems(null);
        EmployeeTable.setItems(data);
        
         
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        index = EmployeeTable.getSelectionModel().getSelectedIndex();
        if(index <=-1){
            return;
        }
        Empid.setText(columnId.getCellData(index).toString());
        EmpName.setText(columnName.getCellData(index).toString());
        EmpPosition.setValue(columnPosition.getCellData(index).toString());
        
        if("Male".equals(columnGender.getCellData(index).toString())){
            Male.setSelected(true);
            Female.setSelected(false);
        }
        if("Femal".equals(columnGender.getCellData(index).toString())){
            Female.setSelected(true);
            Male.setSelected(false);
        }
        
    }

    @FXML
    private void EditEmployee(ActionEvent event) {
        
         mysqlconnect sqlcon=new mysqlconnect();
        
        if(this.ToggleGroup1.getSelectedToggle().equals(this.Male)){
            gender ="Male";
        }
        if(this.ToggleGroup1.getSelectedToggle().equals(this.Female)){
            gender = "Femal";
        }
     

        
        String position = EmpPosition.getValue().toString();
        String id = Empid.getText().toString();
        String name = EmpName.getText().toString();
        EditDB(id,name, position,gender);
        populateTableView();
        
        
    }

    @FXML
    private void DeleteEmployee(ActionEvent event) {
        mysqlconnect sqlcon=new mysqlconnect();
        

       
        String id = Empid.getText().toString();
        
        DeleteDB(id);
        populateTableView();
        
        
    }
        
    
    
    
    public void DeleteDB(String id){
  
      mysqlconnect myconnection =new mysqlconnect();
 
        PreparedStatement st;
        ResultSet rs;
        String addQuery="DELETE FROM `employees` WHERE `id`=?";
        
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
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
            
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(id+"\n"+"Fail to Delete Employee!"+"\n"+"Check Valid ID");
            alert.show();
            
            
        }
    
    
}
}
