package M.DAO;

import Control.Database;
import M.Group_Promotion;
import M.Promotion;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Group_PromotionDAO extends DAO<Group_Promotion>{
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public Group_PromotionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Group_Promotion obj) {
        return false;
    }

    public boolean delete(Group_Promotion obj) {
        return false;
    }

    public boolean update(Group_Promotion obj) {
        return false;
    }

    public Group_Promotion find(int id) {
        Group_Promotion group_promotion = new Group_Promotion();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM group_promotion WHERE ID = " + id);
            //PromotionDAO promotionDAO = new PromotionDAO(cnx);
            if(result.first())

                group_promotion = new Group_Promotion(id, result.getString("NAME"), result.getInt("ID_PROMOTION"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group_promotion;
    }
}
