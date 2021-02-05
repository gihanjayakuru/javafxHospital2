/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reception;

import Admin.AddEmployeeController;
import Admin.Employee;
import Admin.Status;
import hospitalaa.mysqlconnect;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import hospitalaa.mysqlconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author Jayakuru
 */
public class AttendanceController implements Initializable {
    @FXML
    private TableView<Status> StatusTable;
    @FXML
    private JFXTextField Search;
    @FXML
    private JFXRadioButton Active;
    @FXML
    private JFXRadioButton NotActive;
    @FXML
    private JFXTextField date1;
    @FXML
    private JFXComboBox<?> Position;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField ID;
    @FXML
    private TableColumn<Status, String> STcolumnId;
    @FXML
    private TableColumn<Status, String> STcolumnName;
    @FXML
    private TableColumn<Status, String> STcolumnActive;
    @FXML
    private TableColumn<Status, String> STcolumnDate;
    @FXML
    private TableColumn<Status, String> STcolumnPosition;
    
    
    private ToggleGroup ToggleGroup1;
    
    
   //Initialize observable list to hold out database 
    private ObservableList<Status>dataa;
    
    private mysqlconnect dc;
    
    static String gender;
    int index= -1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mysqlconnect myconnection =new mysqlconnect();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    //get current date time with Date()
     Date date = new Date() {};
    System.out.println(dateFormat.format(date));
    String dates = dateFormat.format(date);
    date1.setText(dates);

    //get current date time with Calendar()
    Calendar cal = Calendar.getInstance();
    System.out.println(dateFormat.format(cal.getTime()));
    
    
     ToggleGroup1 = new ToggleGroup();
       this.Active.setToggleGroup(ToggleGroup1);
       this.NotActive.setToggleGroup(ToggleGroup1);
       
       //populateTableView3();
    
    
    }    

    @FXML
    private void UpdateStatus(ActionEvent event) {
        
        
    }
    
    public void EditDB(String id, String name, String position,String date,String active){
      
      
      mysqlconnect myconnection =new mysqlconnect();
      PreparedStatement st;
        ResultSet rs;
        String addQuery="UPDATE `activestatus` SET `name`=?,`position`=?,`date`=?,`ACstatus`=? WHERE `id`=?";
        
        try {
            st=myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, name);
            st.setString(2, position);
            st.setString(3, date);
            st.setString(4, active);
            st.setString(4, id);
            
            
            if(st.executeUpdate()>0)
            {
              
            }else{
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            
        }

  
  }
    public void populateTableView3() {
        
        mysqlconnect sqlcon=new mysqlconnect();
        
        try {
            Connection conn=dc.createConnection();
            dataa = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `activestatus`");
            
            while(rs.next()){
                //get String from db,
                dataa.add(new Status(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        //set cell values
        STcolumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        STcolumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        STcolumnActive.setCellValueFactory(new PropertyValueFactory<>("Position"));
        STcolumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        STcolumnPosition.setCellValueFactory(new PropertyValueFactory<>("Active"));
        
        StatusTable.setItems(null);
        StatusTable.setItems(dataa);
        
        System.out.print("populate table");
//       
//        try {
//            Connection conn=dc.createConnection();
//            data = FXCollections.observableArrayList();
//            //Execute query and store result in a resultset
//            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM `activestatus`");
//            
//            while(rs.next()){
//                //get String from db,
//               // data.add(new Status(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
//            }
//            
//            
//        } catch (SQLException ex) {
//            System.err.println("Error"+ex);
//        }
//        //set cell values
//        STcolumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
//        STcolumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        STcolumnActive.setCellValueFactory(new PropertyValueFactory<>("Position"));
//        STcolumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
//        STcolumnPosition.setCellValueFactory(new PropertyValueFactory<>("Active"));
//        
//        StatusTable.setItems(null);
//        StatusTable.setItems(data);
//        
//        System.out.print("populate table");
   }
    
}
