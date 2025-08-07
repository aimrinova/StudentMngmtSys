package org.anubis.lectures.studentmngmtsys.model;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javafx.beans.property.*;
import org.anubis.lectures.studentmngmtsys.util.LocalDateAdapter;

public class Student {
    private static int idCounter = 0;

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty studentId;
    private final StringProperty studyField;
    private final IntegerProperty credits;
    private final DoubleProperty avgGrade;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
    public Student() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Student(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.studyField = new SimpleStringProperty("CS");
        // build ID: first two letters of studyField + zero-padded counter
        this.studentId  = new SimpleStringProperty(
                studyField.get().substring(0,2).toUpperCase()
                        + String.format("%05d", ++idCounter)
        );
        this.credits = new SimpleIntegerProperty(0);
        this.avgGrade = new SimpleDoubleProperty(0.0);
        // Some initial dummy data, just for convenient testing.
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) { this.lastName.set(lastName); }
    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStudentId() {
        return studentId.get();
    }
    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }
    public StringProperty studentIdProperty() {
        return studentId;
    }

    public String getStudyField() { return studyField.get(); }
    public void setStudyField(String studyField) {
        this.studyField.set(studyField);
    }
    public StringProperty studyFieldProperty() { return studyField; }

    public int getCredits() { return credits.get(); }
    public void setCredits(int credits) {
        this.credits.set(credits);
    }
    public IntegerProperty creditsProperty() { return credits; }

    public double getAvgGrade() { return avgGrade.get(); }
    public void setAvgGrade(double avgGrade) { this.avgGrade.set(avgGrade); }
    public DoubleProperty avgGradeProperty() { return avgGrade; }

    public String getStreet() {
        return street.get();
    }
    public void setStreet(String street) {
        this.street.set(street);
    }
    public StringProperty streetProperty() {
        return street;
    }

    public int getPostalCode() {
        return postalCode.get();
    }
    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }
    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public String getCity() {
        return city.get();
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public StringProperty cityProperty() {
        return city;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }
    public void setBirthday(LocalDate birthday) { this.birthday.set(birthday); }
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public static int getIdCounter() { return idCounter;}
    public static void setIdCounter(int idCounter) { Student.idCounter = idCounter; }
}
