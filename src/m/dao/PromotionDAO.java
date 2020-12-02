package m.dao;

import m.Course;
import m.GroupPromotion;
import  m.Promotion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO extends DAO<Promotion> {

    public PromotionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Promotion obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO promotion(NAME)" +
                    "values( '" + obj.getName()+ "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Promotion obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM promotion WHERE ID =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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

    public List<Promotion> getAll() {
        List<Promotion> listPromotions = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM promotion");

            boolean next = result.next();

            while (next) {
                listPromotions.add(new Promotion(result.getInt("ID"), result.getString("NAME")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listPromotions;
    }

}
