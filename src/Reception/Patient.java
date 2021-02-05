/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reception;

import hospitalaa.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jayakuru
 */
public class Patient {

    private final StringProperty PId;
    private final StringProperty PName;
    private final StringProperty GName;
    private final StringProperty GId;
    private final StringProperty Contact;
    private final StringProperty Address;
    

    //default constructer
    public Patient(String PId, String PName, String GName, String GId, String Contact, String Address) {

        this.PId = new SimpleStringProperty(PId);
        this.PName = new SimpleStringProperty(PName);
        this.GName = new SimpleStringProperty(GName);
        this.GId = new SimpleStringProperty(GId);
        this.Contact = new SimpleStringProperty(Contact);
        this.Address = new SimpleStringProperty(Address);
    }
    
    
    
    //Getters
    public String getPId(){
        return PId.get();
    }
    public String getPName(){
        return PName.get();
    }
    public String getGName(){
        return GName.get();
    }
    public String getGId(){
        return GId.get();
    }
    public String getContact(){
        return Contact.get();
    }
    public String getAddress(){
        return Address.get();
    }

    
    
    //Setters
    public void setPId(String value) {
        PId.set(value);
    }

    public void setPName(String value) {
        PName.set(value);
    }

    public void setGName(String value) {
        GName.set(value);
    }
    public void setGId(String value) {
        GId.set(value);
    }
    public void setContact(String value) {
        Contact.set(value);
    }
    public void setAddress(String value) {
        Address.set(value);
    }

    
    
    //Property values
    public StringProperty PIdProperty() {
        return PId;
    }

    public StringProperty PNameProperty() {
        return PName;
    }

    public StringProperty GNameProperty() {
        return GName;
    }
    public StringProperty GIdProperty() {
        return GId;
    }
    public StringProperty ContactProperty() {
        return Contact;
    }

    public StringProperty AddressProperty() {
        return Address;
    }

}
