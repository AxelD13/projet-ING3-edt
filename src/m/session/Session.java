package m.session;

import m.Course;
import m.typecourse.TypeCourse;

import java.sql.Time;
import java.util.Date;

public class Session {

    private int id;
    private int week;
    private Date date;
    private Time startTime;
    private Time endTime;
    private EnumState state;
    private Course idCourse;
    private TypeCourse idType;

    public Session(int id, int week, Date date, Time start_Time, Time end_Time, EnumState state, Course idCourse, TypeCourse idType) {
        this.id = id;
        this.week = week;
        this.date = date;
        this.startTime = start_Time;
        this.endTime = end_Time;
        this.state = state;
        this.idCourse = idCourse;
        this.idType = idType;
    }

    public Session(){}

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

    public Course getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Course idCourse) {
        this.idCourse = idCourse;
    }

    public TypeCourse getIdType() {
        return idType;
    }

    public void setIdType(TypeCourse idType) {
        this.idType = idType;
    }
}
