package com.peak2peak.view;


import com.peak2peak.Main;
import com.peak2peak.model.Person;
import com.peak2peak.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

    private Main main;

    // Left Table View with First and Last Names
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;


    // Labels are linked to the data in the Name Table View to provide more info
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Empty Constructor
    public PersonOverviewController(){}


    // Buttons (Add, Edit, Delete)

    @FXML
    private void handleDeletePerson(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >=0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            //Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person before trying to delete");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = main.showPersonEditDialog(tempPerson);
        if (okClicked)
            main.getPersonData().add(tempPerson);
    }

    @FXML
    private void handelEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null){
            boolean okClicked = main.showPersonEditDialog(selectedPerson);
            if (okClicked)
                showPersonDetails(selectedPerson);
        } else {
            //Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person before trying to delete");
            alert.showAndWait();

        }
    }

    /*
    Initialize the view class
    Is Automatically called
    */
    @FXML
    private void initialize(){
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().fNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lNameProperty());

        //Clear person details
        showPersonDetails(null);

        //Listen for selection changes and show the person details when changed
        personTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showPersonDetails(newValue)));
    }

    /*
    Is called by the main application to give a reference back to itself
     */
    public void setMain(Main main){
        this.main = main;

        personTable.setItems(main.getPersonData());
    }

    /*
    Fill all text fields to show details about the person
     */
    private void showPersonDetails(Person p){
        if (p != null){
            //Fill labels with info from the person object
            fNameLabel.setText(p.getfName());
            lNameLabel.setText(p.getlName());
            streetLabel.setText(p.getStreet());
            postalCodeLabel.setText(Integer.toString(p.getPostalCode()));
            cityLabel.setText(p.getCity());
            birthdayLabel.setText(DateUtil.format(p.getBirthday()));

        } else {
            fNameLabel.setText("");
            lNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            birthdayLabel.setText("");

        }
    }
}
