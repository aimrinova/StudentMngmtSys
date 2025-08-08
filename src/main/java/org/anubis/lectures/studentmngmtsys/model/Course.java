package org.anubis.lectures.studentmngmtsys.model;

import javafx.beans.property.*;
import org.anubis.lectures.studentmngmtsys.MainApp;

public class Course {
    private static int idCounter = 0;

    private final StringProperty courseId;
    private final StringProperty courseTitle;
    private final StringProperty courseDescription;
    private final IntegerProperty courseValue;
    private final IntegerProperty courseVolume;

    // Reference to the main application.
    private MainApp mainApp;
    private ViewDataBar viewDataBar;
    private StudentStorage storage;

    /**
     * Default constructor.
     */
    public Course() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param courseId
     * @param courseTitle
     */
    public Course(String courseId, String courseTitle) {
        this.courseId = new SimpleStringProperty(courseId);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.courseDescription = new SimpleStringProperty("courseDescription");
        this.courseValue = new SimpleIntegerProperty(6);
        this.courseVolume = new SimpleIntegerProperty(120);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseTitle=" + courseTitle +
                '}';
    }

    public String getCourseId() { return courseId.get(); }
    public void setCourseID(String courseId) { this.courseId.set(courseId); }
    public StringProperty courseIdProperty() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle.get();
    }
    public void setCourseTitle(String courseTitle) { this.courseTitle.set(courseTitle); }
    public StringProperty courseTitleProperty() {
        return courseTitle;
    }

    public int getCourseVolume() {
        return courseVolume.get();
    }
    public void setCourseVolume(int courseVolume) {
        this.courseVolume.set(courseVolume);
    }
    public IntegerProperty courseVolumeProperty() {
        return courseVolume;
    }

    public int getCourseValue() {
        return courseValue.get();
    }
    public void setCourseValue(int courseValue) {
        this.courseValue.set(courseValue);
    }
    public IntegerProperty courseValueProperty() {
        return courseValue;
    }

    public String getCourseDescription() {
        return courseDescription.get();
    }
    public void setCourseDescription(String courseDescription) { this.courseDescription.set(courseDescription); }
    public StringProperty courseDescriptionProperty() { return courseDescription; }
}
