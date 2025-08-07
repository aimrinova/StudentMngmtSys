package org.anubis.lectures.studentmngmtsys.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.anubis.lectures.studentmngmtsys.model.Student;
import org.anubis.lectures.studentmngmtsys.util.DateUtil;

public class StudentEditModal {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    public TextField studentIdField;
    public TextField studyFieldField;
    public TextField creditsField;
    public TextField avgGradeField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Student student;
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
     * Sets the student to be edited in the dialog.
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;

        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        studentIdField.setText(student.getStudentId());
        studyFieldField.setText(student.getStudyField());
        creditsField.setText(Integer.toString(student.getCredits()));
        avgGradeField.setText(Double.toString(student.getAvgGrade()));
        streetField.setText(student.getStreet());
        postalCodeField.setText(Integer.toString(student.getPostalCode()));
        cityField.setText(student.getCity());
        birthdayField.setText(DateUtil.format(student.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
        studentIdField.setText(student.getStudentId());
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
            student.setFirstName(firstNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setStudentId(studentIdField.getText());
            student.setStudyField(studyFieldField.getText());
            student.setCredits(Integer.parseInt(creditsField.getText()));
            student.setAvgGrade(Double.parseDouble(avgGradeField.getText()));
            student.setStreet(streetField.getText());
            student.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            student.setCity(cityField.getText());
            student.setBirthday(DateUtil.parse(birthdayField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}