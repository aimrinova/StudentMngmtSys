package org.anubis.lectures.studentmngmtsys.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.anubis.lectures.studentmngmtsys.util.StudentListWrapper;

import java.io.File;
import java.util.prefs.Preferences;

public class StudentStorage {

    private final ObservableList<Student> studentData = FXCollections.observableArrayList();
    private final Stage primaryStage;

    public StudentStorage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // on startup, try loading the last file
        File last = getStudentFilePath();
        if (last != null) {
            loadStudentDataFromFile(last);
        } else {
            studentData.add(new Student("John", "Doe"));
            studentData.add(new Student("Rita", "Lore"));
            studentData.add(new Student("Adam", "Short"));
            studentData.add(new Student("Eve", "Sour"));
            studentData.add(new Student("Richards", "Dane"));
            studentData.add(new Student("Lory", "Pain"));
            studentData.add(new Student("Anna", "Best"));
            studentData.add(new Student("Sally", "Pierce"));
            studentData.add(new Student("Becky", "Try"));
        }
    }

    public ObservableList<Student> getStudentData() {
        return studentData;
    }

    /**
     * Returns the Student file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getStudentFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(StudentStorage.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setStudentFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(StudentStorage.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("Course Data — " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("Course Data");
        }
    }

    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param file
     */
    public void loadStudentDataFromFile(File file) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(StudentListWrapper.class);
            Unmarshaller um = ctx.createUnmarshaller();
            StudentListWrapper wrapper = (StudentListWrapper) um.unmarshal(file);

            studentData.setAll(wrapper.getStudents());
            setStudentFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current student data to the specified file.
     *
     * @param file
     */
    public void saveStudentDataToFile(File file) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(StudentListWrapper.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StudentListWrapper wrapper = new StudentListWrapper();
            wrapper.setStudents(studentData);

            m.marshal(wrapper, file);
            setStudentFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    private void showError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Error");
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public ObservableList<Student> studentData() {
        return studentData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
