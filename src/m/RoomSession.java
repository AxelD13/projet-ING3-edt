package m;


import m.session.Session;

import java.util.HashSet;
import java.util.Set;

public class RoomSession {
    private Session id_Session;
    private Set<Room> id_Room = new HashSet<Room>();


    public RoomSession(Session id_Session, Set<Room> id_Room) {
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

    public Set<Room> getId_Room() {
        return id_Room;
    }

    public void setId_Room(Set<Room> id_Room) {
        this.id_Room = id_Room;
    }

    public void addRoom_Session(Room room){
        this.id_Room.add(room);
    }

    public void removeRoom_Session(Room room){
        this.id_Room.remove(room);
    }
}
