package m.dao;

import c.Database;
import m.Course;
import m.GroupPromotion;
import m.session.EnumState;
import m.session.Session;
import m.typecourse.TypeCourse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionDAO extends DAO<Session> {
    Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
    Connection cnx = db.connectDB();

    public SessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Session obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO session(WEEK, DATE, START_TIME, END_TIME, STATE, ID_COURSE, ID_TYPE)" +
                    "values('" + obj.getWeek() + "', '" + obj.getDate() + "', '" + obj.getStartTime() + "', '"
                    + obj.getEndTime() + "', '" + obj.getState() + "', '" + obj.getIdCourse() + "', '" + obj.getIdType()
                    + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Session obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM session WHERE ID =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Session obj) {
        return false;
    }

    public Session find(int id) {
        Session session = new Session();
        CourseDAO courseDAO = new CourseDAO(cnx);
        TypeCourseDAO typeCourseDAO = new TypeCourseDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM session WHERE ID = " + id);

            if (result.first()) {
                session = new Session(id,
                        result.getInt("WEEK"),
                        result.getDate("DATE"),
                        result.getTime("START_TIME"),
                        result.getTime("END_TIME"),
                        EnumState.valueOf(result.getString("STATE").toUpperCase()),
                        result.getInt("ID_COURSE"),
                        result.getInt("ID_TYPE"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return session;
    }

    public List<Session> getAll() {
        List<Session> listSessions = new ArrayList<>();
        CourseDAO courseDAO = new CourseDAO(cnx);
        TypeCourseDAO typeCourseDAO = new TypeCourseDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM session");

            boolean next = result.next();

            while (next) {
                listSessions.add(new Session(result.getInt("ID"),
                        result.getInt("WEEK"),
                        result.getDate("DATE"),
                        result.getTime("START_TIME"),
                        result.getTime("END_TIME"),
                        EnumState.valueOf(result.getString("STATE").toUpperCase()),
                        result.getInt("ID_COURSE"),
                        result.getInt("ID_TYPE")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSessions;
    }

}
