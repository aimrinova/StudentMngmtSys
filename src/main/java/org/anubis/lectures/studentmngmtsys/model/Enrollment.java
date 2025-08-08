package org.anubis.lectures.studentmngmtsys.model;

import javafx.beans.property.*;

public class Enrollment {
    private final ObjectProperty<Student> student = new SimpleObjectProperty<>();
    private final ObjectProperty<Course>  course  = new SimpleObjectProperty<>();
    private final DoubleProperty          grade   = new SimpleDoubleProperty();

    public Enrollment(Student s, Course c) {
        student.set(s);
        course.set(c);
        grade.set(0.0);        // default
    }

    public Student getStudent()       { return student.get(); }
    public void setStudent(Student s){ student.set(s); }
    public ObjectProperty<Student> studentProperty(){ return student; }

    public Course getCourse()         { return course.get(); }
    public void  setCourse(Course c)  { course.set(c); }
    public ObjectProperty<Course> courseProperty(){ return course; }

    public double getGrade()          { return grade.get(); }
    public void setGrade(double g)    { grade.set(g); }
    public DoubleProperty gradeProperty(){ return grade; }
}
