package m.dao;

import c.Database;
import m.TeachersSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeachersSessionDAO extends DAO<TeachersSession>{

    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public TeachersSessionDAO(Connection conn) {
        super(conn);
    }

    public boolean create(TeachersSession obj) {
        return false;
    }

    public boolean delete(TeachersSession obj) {
        return false;
    }

    public boolean update(TeachersSession obj) {
        return false;
    }

    public TeachersSession find(int id) {
        TeachersSession TeachersSession = new TeachersSession();
        SessionDAO sessionDAO = new SessionDAO(cnx);
        TeacherDAO teacherDAO = new TeacherDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM teachers_session WHERE ID_SESSION = " + id);
            if (result.first())
                TeachersSession = new TeachersSession(
                        sessionDAO.find(result.getInt("ID_SESSION")),
                        teacherDAO.find(result.getInt("ID_TEACHER")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return TeachersSession;
    }

    public List<TeachersSession> getAll() { return null; }
}
