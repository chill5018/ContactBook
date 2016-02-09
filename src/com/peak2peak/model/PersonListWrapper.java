package com.peak2peak.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by colinhill on 2/9/16.
 *
 * This file will take the Person Data and wrap the information into an XML file
 */

@XmlRootElement(name = "persons") // Root XML Tag
public class PersonListWrapper {
    private List<Person> persons;

    @XmlElement(name = "person") // Element XML Tag
    public List<Person>getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
