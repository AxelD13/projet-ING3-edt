package M;

public class Teacher extends User {
private Course id_course;

    public Teacher(int id, String email, String password, String lastName, String firstName, Permission permission, Course id_course) {
        super(id, email, password, lastName, firstName, permission);
        this.id_course = id_course;

    }

    public Course getId_course() {
        return id_course;
    }

    public void setId_course(Course id_course) {
        this.id_course = id_course;
    }
}
