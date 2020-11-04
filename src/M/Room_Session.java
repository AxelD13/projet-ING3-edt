package M;


import java.util.HashSet;
import java.util.Set;

public class Room_Session {
    private Session id_Session;
    private Set<Room> id_Room = new HashSet<Room>();


    public Room_Session(int id, int week, String date, String start_Time, String end_Time, Session.State state, int idCourse, String name, int idType, Type_Course.Name nameType) {
        this.id_Session = new Session(id, week, date, start_Time, end_Time, state, idCourse, name, idType, nameType);



    }

    public Room_Session(){}
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
