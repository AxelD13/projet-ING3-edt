package m.dao;

import c.Database;
import m.RoomsSession;
import m.session.EnumState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomsSessionDAO extends DAO<RoomsSession> {

    public RoomsSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(RoomsSession obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO session(WEEK, DATE, START_TIME, END_TIME, STATE, ID_COURSE, ID_TYPE)" +
                    "VALUES('" + obj.getWeek() + "', '" + obj.getDate() + "', '" + obj.getStartTime() + "', '"
                    + obj.getEndTime() + "', '" + obj.getState() + "', '" + obj.getIdCourse() + "', '" + obj.getIdType()
                    + "')");

            this.connect.createStatement().executeUpdate("INSERT INTO rooms_session(ID_SESSION, ID_ROOM)" +
                    "VALUES(LAST_INSERT_ID(), " + obj.getIdRoom() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(RoomsSession obj) {
        try {
            this.connect.createStatement().executeUpdate(
                    "DELETE FROM rooms_session WHERE ID_SESSION =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(RoomsSession obj) {
        return false;
    }


    public RoomsSession find(int id) {
        RoomsSession RoomsSession = new RoomsSession();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM session INNER JOIN rooms_session rs " +
                    "on session.ID = rs.ID_SESSION WHERE ID = " + id);

            if (result.first()) {
                RoomsSession = new RoomsSession(id,
                        result.getInt("WEEK"),
                        result.getDate("DATE"),
                        result.getTime("START_TIME"),
                        result.getTime("END_TIME"),
                        EnumState.valueOf(result.getString("STATE").toUpperCase()),
                        result.getInt("ID_COURSE"),
                        result.getInt("ID_TYPE"),
                        result.getInt("ID_ROOM"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return RoomsSession;
    }

    public List<RoomsSession> getAll() { return null; }

}
