package M;

import M.User.*;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {

    private Set<Course> id_course = new HashSet<Course>();

    public Teacher() {}

    public Teacher(String email, String password, String lastName, String firstName) {
        super(email, password, lastName, firstName, EnumPermission.TEACHER);
    }

    public Teacher(int id, String email, String password, String lastName, String firstName) {
        super(id, email, password, lastName, firstName, EnumPermission.TEACHER);
    }

    public Set<Course> getId_course() {
        return id_course;
    }

    public void setId_course(Set<Course> id_course) {
        this.id_course = id_course;
    }

    void addCourse(Course course){
        this.id_course.add(course);
    }

    void removeCourse(Course course){
        this.id_course.remove(course);
    }
}
