package m;

public class GroupPromotion {
    private int id;
    private String name;
    private Promotion id_promotion;

    public GroupPromotion(int id, String name, Promotion id_promotion) {
        this.id = id;
        this.name = name;
        this.id_promotion = id_promotion;
    }

    public GroupPromotion(){}

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

    public Promotion getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(Promotion id_promotion) {
        this.id_promotion = id_promotion;
    }
}
