package M;

public class Group_Promotion {
    private Promotion promotion;
    private int id_promotion;

    public Group_Promotion(int id, String name, int id_promotion) {
        this.promotion = new Promotion(id, name);
        this.id_promotion = id_promotion;
    }

    public Group_Promotion(){}

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }
}
