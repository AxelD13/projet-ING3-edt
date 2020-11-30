package m.dao;

import c.Database;
import m.GroupPromotion;
import m.GroupsSession;
import m.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupsSessionDAO extends DAO<GroupsSession> {
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public GroupsSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(GroupsSession obj) {
        return false;
    }

    public boolean delete(GroupsSession obj) {
        return false;
    }

    public boolean update(GroupsSession obj) {
        return false;
    }

    public GroupsSession
    find(int id) {
        GroupsSession groupsSession = new GroupsSession();
        SessionDAO sessionDAO = new SessionDAO(cnx);
        GroupPromotionDAO groupPromotionDAO = new GroupPromotionDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM groups_session WHERE ID_SESSION = " + id);
            if (result.first())
                groupsSession = new GroupsSession(
                        sessionDAO.find(result.getInt("ID_SESSION")),
                        groupPromotionDAO.find(result.getInt("ID_SITE")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsSession;
    }
}
