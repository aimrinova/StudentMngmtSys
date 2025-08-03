package org.anubis.lectures.studentmngmtsys.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.anubis.lectures.studentmngmtsys.controller.AllStudentsController;

public class ViewDataBar {

    // a reference to the main BorderPane in your RootLayout
    private BorderPane rootLayout;

    // a reference to your StudentStorage (so you can pass it on)
    private StudentStorage storage;

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
}
