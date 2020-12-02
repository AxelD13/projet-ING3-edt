package m;


import m.session.EnumState;
import m.session.Session;

import java.sql.Date;
import java.sql.Time;

public class RoomsSession extends Session{

    private int idRoom;

    public RoomsSession(){}

    public RoomsSession(int week, Date date, Time startTime,
                        Time endTime, EnumState state, int idCourse, int idType, int idRoom) {
        super(week, date, startTime, endTime, state, idCourse, idType);
        this.idRoom = idRoom;
    }

    public RoomsSession(int idSession, int week, Date date, Time startTime,
                        Time endTime, EnumState state, int idCourse, int idType, int idRoom) {
        super(idSession, week, date, startTime, endTime, state, idCourse, idType);
        this.idRoom = idRoom;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

}
