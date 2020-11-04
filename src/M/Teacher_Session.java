package M;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Teacher_Session {
    private Session id_Session;
    private Set<Teacher> id_Teacher = new HashSet<Teacher>();


    public Teacher_Session(int id, int week, String date, String start_Time, String end_Time, Session.State state, int idCourse, String name, int idType, Type_Course.Name nameType) {
        this.id_Session = new Session(id, week, date, start_Time, end_Time, state, idCourse, name, idType, nameType);


    }


    public Teacher_Session(){}

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
