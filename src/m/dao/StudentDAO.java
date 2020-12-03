package m.dao;

import c.Database;

import m.GroupPromotion;
import m.Student;
import m.user.EnumPermission;
import m.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentDAO extends DAO<Student>{

    public StudentDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Student obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO user(EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, PERMISSION)" +
                    "VALUES('" + obj.getEmail() + "', '" + obj.getPassword() + "', '" + obj.getLastName() + "', '"
                    + obj.getFirstName() + "', '" + EnumPermission.STUDENT.getValue() + "')");

            this.connect.createStatement().executeUpdate("INSERT INTO student(ID_USER, NUMBER, ID_GROUP_PROMOTION)" +
                    "VALUES(LAST_INSERT_ID(), " + obj.getNumber() + ", " + obj.getIdGroupPromotion() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Student obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM student WHERE ID_USER =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Student obj) {
        return false;
    }

    public Student find(int id) {
        Student student = new Student();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM user INNER JOIN student s on user.ID = s.ID_USER WHERE ID = " + id);

            if (result.first()) {
                student = new Student(id, result.getString("EMAIL"),
                        result.getString("PASSWORD"),
                        result.getString("LAST_NAME"),
                        result.getString("FIRST_NAME"),
                        result.getInt("NUMBER"),
                        result.getInt("ID_GROUP_PROMOTION"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public List<Student> getAll() {
        List<Student> listStudents = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM user RIGHT JOIN student s on user.ID = s.ID_USER");

            boolean next = result.next();

            while (next) {
                listStudents.add(new Student(result.getInt("ID"), result.getString("EMAIL"),
                        result.getString("PASSWORD"),
                        result.getString("LAST_NAME"),
                        result.getString("FIRST_NAME"),
                        result.getInt("NUMBER"),
                        result.getInt("ID_GROUP_PROMOTION")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listStudents;
    }

    public List<Student> getAll(int idGroupPromotion) {  //retourne une liste de tous les élèves d'un groupe
        List<Student> listStudents = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM user RIGHT JOIN student s on user.ID = s.ID_USER " +
                    "WHERE ID_GROUP_PROMOTION = " + idGroupPromotion);

            boolean next = result.next();

            while (next) {
                listStudents.add(new Student(result.getInt("ID"), result.getString("EMAIL"),
                        result.getString("PASSWORD"),
                        result.getString("LAST_NAME"),
                        result.getString("FIRST_NAME"),
                        result.getInt("NUMBER"),
                        result.getInt("ID_GROUP_PROMOTION")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listStudents;
    }

    public int generateRandomNumber() {  //génère un Number aléatoire (utile pour la création d'un Student)
        List<Integer> listNumbers = new ArrayList<>();
        List<Student> listStudents = getAll();
        for(Student student : listStudents) {
            listNumbers.add(student.getNumber());
        }
        Random rnd = new Random();
        int number = rnd.nextInt(1000 - listNumbers.size());
        for(Integer integer : listNumbers) {
            if (number < integer) {
                break;
            }
            number++;
        }
        return number;
    }

}




