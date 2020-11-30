package m;

import m.user.*;

public class Student {
    private User user;
    private int number;
    private GroupPromotion id_Group_Promotion;


    public Student(User user, int number, GroupPromotion group_promotion) {
        this.user = user;
        this.number = number;
        this.id_Group_Promotion = group_promotion;
    }
public Student(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public GroupPromotion getId_Group_Promotion() {
        return id_Group_Promotion;
    }

    public void setId_Group_Promotion(GroupPromotion id_Group_Promotion) {
        this.id_Group_Promotion = id_Group_Promotion;
    }
}
