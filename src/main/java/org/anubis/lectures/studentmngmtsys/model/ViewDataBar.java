package org.anubis.lectures.studentmngmtsys.model;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.anubis.lectures.studentmngmtsys.MainApp;
import org.anubis.lectures.studentmngmtsys.controller.*;

import java.io.IOException;

public class ViewDataBar {

    // a reference to the main BorderPane in your RootLayout
    private BorderPane rootLayout;
    private MainApp mainApp;
    // a reference to your StudentStorage (so you can pass it on)
    private StudentStorage storage;
    private CourseStorage course;

    public ViewDataBar() {
    }

    /** called by RootLayoutController to inject the pane */
    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    /** called by RootLayoutController to inject the storage */
    public void setStorage(StudentStorage storage) {
        this.storage = storage;
    }

    public void handleShowStudents() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/all-students-table.fxml")
            );
            AnchorPane pane = loader.load();

            // grab the students controller and give it the shared storage
            AllStudentsController ctrl = loader.getController();
            ctrl.setStorage(this.storage);

            rootLayout.setCenter(pane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleShowCourses() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/all-courses-table.fxml")
            );
            AnchorPane pane = loader.load();

            // grab the courses controller and give it the shared storage
            AllCoursesController ctrl = loader.getController();
            ctrl.setStorage(this.course);
            rootLayout.setCenter(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleShowEnrollments() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/enrollment-overview.fxml")
            );
            AnchorPane pane = loader.load();
            rootLayout.setCenter(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the apps main screen which displays inforamtion about the menubar and functionalities
     */
    public void showHomeScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/homescreen-info.fxml")
            );
            AnchorPane pane = loader.load();
            rootLayout.setCenter(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showEditStudent() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/student-overview.fxml")
            );
            if (loader.getLocation() == null) {
                throw new IllegalStateException("Cannot find FXML 'student-overview.fxml'");
            }
            AnchorPane studentOverview = (AnchorPane) loader.load();
            // Give the controller access to the storage.
            StudentController studentCtrl = loader.getController();
            studentCtrl.setStorage(storage);
            // ← NEW: also give it a reference to this MainApp so showPersonEditDialog(...) works
            studentCtrl.setMainApp(mainApp);
            // ← inject the helper instance so viewDataBar is never null
            studentCtrl.setViewDataBar(this);
            rootLayout.setCenter(studentOverview);

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
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/student-edit-modal.fxml")
            );
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
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
     * Opens a dialog to edit details for the specified course. If the user
     * clicks OK, the changes are saved into the provided course object and true
     * is returned.
     *
     * @param course the course object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showCourseEditDialog(Course course) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/anubis/lectures/studentmngmtsys/course-edit-modal.fxml")
            );
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Course into the controller.
            CourseEditModal controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCourse(course);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


}
