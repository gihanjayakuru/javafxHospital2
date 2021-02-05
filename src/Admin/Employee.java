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
public class Employee {

    private final StringProperty Id;
    private final StringProperty Name;
    private final StringProperty Position;
    private final StringProperty Gender;

    //default constructer
    public Employee(String Id, String Name, String Position, String Gender) {

        this.Id = new SimpleStringProperty(Id);
        this.Name = new SimpleStringProperty(Name);
        this.Position = new SimpleStringProperty(Position);
        this.Gender = new SimpleStringProperty(Gender);
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
    public String getGender(){
        return Gender.get();
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

    public void setGender(String value) {
        Gender.set(value);
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

    public StringProperty GenderProperty() {
        return Gender;
    }

}
