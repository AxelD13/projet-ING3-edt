package m.dao;

import c.Database;
import m.Course;
import m.Student;
import m.Teacher;
import m.user.EnumPermission;
import m.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherDAO extends DAO<Teacher> {

    public TeacherDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Teacher obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO user(EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, PERMISSION)" +
                    "VALUES('" + obj.getEmail() + "', '" + obj.getPassword() + "', '" + obj.getLastName() + "', '"
                    + obj.getFirstName() + "', '" + EnumPermission.TEACHER.getValue() + "')");

            this.connect.createStatement().executeUpdate("INSERT INTO teacher(ID_USER, ID_COURSE) " +
                        "VALUES(LAST_INSERT_ID(), " + obj.getHashSetCourses().iterator().next().getId() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Teacher obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM teacher WHERE ID_USER =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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
            ).executeQuery("SELECT * FROM user INNER JOIN student s on user.ID = s.ID_USER WHERE ID = " + id);

            if(result.first()) {
                teacher = new Teacher(id, result.getString("EMAIL"),
                        result.getString("PASSWORD"),
                        result.getString("LAST_NAME"),
                        result.getString("FIRST_NAME"),
                        getCourses(id));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }

    public List<Teacher> getAll() {  //retourne une liste de tous les professeurs
        List<Teacher> listTeachers = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM user RIGHT JOIN teacher t on user.ID = t.ID_USER");

            boolean next = result.next();

            while (next) {
                listTeachers.add(new Teacher(result.getInt("ID"), result.getString("EMAIL"),
                        result.getString("PASSWORD"),
                        result.getString("LAST_NAME"),
                        result.getString("FIRST_NAME"),
                        getCourses(result.getInt("ID"))));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTeachers;
    }

    public Set<Course> getCourses(int id) {  //retourne un Set des matières enseignées par un professeur
        Set<Course> hashSetCourses = new HashSet<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM teacher WHERE ID_USER = " + id);

            boolean next = result.next();

            CourseDAO courseDAO = new CourseDAO(this.connect);

            while (next) {
                hashSetCourses.add(courseDAO.find(result.getInt("ID_COURSE")));
                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hashSetCourses;
    }

}
