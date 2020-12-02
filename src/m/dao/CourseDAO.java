package m.dao;
import m.Course;
import m.user.EnumPermission;
import m.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends DAO<Course> {

    public CourseDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Course obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO course(NAME)" +
                    "values('" + obj.getName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Course obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM course WHERE ID =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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

            if(result.first()) {
                course = new Course(id, result.getString("NAME"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    public List<Course> getAll() {
        List<Course> listCourses = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM course");

            boolean next = result.next();

            while (next) {
                listCourses.add(new Course(result.getInt("ID"), result.getString("NAME")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCourses;
    }

}
