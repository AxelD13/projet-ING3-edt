package M;

public class Room_Session {
    private Session id_Session;
    private Room id_Room;

    public Room_Session(int id, int week, String date, String start_Time, String end_Time, Session.State state, int idCourse, String name, int idType, Type_Course.Name nameType, int idRoom, String nameRoom, int capacity, int idSite, String nameSite) {
        this.id_Session = new Session(id, week, date, start_Time, end_Time, state, idCourse, name, idType, nameType);
        this.id_Room = new Room(idRoom, nameRoom, capacity, idSite, nameSite);


    }

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
