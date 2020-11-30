package m.dao;

import c.Database;
import m.TeacherSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherSessionDAO extends DAO<TeacherSession>{
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public TeacherSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(TeacherSession obj) {
        return false;
    }

    public boolean delete(TeacherSession obj) {
        return false;
    }

    public boolean update(TeacherSession obj) {
        return false;
    }

    public TeacherSession find(int id) {
        TeacherSession teacherSession = new TeacherSession();
        SessionDAO sessionDAO = new SessionDAO(cnx);
        TeacherDAO teacherDAO = new TeacherDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM teachers_session WHERE ID_SESSION = " + id);
            if (result.first())
                teacherSession = new TeacherSession(
                        sessionDAO.find(result.getInt("ID_SESSION")),
                        teacherDAO.find(result.getInt("ID_TEACHER")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherSession;
    }
}
