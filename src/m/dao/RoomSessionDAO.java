package m.dao;

import c.Database;
import m.GroupsSession;
import m.RoomSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomSessionDAO extends DAO<RoomSession> {
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public RoomSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(RoomSession obj) {
        return false;
    }

    public boolean delete(RoomSession obj) {
        return false;
    }

    public boolean update(RoomSession obj) {
        return false;
    }

    public RoomSession
    find(int id) {
        RoomSession roomSession = new RoomSession();
        SessionDAO sessionDAO = new SessionDAO(cnx);
        RoomDAO roomDAO = new RoomDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM rooms_session WHERE ID_SESSION = " + id);
            if (result.first())
                roomSession = new RoomSession(
                        sessionDAO.find(result.getInt("ID_SESSION")),
                        roomDAO.find(result.getInt("ID_ROOM")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomSession;
    }
}
