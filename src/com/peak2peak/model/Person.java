package com.peak2peak.model;

import com.peak2peak.util.LocalDateAdapter;
import javafx.beans.property.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Created by colinhill on 2/8/16.
 */
public class Person {
    private final StringProperty fName;
    private final StringProperty lName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;

    public Person(){
        this (null, null);
    }

    public Person (String fName, String lName){
        this.fName = new SimpleStringProperty(fName);
        this.lName = new SimpleStringProperty(lName);

        // Dummy Data
        this.street = new SimpleStringProperty("Edvards Thomsens Vej");
        this.postalCode = new SimpleIntegerProperty(2300);
        this.city = new SimpleStringProperty("København");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1990, 7, 28));
    }

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getlName() {
        return lName.get();
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class) // Tell Birthday to use adapter
    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }
}
