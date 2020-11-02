package M;

import java.time.LocalDateTime;

public class Session {
    private int id;
    private int week;
    private String date;
    private String Start_Time;
    private String End_Time;
    private State state;
    private Course id_Course;
    private Type_Course id_Type;

    public Session(int id, int week, String date, String start_Time, String end_Time, State state, int idCourse, String name, int idType, Type_Course.Name nameType) {
        this.id = id;
        this.week = week;
        this.date = date;
        this.Start_Time = start_Time;
        this.End_Time = end_Time;
        this.state = state;
        this.id_Course = new Course(idCourse, name);
        this.id_Type = new Type_Course(idType, nameType);
    }

    public enum State{VALIDEE, ANNULE}

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(String start_Time) {
        this.Start_Time = start_Time;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String end_Time) {
        this.End_Time = end_Time;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Course getId_Course() {
        return id_Course;
    }

    public void setId_Course(Course id_Course) {
        this.id_Course = id_Course;
    }

    public Type_Course getId_Type() {
        return id_Type;
    }

    public void setId_Type(Type_Course id_Type) {
        this.id_Type = id_Type;
    }
}
