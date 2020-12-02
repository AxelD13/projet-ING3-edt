package m.dao;

import c.Database;
import m.GroupsSession;
import m.RoomsSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomsSessionDAO extends DAO<RoomsSession> {

    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public RoomsSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(RoomsSession obj) {
        return false;
    }

    public boolean delete(RoomsSession obj) {
        return false;
    }

    public boolean update(RoomsSession obj) {
        return false;
    }


    public RoomsSession find(int id) {
        RoomsSession RoomsSession = new RoomsSession();
        SessionDAO sessionDAO = new SessionDAO(cnx);
        RoomDAO roomDAO = new RoomDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM rooms_session WHERE ID_SESSION = " + id);
            if (result.first())
                RoomsSession = new RoomsSession(
                        sessionDAO.find(result.getInt("ID_SESSION")),
                        roomDAO.find(result.getInt("ID_ROOM")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return RoomsSession;
    }

    public List<RoomsSession> getAll() { return null; }

}
