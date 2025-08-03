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
import javafx.stage.Stage;

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
        studentData.add(new Student("John", "Doe"));
        studentData.add(new Student("Rita", "Lore"));
        studentData.add(new Student("Adam", "Short"));
        studentData.add(new Student("Eve", "Sour"));
        studentData.add(new Student("Richards", "Dane"));
        studentData.add(new Student("Lory", "Pain"));
        studentData.add(new Student("Anna", "Best"));
        studentData.add(new Student("Sally", "Pierce"));
        studentData.add(new Student("Becky", "Try"));
    }

    /**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<Student> getStudentData() {
        return studentData;
    }



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Course Data");

        initRootLayout();
        showStudent();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/root-layout.fxml")
            );
            if (loader.getLocation() == null) {
                throw new IllegalStateException("Cannot find FXML 'root-layout.fxml'");
            }
            rootLayout = (BorderPane) loader.load();

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
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showStudent() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    MainApp.class.getResource("/org/anubis/lectures/studentmngmtsys/student-overview.fxml")
            );
            if (loader.getLocation() == null) {
                throw new IllegalStateException("Cannot find FXML 'student-overview.fxml'");
            }
            AnchorPane studentOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(studentOverview);


            // Give the controller access to the main app.
            StudentController controller = loader.getController();
            controller.setMainApp(this);
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
