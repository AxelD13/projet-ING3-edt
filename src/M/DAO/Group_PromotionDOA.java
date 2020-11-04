package M.DAO;

import M.Group_Promotion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Group_PromotionDOA extends DAO<Group_Promotion>{

    public Group_PromotionDOA(Connection conn) {
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
            ).executeQuery("SELECT * FROM user WHERE user_id = " + id);
            if(result.first())
                group_promotion = new Group_Promotion(id, result.getString("group_promotion_name"), result.getInt("id_promotion"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group_promotion;
    }
}
