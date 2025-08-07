package org.anubis.lectures.studentmngmtsys;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.anubis.lectures.studentmngmtsys.controller.RootLayoutController;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Students.
     */
    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Course Data");
        // 1) create storage (loads last file if any)
        StudentStorage storage = new StudentStorage(primaryStage);

        initRootLayout(storage);
        //showStudent(storage);
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout(StudentStorage storage) {
        try {
            FXMLLoader rootLoader = new FXMLLoader();
            rootLoader.setLocation(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/root-layout.fxml")
            );
            if (rootLoader.getLocation() == null) {
                throw new IllegalStateException("Cannot find FXML 'root-layout.fxml'");
            }
            rootLayout = (BorderPane) rootLoader.load();

            // Give the controller access to the storage.
            RootLayoutController rootCtrl = rootLoader.getController();
            rootCtrl.setStorage(storage, rootLayout);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Startup Error");
            alert.setHeaderText("Unable to load UI");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            Platform.exit();
        }

        /* Try to load last opened student file.
        File file = getStudentFilePath();
        if (file != null) {
            loadStudentDataFromFile(file);
        }
        */
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
