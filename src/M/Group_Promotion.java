package M;

public class Group_Promotion {
    private String name;
    //private Promotion promotion;
    private int idPromotion;

    public Group_Promotion(int id, String name, int idPromotion) {
        //this.promotion = new Promotion(id, name);
        this.name = name;
        this.idPromotion = idPromotion;
    }

    public Group_Promotion(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    */

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int id_promotion) {
        this.idPromotion = id_promotion;
    }
}
