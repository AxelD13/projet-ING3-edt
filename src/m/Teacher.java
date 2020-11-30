package m;

import m.user.*;

import java.util.HashSet;
import java.util.Set;

public class Teacher {

    private User user;
    private Course course;
    private Set<Course> id_course = new HashSet<Course>();



    public Teacher() {}

    public Teacher(User user, Course course) {
        this.user = user;
        this.course = course;

    }


    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setUser(User user) {
        this.user = user;
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
