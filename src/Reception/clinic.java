/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reception;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jayakuru
 */
public class clinic {
    
    private final StringProperty PId;
    private final StringProperty clinic;
    private final StringProperty date;
    private final StringProperty time;
    
    

    //default constructer
    public clinic(String PId, String clinic, String date, String time) {

        this.PId = new SimpleStringProperty(PId);
        this.clinic = new SimpleStringProperty(clinic);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        
    }
    
    
    
    //Getters
    public String getPId(){
        return PId.get();
    }
    public String getclinic(){
        return clinic.get();
    }
    public String getdate(){
        return date.get();
    }
    public String gettime(){
        return time.get();
    }
   

    
    
    //Setters
    public void setPId(String value) {
        PId.set(value);
    }

    public void setclinic(String value) {
        clinic.set(value);
    }

    public void setdate(String value) {
        date.set(value);
    }
    public void settime(String value) {
        time.set(value);
    }
    

    
    
    //Property values
    public StringProperty PIdProperty() {
        return PId;
    }

    public StringProperty clinicProperty() {
        return clinic;
    }

    public StringProperty dateProperty() {
        return date;
    }
    public StringProperty timeProperty() {
        return time;
    }
   
    
}
