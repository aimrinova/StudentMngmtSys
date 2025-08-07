package org.anubis.lectures.studentmngmtsys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.anubis.lectures.studentmngmtsys.model.Course;

public class CourseEditModal {
    @FXML
    private TextField courseIdField;
    @FXML
    private TextField courseTitleField;
    @FXML
    private TextField courseDescriptionField;
    @FXML
    private TextField courseValueField;
    @FXML
    private TextField courseVolumeField;

    private Stage dialogStage;
    private Course course;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the course to be edited in the dialog.
     *
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;

        courseIdField.setText(course.getCourseId());
        courseTitleField.setText(course.getCourseTitle());
        courseDescriptionField.setText(course.getCourseDescription());
        courseValueField.setText(String.valueOf(course.getCourseValue()));
        courseVolumeField.setText(Integer.toString(course.getCourseVolume()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            course.setCourseID(courseIdField.getText());
            course.setCourseTitle(courseTitleField.getText());
            course.setCourseDescription(courseDescriptionField.getText());
            course.setCourseValue(Integer.parseInt(courseValueField.getText()));
            course.setCourseVolume(Integer.parseInt(courseVolumeField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    public void handleCancel(ActionEvent actionEvent) { dialogStage.close(); }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (courseIdField.getText() == null || courseIdField.getText().length() == 0) {
            errorMessage += "No valid course ID!\n";
        }
        if (courseTitleField.getText() == null || courseTitleField.getText().length() == 0) {
            errorMessage += "No valid course title!\n";
        }
        if (courseDescriptionField.getText() == null || courseDescriptionField.getText().length() == 0) {
            errorMessage += "No valid course description!\n";
        }

        if (courseValueField.getText() == null || courseValueField.getText().length() == 0) {
            errorMessage += "No valid course value in credits!\n";
        } else {
            // try to parse the course value into an int.
            try {
                Integer.parseInt(courseValueField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid course credits (must be an integer)!\n";
            }
        }

        if (courseVolumeField.getText() == null || courseVolumeField.getText().length() == 0) {
            errorMessage += "No valid volume in hours!\n";
        } else {
            // try to parse the course volume into an int.
            try {
                Integer.parseInt(courseVolumeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid course hours (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
