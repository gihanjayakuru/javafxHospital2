/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import hospitalaa.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jayakuru
 */
public class MngDoctors {

    private final StringProperty Id;
    private final StringProperty Name;
    private final StringProperty oi;
    private final StringProperty word;
    private final StringProperty Position;

    //default constructer
    public MngDoctors(String Id, String Name,String oi, String word, String Position) {

        this.Id = new SimpleStringProperty(Id);
        this.Name = new SimpleStringProperty(Name);
        this.oi = new SimpleStringProperty(oi);       
        this.word = new SimpleStringProperty(word);
        this.Position = new SimpleStringProperty(Position);
        
    }
    
    
    
    //Getters
    public String getId(){
        return Id.get();
    }
    public String getName(){
        return Name.get();
    }
    public String getoi(){
        return oi.get();
    }
    public String getword(){
        return word.get();
    }
    public String getPosition(){
        return Position.get();
    }

    
    
    //Setters
    public void setId(String value) {
        Id.set(value);
    }

    public void setName(String value) {
        Name.set(value);
    }
    public void setoi(String value) {
        oi.set(value);
    }
    public void setword(String value) {
        word.set(value);
    }
    public void setPosition(String value) {
        Position.set(value);
    }

    
    
    //Property values
    public StringProperty IdProperty() {
        return Id;
    }

    public StringProperty NameProperty() {
        return Name;
    }
    public StringProperty oiProperty() {
        return oi;
    }
    public StringProperty wordProperty() {
        return word;
    }
    public StringProperty PositionProperty() {
        return Position;
    }
    

}
