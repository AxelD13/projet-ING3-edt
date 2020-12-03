package m.dao;

import c.Database;
import m.*;
import m.session.EnumState;
import m.session.Session;
import m.typecourse.TypeCourse;
import m.user.User;
import java.sql.Connection;
import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TestDAO {

    public static void main(String[] args) {
        Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
        Connection cnx = db.connectDB();

        /*
        DAO<Session> sessionDAO = new SessionDAO(cnx);

        Session testSession = new Session(12, new Date(1607554800L * 1000),
                new Time(8, 0, 0), new Time(9, 30, 0),
                EnumState.PROCESSING, 1, 1);

        sessionDAO.create(testSession);

        DAO<User> userDao = new UserDAO(cnx);
        List<User> listUsers = userDao.getAll();
        for(User user : listUsers) {
            System.out.println(user.toString());
        }

        System.out.println();
        System.out.println("///////////////////////");
        System.out.println();

        StudentDAO studentDAO = new StudentDAO(cnx);
        List<Student> listStudents = studentDAO.getAll();  //getAll(idGroupePromotion) pour avoir les élèves d'un groupe
        for(Student student : listStudents) {
            System.out.println(student.toString());
        }

        System.out.println();
        System.out.println("///////////////////////");
        System.out.println();

        DAO<Teacher> teacherDAO = new TeacherDAO(cnx);
        List<Teacher> listTeachers = teacherDAO.getAll();
        for(Teacher teacher : listTeachers) {
            System.out.println(teacher.toString());
        }

        System.out.println();
        System.out.println("///////////////////////");
        System.out.println();

        DAO<Course> courseDAO = new CourseDAO(cnx);
        List<Course> listCourses = courseDAO.getAll();
        for(Course course : listCourses) {
            System.out.println(course.getName());
        }

        System.out.println();
        System.out.println("///////////////////////");
        System.out.println();

        DAO<GroupPromotion> groupPromotionDAO = new GroupPromotionDAO(cnx);
        List<GroupPromotion> listGroupsPromotion = groupPromotionDAO.getAll();
        for(GroupPromotion groupPromotion : listGroupsPromotion) {
            System.out.println(groupPromotion.toString());
        }

        System.out.println();
        System.out.println("///////////////////////");
        System.out.println();

         */
    }

}

