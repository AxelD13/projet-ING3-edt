package m.dao;

import c.Database;
import m.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO extends DAO<Teacher> {
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();


    public TeacherDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Teacher obj) {
        return false;
    }

    public boolean delete(Teacher obj) {
        return false;
    }

    public boolean update(Teacher obj) {
        return false;
    }

    public Teacher find(int id) {
        Teacher teacher = new Teacher();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM teacher WHERE ID_USER = " + id);

            UserDAO userDAO = new UserDAO(cnx);
            CourseDAO courseDAO = new CourseDAO(cnx);
            if(result.first())

                teacher = new Teacher(
                        userDAO.find(result.getInt("ID_USER")),
                        courseDAO.find(result.getInt("ID_COURSE"))
                        );




        } catch (SQLException e) {
            e.printStackTrace();
        }


        return teacher;
    }
}
