package org.anubis.lectures.studentmngmtsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import org.anubis.lectures.studentmngmtsys.model.Enrollment;
import org.anubis.lectures.studentmngmtsys.model.Student;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;

import javafx.util.StringConverter;

public class EnrollByStudentController {
    @FXML private ChoiceBox<Student> studentChoice;
    @FXML private TableView<Enrollment> table;
    private StudentStorage storage;

    public void setStorage(StudentStorage storage) {
        this.storage = storage;

        // 1) converter so Student → “First Last”
        studentChoice.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student s) {
                return s == null
                        ? ""
                        : s.getFirstName() + " " + s.getLastName();
            }
            @Override
            public Student fromString(String string) {
                // Not used by ChoiceBox
                return null;
            }
        });

        // 2) populate the choice box
        studentChoice.setItems(storage.getStudentData());

        // 3) when selection changes, update table
        studentChoice.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldS, newS) -> {
                    if (newS != null) {
                        table.setItems(
                                storage.getEnrollments()
                                        .filtered(e -> e.getStudent() == newS)
                        );
                    }
                }
        );
    }
}
