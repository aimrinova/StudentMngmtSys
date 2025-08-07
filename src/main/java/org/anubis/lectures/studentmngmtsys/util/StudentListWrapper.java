package org.anubis.lectures.studentmngmtsys.util;

import org.anubis.lectures.studentmngmtsys.model.Student;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


/**
 * Helper class to wrap a list of students. This is used for saving the
 * list of students to XML.
 */
@XmlRootElement(name = "students")
public class StudentListWrapper {

    private List<Student> students;

    @XmlElement(name = "student")
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}