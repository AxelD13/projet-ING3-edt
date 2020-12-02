package m;

import m.session.EnumState;
import m.session.Session;

import java.sql.Date;
import java.sql.Time;

public class TeachersSession extends Session {

    private int idTeacher;

    public TeachersSession() {}

    public TeachersSession(int week, Date date, Time startTime,
                           Time endTime, EnumState state, int idCourse, int idType, int idTeacher) {
        super(week, date, startTime, endTime, state, idCourse, idType);
        this.idTeacher = idTeacher;
    }
    public TeachersSession(int idSession, int week, Date date, Time startTime,
                           Time endTime, EnumState state, int idCourse, int idType, int idTeacher) {
        super(idSession, week, date, startTime, endTime, state, idCourse, idType);
        this.idTeacher = idTeacher;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

}
