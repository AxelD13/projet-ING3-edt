package m;

import m.session.EnumState;
import m.session.Session;
import m.typecourse.TypeCourse;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TeachersSession {

    private Session session;

    private Teacher teacher;

    public TeachersSession() {}

    public TeachersSession(Session session, Teacher teacher) {
        this.session = session;
        this.teacher = teacher;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
