package com.example.agendadi;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /*
    La lista la rellenamos con datos para utilizar la aplicacion
     */
    public MainApp() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    //Devolvemos la lista entera
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        //Cargamos el stage
        this.primaryStage = primaryStage;
        //Le ponemos titulo a la ventana
        this.primaryStage.setTitle("AddressApp");

        //Le asignamod la imagen seleccionada como icono de aplicaion
        this.primaryStage.getIcons().add(new Image("file:src/main/resources/com/example/agendadi/images/9036037_rocket_sharp_icon.png"));

        //LLamamos a los distintos metodos
        initRootLayout();

        showPersonOverview();
    }


    //Mostramos la ventana de vision de datos que es la inicial
    public void initRootLayout() {
        try {
            //Cargamos el rootLayout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Mostramos la scene
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);   
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Para mostrar el menu de edicion
    public boolean showPersonEditDialog(Person person) {
        try {
            //Cargamos el fxml del menu de edcion
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Le damos un titulo a la ventana que se crea y varias caracteristicas
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //Le asignamos el controlador
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            //Lo mostramos y esperamos hasta que uno de los batones se haya pulsado
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    //Muestra la clase PersonOverview
    public void showPersonOverview() {
        try {
            // Coargamos la clase
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            //Lo ponemos en el centro de la ventana
            rootLayout.setCenter(personOverview);

            //Le asiganamos su control
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Para recoger el stage indicado
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    //Para arrancar la aplicacion
    public static void main(String[] args) {
        launch(args);
    }
}