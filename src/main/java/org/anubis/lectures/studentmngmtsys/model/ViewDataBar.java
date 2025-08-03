package org.anubis.lectures.studentmngmtsys.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ViewDataBar {
    private BorderPane rootLayout;   // you’ll need to pass this in via a setter…

    public void handleShowStudents() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/anubis/.../students-overview.fxml")
        );
        AnchorPane pane = loader.load();
        rootLayout.setCenter(pane);
    }

    public void handleShowCourses() {
        // your courses-view logic here…
    }

    public void handleShowEnrollmetns() {
        // your enrollments-view logic here…
    }

    public void setRootLayout(BorderPane rootLayout) {
    }
}
