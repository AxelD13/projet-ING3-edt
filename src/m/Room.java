package m;

public class Room {

    private int id;

    private String name;

    private int capacity;

    private int idSite;

    public Room() {}

    public Room(int id, String name, int capacity, int idSite) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.idSite = idSite;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getIdSite() {
        return idSite;
    }

    public void setIdSite(int idSite) {
        this.idSite = idSite;
    }
}
