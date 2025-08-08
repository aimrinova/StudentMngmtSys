package org.anubis.lectures.studentmngmtsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import org.anubis.lectures.studentmngmtsys.model.Enrollment;
import org.anubis.lectures.studentmngmtsys.model.Student;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;

public class EnrollByStudentController {
    @FXML private ChoiceBox<Student> studentChoice;
    @FXML
    private TableView<Enrollment> table;
    private StudentStorage storage;

    public void setStorage(StudentStorage storage) {
        this.storage = storage;
        studentChoice.setItems(storage.getStudentData());
        studentChoice.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldS, newS) -> {
                    if (newS != null) {
                        table.setItems(
                                storage.getEnrollments().filtered(e -> e.getStudent() == newS)
                        );
                    }
                });
    }
}
