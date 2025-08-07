package org.anubis.lectures.studentmngmtsys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.anubis.lectures.studentmngmtsys.MainApp;
import org.anubis.lectures.studentmngmtsys.model.Course;
import org.anubis.lectures.studentmngmtsys.model.CourseStorage;
import org.anubis.lectures.studentmngmtsys.model.ViewDataBar;

public class CourseController {
    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, String> courseIdColumn;
    @FXML
    private TableColumn<Course, String> courseTitleColumn;
    @FXML
    private GridPane courseInfo;
    @FXML
    private Label courseIdLabel;
    @FXML
    private Label courseTitleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label courseCreditsLabel;
    @FXML
    private Label courseHoursLabel;

    // Reference to the main application.
    private MainApp mainApp;
    private ViewDataBar viewDataBar;
    private CourseStorage storage;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public CourseController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the student table with the two columns.
        courseIdColumn.setCellValueFactory(
                cellData -> cellData.getValue().courseIdProperty());
        courseTitleColumn.setCellValueFactory(
                cellData -> cellData.getValue().courseTitleProperty());
        // Clear student details.
        showCourseDetails(null);

        // Listen for selection changes and show the student details when changed.
        courseTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCourseDetails(newValue));
    }

    /**
     * Fills all text fields to show details about the course.
     * If the specified course is null, all text fields are cleared.
     *
     * @param course the course or null
     */
    private void showCourseDetails(Course course) {
        if (course != null) {
            // Fill the labels with info from the course object.
            courseIdLabel.setText(course.getCourseId());
            courseTitleLabel.setText(course.getCourseTitle());
            descriptionLabel.setText(course.getCourseDescription());
            courseCreditsLabel.setText(Integer.toString(course.getCourseValue()));
            courseHoursLabel.setText(Integer.toString(course.getCourseVolume()));

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Student is null, remove all the text.
            courseIdLabel.setText("");
            courseTitleLabel.setText("");
            descriptionLabel.setText("");
            courseCreditsLabel.setText("");
            courseHoursLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new course.
     */
    @FXML
    public void handleNewCourse(ActionEvent actionEvent) {
        Course tempCourse = new Course();
        boolean okClicked = viewDataBar.showCourseEditDialog(tempCourse);
        if (okClicked) {
            storage.getCourseData().add(tempCourse);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected course.
     */
    @FXML
    public void handleEditCourse(ActionEvent actionEvent) {
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            boolean okClicked = viewDataBar.showCourseEditDialog(selectedCourse);
            if (okClicked) {
                showCourseDetails(selectedCourse);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(storage.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Course Selected");
            alert.setContentText("Please select a Course in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    public void handleDeleteCourse(ActionEvent actionEvent) {
        int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            courseTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Course Selected");
            alert.setContentText("Please select a Course in the table.");
            alert.showAndWait();
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param storage
     */
    public void setStorage(CourseStorage storage) {
        this.storage = storage;
        // Add observable list data to the table
        courseTable.setItems(storage.getCourseData());
    }

    public void setViewDataBar(ViewDataBar viewDataBar) {
        this.viewDataBar = viewDataBar;
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

