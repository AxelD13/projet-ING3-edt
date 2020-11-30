package m.session;

import m.Course;
import m.typecourse.EnumName;
import m.typecourse.TypeCourse;

import java.sql.Time;
import java.util.Date;

public class Session {
    private int id;
    private int week;
    private Date date;
    private Time Start_Time;
    private Time End_Time;
    private EnumState state;
    private Course id_Course;
    private TypeCourse id_Type;

    public Session(int id, int week, Date date, Time start_Time, Time end_Time, EnumState state, Course id_Course, TypeCourse id_Type) {
        this.id = id;
        this.week = week;
        this.date = date;
        this.Start_Time = start_Time;
        this.End_Time = end_Time;
        this.state = state;
        this.id_Course = id_Course;
        this.id_Type = id_Type;
    }

    public Session(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Time getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(Time start_Time) {
        Start_Time = start_Time;
    }

    public Time getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(Time end_Time) {
        End_Time = end_Time;
    }

    public EnumState getState() {
        return state;
    }

    public void setState(EnumState state) {
        this.state = state;
    }

    public Course getId_Course() {
        return id_Course;
    }

    public void setId_Course(Course id_Course) {
        this.id_Course = id_Course;
    }

    public TypeCourse getId_Type() {
        return id_Type;
    }

    public void setId_Type(TypeCourse id_Type) {
        this.id_Type = id_Type;
    }
}
