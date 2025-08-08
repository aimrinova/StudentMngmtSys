package org.anubis.lectures.studentmngmtsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.anubis.lectures.studentmngmtsys.model.*;

public class EnrollByCourseController {
    @FXML private ChoiceBox<Course> courseChoice;
    @FXML private TableView<Enrollment> table;

    private StudentStorage studentStorage;
    private CourseStorage  courseStorage;
    @FXML private TableColumn<Enrollment, Student> studentColumn;
    @FXML private TableColumn<Enrollment, Double>  gradeColumn;

    @FXML
    public void initialize() {
        studentColumn.setCellValueFactory(
                cellData -> cellData.getValue().studentProperty()
        );
        gradeColumn.setCellValueFactory(
                cellData -> cellData.getValue().gradeProperty().asObject()
        );
    }

    /** Inject both storages */
    public void setStorage(StudentStorage ss, CourseStorage cs) {
        this.studentStorage = ss;
        this.courseStorage  = cs;

        // Populate the choice‐box with all courses:
        courseChoice.setItems(cs.getCourseData());

        // When the user picks a course, show only its enrollments:
        courseChoice.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldC, newC) -> {
                    if (newC != null) {
                        table.setItems(
                                ss.getEnrollments().filtered(e -> e.getCourse() == newC)
                        );
                    }
                }
        );
    }

    @FXML
    private void handleEnrollStudent() {
        Course course = courseChoice.getValue();
        if (course == null) {
            // show warning if you like
            return;
        }

        ChoiceDialog<Student> dialog = new ChoiceDialog<>(
                null,
                studentStorage.getStudentData()
        );
        dialog.setTitle("Enroll Student");
        dialog.setHeaderText("Enroll in “" + course.getCourseTitle() + "”");
        dialog.setContentText("Select student:");

        dialog.showAndWait()
                .ifPresent(s -> studentStorage.enroll(s, course));
    }
}
