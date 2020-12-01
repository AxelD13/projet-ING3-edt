package m.dao;
import c.Database;
import m.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO extends DAO<Student> {
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();


    public StudentDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Student obj) {
        return false;
    }

    public boolean delete(Student obj) {
        return false;
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
            ).executeQuery("SELECT * FROM student WHERE ID_USER = " + id);

            UserDAO userDAO = new UserDAO(cnx);
            GroupPromotionDAO group_promotionDAO = new GroupPromotionDAO(cnx);
            if(result.first())

                student = new Student(


                        userDAO.find(result.getInt("ID_USER")),
                        result.getInt("NUMBER"),
                        group_promotionDAO.find(result.getInt("ID_GROUP_PROMOTION"))
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}




