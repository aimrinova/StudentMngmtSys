// --- src/main/java/org/anubis/lectures/studentmngmtsys/StudentController.java ---
package org.anubis.lectures.studentmngmtsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.anubis.lectures.studentmngmtsys.MainApp;
import org.anubis.lectures.studentmngmtsys.model.ViewDataBar;
import org.anubis.lectures.studentmngmtsys.Student;
import org.anubis.lectures.studentmngmtsys.model.StudentStorage;

public class StudentController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label studentIdLabel;

    // Reference to the main application.
    private MainApp mainApp;
    private ViewDataBar viewDataBar;
    private StudentStorage storage;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StudentController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the student table with the two columns.
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        // Clear student details.
        showStudentDetails(null);

        // Listen for selection changes and show the student details when changed.
        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStudentDetails(newValue));
    }

    /**
     * Fills all text fields to show details about the student.
     * If the specified student is null, all text fields are cleared.
     *
     * @param student the student or null
     */
    private void showStudentDetails(Student student) {
        if (student != null) {
            // Fill the labels with info from the student object.
            firstNameLabel.setText(student.getFirstName());
            lastNameLabel.setText(student.getLastName());
            streetLabel.setText(student.getStreet());
            postalCodeLabel.setText(Integer.toString(student.getPostalCode()));
            cityLabel.setText(student.getCity());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Student is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteStudent() {
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new student.
     */
    @FXML
    private void handleNewStudent() {
        Student tempStudent = new Student();
        boolean okClicked = viewDataBar.showPersonEditDialog(tempStudent);
        if (okClicked) {
            storage.getStudentData().add(tempStudent);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected student.
     */
    @FXML
    private void handleEditStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            boolean okClicked = viewDataBar.showPersonEditDialog(selectedStudent);
            if (okClicked) {
                showStudentDetails(selectedStudent);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(storage.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param storage
     */
    public void setStorage(StudentStorage storage) {
        this.storage = storage;
        // Add observable list data to the table
        studentTable.setItems(storage.getStudentData());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
    */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}