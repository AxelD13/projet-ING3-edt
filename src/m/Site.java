package m;

import java.util.HashSet;
import java.util.Set;

public class Site {

    private int id;

    private String name;

    private Set<Room> hashSetRooms;

    public Site(){}

    public Site(int id, String name) {
        this.id = id;
        this.name = name;
        this.hashSetRooms = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Room> getHashSetRooms() {
        return hashSetRooms;
    }

    public void addRoom(Room r) { hashSetRooms.add(r); }

    public void removeRoom(Room r) { hashSetRooms.remove(r); }

}
