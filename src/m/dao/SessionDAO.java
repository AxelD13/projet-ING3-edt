package m.dao;

import c.Database;
import m.session.EnumState;
import m.session.Session;
import m.typecourse.EnumName;
import m.typecourse.TypeCourse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDAO extends DAO<Session> {
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public SessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Session obj) {
        return false;
    }

    public boolean delete(Session obj) {
        return false;
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
            if (result.first())
                session = new Session(id,
                        result.getInt("WEEK"),
                        result.getDate("DATE"),
                        result.getTime("START_TIME"),
                        result.getTime("END_TIME"),
                        EnumState.valueOf(result.getString("STATE").toUpperCase()),
                        courseDAO.find(result.getInt("ID_COURSE")),
                        typeCourseDAO.find(result.getInt("ID_TYPE"))
                );


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return session;
    }
}
