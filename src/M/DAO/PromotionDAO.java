package M.DAO;

import M.Promotion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionDAO extends DAO<Promotion>{

    public PromotionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Promotion obj) {
        return false;
    }

    public boolean delete(Promotion obj) {
        return false;
    }

    public boolean update(Promotion obj) {
        return false;
    }

    public Promotion find(int id) {
        Promotion promotion = new Promotion();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM promotion WHERE ID = " + id);
            if(result.first())
                promotion = new Promotion(id, result.getString("NAME"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotion;
    }
}
