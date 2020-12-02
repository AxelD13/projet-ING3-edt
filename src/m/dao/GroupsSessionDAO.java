package m.dao;

import c.Database;
import m.GroupsSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public GroupsSession find(int id) {
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
                        groupPromotionDAO.find(result.getInt("ID_GROUP")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsSession;
    }

    public List<GroupsSession> getAll() { return null; }

}
