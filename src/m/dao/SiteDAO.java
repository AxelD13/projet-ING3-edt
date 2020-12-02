package m.dao;

import m.Course;
import m.GroupPromotion;
import m.Site;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO extends DAO<Site> {
    public SiteDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Site obj) {
        return false;
    }

    public boolean delete(Site obj) {
        return false;
    }

    public boolean update(Site obj) {
        return false;
    }

    public Site find(int id) {
        Site site = new Site();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM site WHERE ID = " + id);

            if(result.first()) {
                site = new Site(id, result.getString("NAME"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return site;
    }

    public List<Site> getAll() {
        List<Site> listSites = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM site");

            boolean next = result.next();

            while (next) {
                listSites.add(new Site(result.getInt("ID"), result.getString("NAME")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSites;
    }

}
