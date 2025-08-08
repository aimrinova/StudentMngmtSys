package org.anubis.lectures.studentmngmtsys.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.anubis.lectures.studentmngmtsys.MainApp;
import org.anubis.lectures.studentmngmtsys.model.CourseStorage;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;
import org.anubis.lectures.studentmngmtsys.model.ViewDataBar;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */
public class RootLayoutController {

    // the main BorderPane, so we can swap the center content
    @FXML
    private BorderPane rootLayout;
    // the shared storage instance
    private StudentStorage storage;
    private MainApp mainApp;
    // inject your bottom-bar controls if needed:
    @FXML
    private Label statusLabel;
    @FXML
    private ProgressBar progressBar;

    // compose your helper:
    private final ViewDataBar viewDataBar = new ViewDataBar();


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Is called by the main application to give a reference back to itself
     * and the root layout pane.
     *
     * @param storage the StudentStorage
     * @param rootLayout the BorderPane root
     */
    public void setStorage(StudentStorage storage, CourseStorage courseStorage, BorderPane rootLayout) {
        this.storage = storage;
        // wire the root pane and storage into the helper
        viewDataBar.setRootLayout(rootLayout);
        viewDataBar.setStorage(storage);
        viewDataBar.setCourseStorage(courseStorage);
        viewDataBar.setMainApp(mainApp);

    }

    /** Called when user selects “View Home” */
    @FXML
    private void handleShowHome(){
        viewDataBar.showHomeScreen();
        statusLabel.setText("Home");
    }

    /** Called when user selects “Display Students” */
    @FXML
    private void handleShowStudents() throws IOException {
        viewDataBar.handleShowStudents();
        statusLabel.setText("Showing students");
    }

    /** Called when user selects “Display Courses” */
    @FXML
    private void handleShowCourses() {
        viewDataBar.handleShowCourses();
        statusLabel.setText("Showing courses");
    }

    /** Called when user selects “Display Enrollments” */
    @FXML
    private void handleShowEnrollments() {
        viewDataBar.handleShowEnrollments();
        statusLabel.setText("Showing enrollments");
    }

    // --- my existing Edit Data menu handlers ---

    /** Called when user selects “Display Enrollments” */
    @FXML
    private void handleEditStudents() {
        viewDataBar.showEditStudent();
        statusLabel.setText("Showing Students Entries");
    }

    /** Called when user selects “Display Enrollments” */
    @FXML
    private void handleEditCourse() {
        viewDataBar.showEditCourse();
        statusLabel.setText("Showing Course Entries");
    }



    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        storage.getStudentData().clear();
        storage.setStudentFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(storage.getPrimaryStage());

        if (file != null) {
            storage.loadStudentDataFromFile(file);
        }
    }

    /**
     * Saves the file to the student file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File studentFile = storage.getStudentFilePath();
        if (studentFile != null) {
            storage.saveStudentDataToFile(studentFile);
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
        File file = fileChooser.showSaveDialog(storage.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            storage.saveStudentDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Daniel Test\nWebsite: uopeople.task");

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