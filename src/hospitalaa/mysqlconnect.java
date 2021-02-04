/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalaa;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Jayakuru
 */
public class mysqlconnect {
    
    public Connection createConnection(){
      
      Connection connection = null;
      
      MysqlDataSource mds = new MysqlDataSource();
      
      mds.setServerName("localhost");
      mds.setPortNumber(3306);
      mds.setUser("root");
      mds.setPassword("");
      mds.setDatabaseName("fx_hospital");
      
      try {
          connection = mds.getConnection();
      } catch (SQLException ex) {
          Logger.getLogger(mysqlconnect.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return connection;
  
  }  
    
  
   
}
