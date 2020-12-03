package m.dao;

import c.Database;
import m.Course;
import m.GroupPromotion;
import m.user.EnumPermission;
import m.user.User;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupPromotionDAO extends DAO<GroupPromotion>{

    public GroupPromotionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(GroupPromotion obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO group_promotion(NAME, ID_PROMOTION)" +
                    "values('" + obj.getName() + "', '" + obj.getIdPromotion() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(GroupPromotion obj) {
        try {
            this.connect.createStatement().executeUpdate(
                    "DELETE FROM group_promotion WHERE ID =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(GroupPromotion obj) {
        return false;
    }

    public GroupPromotion find(int id) {
        GroupPromotion groupPromotion = new GroupPromotion();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM group_promotion WHERE ID = " + id);

            if(result.first()) {
                groupPromotion = new GroupPromotion(id,
                        result.getString("NAME"),
                        result.getInt("ID_PROMOTION"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupPromotion;
    }

    public List<GroupPromotion> getAll() {
        List<GroupPromotion> listGroupsPromotion = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM group_promotion");

            boolean next = result.next();

            while (next) {
                listGroupsPromotion.add(new GroupPromotion(result.getInt("ID"), result.getString("NAME"),
                        result.getInt("ID_PROMOTION")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listGroupsPromotion;
    }
    public GroupPromotion find(String name) {
        GroupPromotion groupPromotion = new GroupPromotion();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM group_promotion WHERE NAME = " + "'" + name + "'");
            if(result.first()) {
                groupPromotion = new GroupPromotion(result.getInt("ID"),
                        result.getString("NAME"),
                        result.getInt("ID_PROMOTION"));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupPromotion;
    }
}
