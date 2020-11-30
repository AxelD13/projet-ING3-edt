package m.dao;
import m.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAO extends DAO<Course> {

    public CourseDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Course obj) {
        return false;
    }

    public boolean delete(Course obj) {
        return false;
    }

    public boolean update(Course obj) {
        return false;
    }

    public Course find(int id) {
        Course course = new Course();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM course WHERE ID = " + id);
            if(result.first())
                course = new Course(id, result.getString("NAME"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
}
