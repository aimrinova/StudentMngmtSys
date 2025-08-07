package org.anubis.lectures.studentmngmtsys.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.File;

public class CourseStorage {

    private final ObservableList<Course> courseData = FXCollections.observableArrayList();
    private final Stage primaryStage;

    public CourseStorage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        courseData.add(new Course("UNIV-101", "Ethics"));
        courseData.add(new Course("CS-Prog1", "Java Beginner"));
        courseData.add(new Course("CS-Prog2", "Java Advanced"));
        courseData.add(new Course("CS-Prog3", "Python"));
        courseData.add(new Course("CS-NET", "Networks"));
        courseData.add(new Course("CS-101-BD", "Big Data"));
        courseData.add(new Course("CS-123-CC", "Compiler Build"));
        courseData.add(new Course("UNIV-212", "Economics"));
        courseData.add(new Course("UNIV-820", "Physics"));
    }

    public ObservableList<Course> getCourseData() {
        return courseData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}