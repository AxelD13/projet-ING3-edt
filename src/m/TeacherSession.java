package m;

import m.session.Session;

import java.util.HashSet;
import java.util.Set;

public class TeacherSession {
    private Session id_Session;
    private Teacher id_Teacher;


    public TeacherSession(Session id_Session, Teacher id_Teacher) {
        this.id_Session = id_Session;
        this.id_Teacher = id_Teacher;


    }


    public TeacherSession(){}

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
