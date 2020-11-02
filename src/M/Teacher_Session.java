package M;

public class Teacher_Session {
    private Session id_Session;
    private Teacher id_Teacher;

    public Teacher_Session(int id, int week, String date, String start_Time, String end_Time, Session.State state, int idCourse, String name, int idType, Type_Course.Name nameType, int idTeacher, String email, String password, String lastName, String firstName, User.Permission permission, Course id_course) {
        this.id_Session = new Session(id, week, date, start_Time, end_Time, state, idCourse, name, idType, nameType);
        this.id_Teacher = new Teacher(idTeacher, email, password, lastName, firstName, permission, id_course);


    }

    public Session getId_Session() {
        return id_Session;
    }

    public void setId_Session(Session id_Session) {
        this.id_Session = id_Session;
    }

    public Teacher getId_Teacher() {
        return id_Teacher;
    }

    public void setId_Teacher(Teacher id_Teacher) {
        this.id_Teacher = id_Teacher;
    }
}
