package M;

public class Room {
    private int id;
    private String name;
    private int capacity;
    private Site id_site;

    public Room(int id, String name, int capacity, int idSite, String nameSite) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.id_site = new Site(idSite, nameSite);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Site getId_site() {
        return id_site;
    }

    public void setId_site(Site id_site) {
        this.id_site = id_site;
    }
}
