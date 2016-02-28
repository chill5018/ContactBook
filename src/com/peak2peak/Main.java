package com.peak2peak;

import com.peak2peak.model.PersonListWrapper;
import com.peak2peak.view.PersonEditDialogController;
import com.peak2peak.view.PersonOverviewController;
import com.peak2peak.model.Person;
import com.peak2peak.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane root;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public Main(){

    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("My Contacts");

        //Load root Layout
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));

        root = (BorderPane) loader.load();

        //Show Scene containing root layout
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Give the controller access to the main app
        RootLayoutController controller = loader.getController();
        controller.setMain(this);

        primaryStage.show();
        showPersonOverview();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }


    // Updates all of the Labels with the details of a person
    public void showPersonOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonOverview.fxml"));

            AnchorPane personOverview = (AnchorPane) loader.load();

            root.setCenter(personOverview);

            //Give the view access to the main app
            PersonOverviewController controller = loader.getController();
            controller.setMain(this);


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Person p){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonEditDialog.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            //Create Dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(root);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(p);

            dialogStage.showAndWait();

            return controller.isOKClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    // User Preferences for the Application
    public File getPersonFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);

        if (filePath != null)
            return new File(filePath);
        else
            return null;

    }

    // Set the file path of the currently loaded file

    public void setPersonFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            primaryStage.setTitle("My Contacts -  " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("My Contacts");
        }
    }

    // Load Person Data from XML File
    public void loadPersonDataFromFile(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);

            Unmarshaller um = context.createUnmarshaller();

            //Reading XML from file
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            setPersonFilePath(file);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not Load Data");
            alert.setContentText("Could not load data from file:\n"+file.getPath());

            alert.showAndWait();
        }
    }

    // Save the Current Person to the XML File
    public void savePersonDataToFile(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Wrapping the person Data
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            //Marshalling and saving XML to file
            m.marshal(wrapper, file);

            setPersonFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not Load Data");
            alert.setContentText("Could not load data from file:\n"+file.getPath());

            alert.showAndWait();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
