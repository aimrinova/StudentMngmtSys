package org.anubis.lectures.studentmngmtsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.anubis.lectures.studentmngmtsys.Student;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;

public class AllStudentsController {

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, String> studentIdColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> studyFieldColumn;
    @FXML
    private TableColumn<Student, Number> creditsColumn;
    @FXML
    private TableColumn<Student, Number> avgGradeColumn;

    private StudentStorage storage;

    /**
     * Called by FXMLLoader to initialize the controller.
     * Sets up the column cell-value factories.
     */
    @FXML
    private void initialize() {
        studentIdColumn.setCellValueFactory(cell -> cell.getValue().studentIdProperty());
        firstNameColumn.setCellValueFactory(cell -> cell.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cell -> cell.getValue().lastNameProperty());

        // [Inference] Assuming Student has these properties:
        studyFieldColumn.setCellValueFactory(cell -> cell.getValue().studyFieldProperty());
        creditsColumn.setCellValueFactory(cell -> cell.getValue().creditsProperty());
        avgGradeColumn.setCellValueFactory(cell -> cell.getValue().avgGradeProperty());
    }

    /**
     * Called by whoever loads this view to give access to the shared data.
     */
    public void setStorage(StudentStorage storage) {
        this.storage = storage;
        studentsTable.setItems(storage.getStudentData());
    }
}
