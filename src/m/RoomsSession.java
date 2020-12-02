package m;


import m.session.EnumState;
import m.session.Session;
import m.typecourse.TypeCourse;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RoomsSession {

    private Session session;

    private  Room room;

    public RoomsSession(){}

    public RoomsSession(Session session, Room room) {
        this.session = session;
        this.room = room;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session id_Session) {
        this.session = id_Session;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
