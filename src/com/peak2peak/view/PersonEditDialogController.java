package com.peak2peak.view;

import com.peak2peak.model.Person;
import com.peak2peak.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by colinhill on 2/8/16.
 */
public class PersonEditDialogController {

    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    @FXML
    private void initialize(){
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person p){
        this.person = p;

        fNameField.setText(p.getfName());
        lNameField.setText(p.getlName());
        streetField.setText(p.getStreet());
        postalCodeField.setText(Integer.toString(p.getPostalCode()));
        cityField.setText(p.getCity());
        birthdayField.setText(DateUtil.format(p.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");

    }

    public boolean isOKClicked(){
        return okClicked;
    }

    /*   Called when user clicks ok  */
    @FXML
    private void okButtonClicked() {
        if (isInputValid()) {
            person.setfName(fNameField.getText());
            person.setlName(lNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void cancelButtonClicked(){
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (fNameField.getText() == null || fNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lNameField.getText() == null || lNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
