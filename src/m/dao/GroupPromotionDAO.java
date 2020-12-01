package m.dao;

import c.Database;
import m.GroupPromotion;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupPromotionDAO extends DAO<GroupPromotion> {
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public GroupPromotionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(GroupPromotion obj) {
        return false;
    }

    public boolean delete(GroupPromotion obj) {
        return false;
    }

    public boolean update(GroupPromotion obj) {
        return false;
    }

    public GroupPromotion find(int id) {
        //Group_Promotion group_promotion = new Group_Promotion();
        GroupPromotion group_promotion = new GroupPromotion();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM group_promotion WHERE ID = " + id);



            if(result.first())

            group_promotion.setId(result.getInt("ID"));
            group_promotion.setName(result.getString("NAME"));

            PromotionDAO promotionDAO = new PromotionDAO(cnx);




            group_promotion.setId_promotion(promotionDAO.find(result.getInt("ID_PROMOTION")));


        }


        catch (SQLException e) {
            e.printStackTrace();
        }
        return group_promotion;
    }
}
