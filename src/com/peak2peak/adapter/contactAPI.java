package com.peak2peak.adapter;

import com.peak2peak.model.Person;
import com.sun.deploy.perf.PerfHelper;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by colinhill on 2/26/16.
 */
public class contactAPI {
    static final String JDBC_URL = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sys";
    static final String USER = "root";
    static final String PASS = "teamtreehouse";

    static final ArrayList<Person> people = new ArrayList<>();

    public static void addPersonToDatabase(Person p){
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

            System.out.println("Inserting records into the table...");
            stmt = connection.createStatement();



            String sql = "INSERT INTO contacts " +
                    "VALUES (id, '" + p.getfName() + "'," + p.getlName()+ p.getStreet()+ p.getCity() + p.getPostalCode() + p.getBirthday()+")";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }


}
