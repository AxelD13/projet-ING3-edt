package M;

public class Student extends User{
    private int number;
    private Group_Promotion id_Group_Promotion;

    public Student(int id, String email, String password, String lastName, String firstName, Permission permission, int number, Group_Promotion group_promotion) {
        super(id, email, password, lastName, firstName, permission);
        this.number = number;
        this.id_Group_Promotion = group_promotion;
    }
public Student(){}
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Group_Promotion getId_Group_Promotion() {
        return id_Group_Promotion;
    }

    public void setId_Group_Promotion(Group_Promotion id_Group_Promotion) {
        this.id_Group_Promotion = id_Group_Promotion;
    }
}
