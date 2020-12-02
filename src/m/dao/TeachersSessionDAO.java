package m.dao;

import m.TeachersSession;
import m.session.EnumState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeachersSessionDAO extends DAO<TeachersSession>{

    public TeachersSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(TeachersSession obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO session(WEEK, DATE, START_TIME, END_TIME, STATE, ID_COURSE, ID_TYPE)" +
                    "VALUES('" + obj.getWeek() + "', '" + obj.getDate() + "', '" + obj.getStartTime() + "', '"
                    + obj.getEndTime() + "', '" + obj.getState() + "', '" + obj.getIdCourse() + "', '" + obj.getIdType()
                    + "')");

            this.connect.createStatement().executeUpdate("INSERT INTO teachers_session(ID_SESSION, ID_TEACHER)" +
                    "VALUES(LAST_INSERT_ID(), " + obj.getIdTeacher() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(TeachersSession obj) {
        try {
            this.connect.createStatement().executeUpdate(
                    "DELETE FROM teachers_session WHERE ID_SESSION = " + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(TeachersSession obj) {
        return false;
    }

    public TeachersSession find(int id) {
        TeachersSession teachersSession = new TeachersSession();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM session INNER JOIN teachers_session ts " +
                    "on session.ID = ts.ID_SESSION WHERE ID = " + id);

            if (result.first()) {
                teachersSession = new TeachersSession(id,
                        result.getInt("WEEK"),
                        result.getDate("DATE"),
                        result.getTime("START_TIME"),
                        result.getTime("END_TIME"),
                        EnumState.valueOf(result.getString("STATE").toUpperCase()),
                        result.getInt("ID_COURSE"),
                        result.getInt("ID_TYPE"),
                        result.getInt("ID_TEACHER"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachersSession;
    }

    public List<TeachersSession> getAll() { return null; }
}
