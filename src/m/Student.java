package m;

import m.dao.DAO;
import m.dao.GroupPromotionDAO;
import m.dao.PromotionDAO;
import m.user.*;

import java.sql.Connection;

public class Student extends User {

    private int number;

    private int idGroupPromotion;

    public Student(){}

    public Student(int idUser, String email, String password, String lastName, String firstName, int number, int idGroupPromotion) {
        super(idUser, email, password, lastName, firstName, EnumPermission.STUDENT);
        this.number = number;
        this.idGroupPromotion = idGroupPromotion;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIdGroupPromotion() { return idGroupPromotion; }

    public void setIdGroupPromotion(int idGroupPromotion) {
        this.idGroupPromotion = idGroupPromotion;
    }

    public GroupPromotion getGroupPromotion(Connection cnx) {
        DAO<GroupPromotion> groupPromotionDAO = new GroupPromotionDAO(cnx);
        return groupPromotionDAO.find(this.idGroupPromotion);
    }

    public Promotion getPromotion(Connection cnx) {
        DAO<Promotion> promotionDAO = new PromotionDAO(cnx);
        GroupPromotion groupPromotion = this.getGroupPromotion(cnx);
        return promotionDAO.find(groupPromotion.getIdPromotion());
    }

    @Override
    public String toString() { return number + " " + getLastName() + " " + getFirstName() + " " + getIdGroupPromotion(); }

}
