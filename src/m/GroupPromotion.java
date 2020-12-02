package m;

public class GroupPromotion {

    private int id;

    private String name;

    private int idPromotion;

    public GroupPromotion(){}

    public GroupPromotion(String name, int idPromotion) {
        this.name = name;
        this.idPromotion = idPromotion;
    }

    public GroupPromotion(int id, String name, int idPromotion) {
        this.id = id;
        this.name = name;
        this.idPromotion = idPromotion;
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

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String toString() { return idPromotion + " " + name; }

}
