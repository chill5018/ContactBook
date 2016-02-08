package com.peak2peak;

import com.peak2peak.view.PersonEditDialogController;
import com.peak2peak.view.PersonOverviewController;
import com.peak2peak.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private BorderPane root;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public Main(){
        personData.add(new Person("Thea", "Libaek"));
        personData.add(new Person("Ario", "Iranpour"));
        personData.add(new Person("Hans", "Ekerold"));
        personData.add(new Person("Billie", "Libaek"));
        personData.add(new Person("Fabrice", "R"));
        personData.add(new Person("Anette", "Bøe"));
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
        primaryStage.setTitle("My Contacts");

        showPersonOverview();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();



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

    public static void main(String[] args) {
        launch(args);
    }
}
