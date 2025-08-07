package org.anubis.lectures.studentmngmtsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.anubis.lectures.studentmngmtsys.model.Course;
import org.anubis.lectures.studentmngmtsys.model.CourseStorage;

public class AllCoursesController {
    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseIdColumn;
    @FXML
    private TableColumn<Course, String> courseTitleColumn;
    @FXML
    private TableColumn<Course, String> courseDescriptionColumn;
    @FXML
    private TableColumn<Course, Number> courseValueColumn;
    @FXML
    private TableColumn<Course, Number> courseVolumeColumn;

    private CourseStorage storage;

    /**
     * Called by FXMLLoader to initialize the controller.
     * Sets up the column cell-value factories.
     */
    @FXML
    private void initialize() {
        courseIdColumn.setCellValueFactory(cell -> cell.getValue().courseIdProperty());
        courseTitleColumn.setCellValueFactory(cell -> cell.getValue().courseTitleProperty());
        courseDescriptionColumn.setCellValueFactory(cell -> cell.getValue().courseDescriptionProperty());

        // [Inference] Assuming Course has these properties:
        courseValueColumn.setCellValueFactory(cell -> cell.getValue().courseValueProperty());
        courseVolumeColumn.setCellValueFactory(cell -> cell.getValue().courseVolumeProperty());
    }

    /**
     * Called by whoever loads this view to give access to the shared data.
     */
    public void setStorage(CourseStorage storage) {
        this.storage = storage;
        courseTable.setItems(storage.getCourseData());
    }
}
