/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jayakuru
 */
public class Status {

    private final StringProperty Id;
    private final StringProperty Name;
    private final StringProperty Position;
    private final StringProperty Date;
    private final StringProperty Active;

    //default constructer
    public Status(String Id, String Name, String Position, String Date, String Active) {

        this.Id = new SimpleStringProperty(Id);
        this.Name = new SimpleStringProperty(Name);
        this.Position = new SimpleStringProperty(Position);
        this.Date = new SimpleStringProperty(Date);
        this.Active = new SimpleStringProperty(Active);
    }
    
    
    
    //Getters
    public String getId(){
        return Id.get();
    }
    public String getName(){
        return Name.get();
    }
    public String getPosition(){
        return Position.get();
    }
    public String getDate(){
        return Date.get();
    }
    public String getActive(){
        return Active.get();
    }

    
    
    //Setters
    public void setId(String value) {
        Id.set(value);
    }

    public void setName(String value) {
        Name.set(value);
    }

    public void setPosition(String value) {
        Position.set(value);
    }

    public void setDate(String value) {
        Date.set(value);
    }
    public void setActive(String value) {
        Active.set(value);
    }

    
    
    //Property values
    public StringProperty IdProperty() {
        return Id;
    }

    public StringProperty NameProperty() {
        return Name;
    }

    public StringProperty PositionProperty() {
        return Position;
    }

    public StringProperty DateProperty() {
        return Date;
    }
    public StringProperty ActiveProperty() {
        return Active;
    }

}
