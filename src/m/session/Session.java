package m.session;

import m.Course;
import m.typecourse.TypeCourse;

import java.sql.Time;
import java.sql.Date;

public class Session {

    private int id;
    private int week;
    private Date date;
    private Time startTime;
    private Time endTime;
    private EnumState state;
    private int idCourse;
    private int idType;

    public Session(){}

    public Session(int week, Date date, Time startTime, Time endTime, EnumState state, int idCourse, int idType) {
        this.week = week;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.idCourse = idCourse;
        this.idType = idType;
    }

    public Session(int id, int week, Date date, Time startTime, Time endTime, EnumState state, int idCourse, int idType) {
        this.id = id;
        this.week = week;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.idCourse = idCourse;
        this.idType = idType;
    }

    public int getId() {
        return id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public EnumState getState() {
        return state;
    }

    public void setState(EnumState state) {
        this.state = state;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
}
