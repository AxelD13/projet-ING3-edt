package m.dao;

import m.GroupsSession;
import m.session.EnumState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupsSessionDAO extends DAO<GroupsSession> {

    public GroupsSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(GroupsSession obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO session(WEEK, DATE, START_TIME, END_TIME, STATE, ID_COURSE, ID_TYPE)" +
                    "VALUES('" + obj.getWeek() + "', '" + obj.getDate() + "', '" + obj.getStartTime() + "', '"
                    + obj.getEndTime() + "', '" + obj.getState() + "', '" + obj.getIdCourse() + "', '" + obj.getIdType()
                    + "')");

            this.connect.createStatement().executeUpdate("INSERT INTO groups_session(ID_SESSION, ID_GROUP)" +
                    "VALUES(LAST_INSERT_ID(), " + obj.getIdGroupPromotion() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(GroupsSession obj) {
        try {
            this.connect.createStatement().executeUpdate(
                    "DELETE FROM groups_session WHERE ID_SESSION =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(GroupsSession obj) {
        return false;
    }

    public GroupsSession find(int id) {
        GroupsSession groupsSession = new GroupsSession();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM session INNER JOIN groups_session gs " +
                    "on session.ID = gs.ID_SESSION WHERE ID = " + id);

            if (result.first()) {
                groupsSession = new GroupsSession(id,
                        result.getInt("WEEK"),
                        result.getDate("DATE"),
                        result.getTime("START_TIME"),
                        result.getTime("END_TIME"),
                        EnumState.valueOf(result.getString("STATE").toUpperCase()),
                        result.getInt("ID_COURSE"),
                        result.getInt("ID_TYPE"),
                        result.getInt("ID_GROUP"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupsSession;
    }

    public List<GroupsSession> getAll() { return null; }

}
