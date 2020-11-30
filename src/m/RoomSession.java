package m;


import m.session.Session;

import java.util.HashSet;
import java.util.Set;

public class RoomSession {
    private Session id_Session;
    private Room id_Room;


    public RoomSession(Session id_Session, Room id_Room) {
        this.id_Session = id_Session;
        this.id_Room = id_Room;


    }

    public RoomSession(){}
    public Session getId_Session() {
        return id_Session;
    }

    public void setId_Session(Session id_Session) {
        this.id_Session = id_Session;
    }

    public Room getId_Room() {
        return id_Room;
    }

    public void setId_Room(Room id_Room) {
        this.id_Room = id_Room;
    }
}
