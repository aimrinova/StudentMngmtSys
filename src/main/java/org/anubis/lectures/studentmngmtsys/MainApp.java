package org.anubis.lectures.studentmngmtsys;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.anubis.lectures.studentmngmtsys.model.RootLayoutController;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;

import java.io.IOException;

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
        showStudent(storage);
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
            rootCtrl.setStorage(storage);

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
     * Shows the person overview inside the root layout.
     */
    public void showStudent(StudentStorage storage) {
        try {
            // Load person overview.
            FXMLLoader studentLoader = new FXMLLoader();
            studentLoader.setLocation(
                    MainApp.class.getResource("/org/anubis/lectures/studentmngmtsys/student-overview.fxml")
            );
            if (studentLoader.getLocation() == null) {
                throw new IllegalStateException("Cannot find FXML 'student-overview.fxml'");
            }
            AnchorPane studentOverview = (AnchorPane) studentLoader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(studentOverview);

            // Give the controller access to the storage.
            StudentController studentCtrl = studentLoader.getController();
            studentCtrl.setStorage(storage);
            // ← NEW: also give it a reference to this MainApp so showPersonEditDialog(...) works
            studentCtrl.setMainApp(this);

        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Startup Error");
            alert.setHeaderText("Unable to load UI");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            Platform.exit();
        }
    }

    /**
     * Opens a dialog to edit details for the specified student. If the user
     * clicks OK, the changes are saved into the provided student object and true
     * is returned.
     *
     * @param student the student object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    MainApp.class.getResource("/org/anubis/lectures/studentmngmtsys/student-edit-modal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the student into the controller.
            StudentEditModal controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
