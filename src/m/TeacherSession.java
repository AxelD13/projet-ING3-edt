package m;

import m.session.Session;

import java.util.HashSet;
import java.util.Set;

public class TeacherSession {
    private Session id_Session;
    private Set<Teacher> id_Teacher = new HashSet<Teacher>();


    public TeacherSession(Session id_Session, Set<Teacher> id_Teacher) {
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

    public Set<Teacher> getId_Teacher() {
        return id_Teacher;
    }

    public void setId_Teacher(Set<Teacher> id_Teacher) {
        this.id_Teacher = id_Teacher;
    }

    public void addTeacher_Session(Teacher teacher){
        this.id_Teacher.add(teacher);
    }

    public void removeTeacher_Session(Teacher teacher){
        this.id_Teacher.remove(teacher);
    }
}
