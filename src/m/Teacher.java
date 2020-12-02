package m;

import m.user.*;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {

    private Set<Course> hashSetCourses  = new HashSet<>();

    private int nbHours = 0;

    public Teacher() {}

    public Teacher(int idUser, String email, String password, String lastName, String firstName, Set<Course> hashSetCourses) {
        super(idUser, email, password, lastName, firstName, EnumPermission.TEACHER);
        this.hashSetCourses.addAll(hashSetCourses);
    }

    public Set<Course> getHashSetCourses() { return hashSetCourses; }

    public String displayCourses() {
        String stringCourses = "";

        for(Course course : hashSetCourses) {
            stringCourses += course.getName();
            stringCourses += " ";
        }
        return stringCourses;
    }

    public void addCourse(Course course){
        this.hashSetCourses.add(course);
    }

    public void removeCourse(Course course){
        this.hashSetCourses.remove(course);
    }

    public int getNbHours() { return nbHours; }

    public void updateNbHours(int h) { this.nbHours += h; }

    @Override
    public String toString() { return getLastName() + " " + getFirstName() + " " + displayCourses(); };
    //problème ici avec hashSetCourses.toString, il faudra sûrement override la fonction toString

}
