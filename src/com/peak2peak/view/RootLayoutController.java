package com.peak2peak.view;

import com.peak2peak.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by colinhill on 2/9/16.
 */

// Controls the Menu of the Root Layout Controller
public class RootLayoutController {

    //Ref to the main APP
    private Main main;

    // Is called by the Main to give ref to itself
    public void setMain(Main main){
        this.main = main;
    }

    // Create empty Address book
    @FXML
    private void handleNew(){
        main.getPersonData().clear();
        main.setPersonFilePath(null);
    }

    //Open a File Chooser to let the user select the addressbook to load
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        //Show Save Dialog

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null){
            main.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     */
    @FXML
    private void handleSave() {
        File personFile = main.getPersonFilePath();
        if (personFile != null) {
            main.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");

        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());


        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.savePersonDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Colin Hill\nWebsite: http://colinwhill.com");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
