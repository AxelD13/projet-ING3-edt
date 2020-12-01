package m.dao;

import c.Database;
import m.*;
import m.session.Session;
import m.typecourse.TypeCourse;
import m.user.User;

import java.sql.Connection;


public class TestDAO {
    public static void main(String[] args) {
        Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
        Connection cnx = db.connectDB();

        DAO<User> userDao = new UserDAO(cnx);
        for(int i = 39; i <59; i++){
            User user = userDao.find(i);
            System.out.println("User N°" + user.getId() + "  - " + user.getLastName() + " " + user.getFirstName() + "  -  Email : " + user.getEmail() + "  - Password : " +user.getPassword() + "  -  Permission" + user.getPermission());
        }

        DAO<Promotion> promotionDAO = new PromotionDAO(cnx);
        for(int i = 1; i < 4; i++){
            Promotion promotion = promotionDAO.find(i);
            System.out.println("Promotion N°" + promotion.getId() + "  - " + promotion.getName());
        }

        DAO<GroupPromotion> group_promotionDOA = new GroupPromotionDAO(cnx);
        for(int i = 1; i < 7; i++){
            GroupPromotion group_promotion = group_promotionDOA.find(i);
            System.out.println("Group promotion N°" + group_promotion.getId()+ "  - " + group_promotion.getName() + "  - " + group_promotion.getId_promotion().getName());
        }

        DAO<Student> studentDAO = new StudentDAO(cnx);
        for(int i = 42; i < 43; i++){
            Student student = studentDAO.find(i);
            System.out.println("Student N°" + student.getUser().getId()+ "  - " + student.getUser().getFirstName()+ "  - " +student.getUser().getLastName()+ "  - " +student.getUser().getEmail()+ "  - " +student.getUser().getPassword()+ "  - " +student.getUser().getPermission()+ "  - " +student.getNumber()+ "  - " +student.getId_Group_Promotion().getName() );
        }
        for(int i = 44; i < 48; i++){
            Student student = studentDAO.find(i);
            System.out.println("Student N°" + student.getUser().getId()+ "  - " + student.getUser().getFirstName()+ "  - " +student.getUser().getLastName()+ "  - " +student.getUser().getEmail()+ "  - " +student.getUser().getPassword()+ "  - " +student.getUser().getPermission()+ "  - " +student.getNumber()+ "  - " +student.getId_Group_Promotion().getName() );
        }

        DAO<Course> courseDAO = new CourseDAO(cnx);
        for(int i = 1; i <7; i++){
            Course course = courseDAO.find(i);
            System.out.println("Cours N°" + course.getId() + "  - " + course.getName() );
        }
        for(int i = 13; i <14; i++){
            Course course = courseDAO.find(i);
            System.out.println("Cours N°" + course.getId() + "  - " + course.getName() );
        }

        DAO<TypeCourse> type_courseDAO = new TypeCourseDAO(cnx);
        for(int i = 1; i <7; i++){
            TypeCourse type_course = type_courseDAO.find(i);
            System.out.println("Type de Cours N°" + type_course.getId() + "  - " + type_course.getName() );
        }



        DAO<Teacher> teacherDAO = new TeacherDAO(cnx);
        for(int i = 41; i < 42; i++){
            Teacher teacher = teacherDAO.find(i);
            System.out.println("Teacher N°" + teacher.getUser().getId()+ "  - Email " + teacher.getUser().getEmail()+ "  -  " +teacher.getUser().getFirstName()+ "  - " +teacher.getUser().getLastName()+ "  - " +teacher.getUser().getPassword()+ "  - " +teacher.getUser().getPermission()+ "  - " +teacher.getCourse().getName()  );
        }
        for(int i = 43; i < 44; i++){
            Teacher teacher = teacherDAO.find(i);
            System.out.println("Teacher N°" + teacher.getUser().getId()+ "  - Email " + teacher.getUser().getEmail()+ "  -  " +teacher.getUser().getFirstName()+ "  - " +teacher.getUser().getLastName()+ "  - " +teacher.getUser().getPassword()+ "  - " +teacher.getUser().getPermission()+ "  - " +teacher.getCourse().getName()  );
        }
        for(int i = 50; i < 51; i++){
            Teacher teacher = teacherDAO.find(i);
            System.out.println("Teacher N°" + teacher.getUser().getId()+ "  - Email " + teacher.getUser().getEmail()+ "  -  " +teacher.getUser().getFirstName()+ "  - " +teacher.getUser().getLastName()+ "  - " +teacher.getUser().getPassword()+ "  - " +teacher.getUser().getPermission()+ "  - " +teacher.getCourse().getName()  );
        }
        for(int i = 52; i < 54; i++){
            Teacher teacher = teacherDAO.find(i);
            System.out.println("Teacher N°" + teacher.getUser().getId()+ "  - Email " + teacher.getUser().getEmail()+ "  -  " +teacher.getUser().getFirstName()+ "  - " +teacher.getUser().getLastName()+ "  - " +teacher.getUser().getPassword()+ "  - " +teacher.getUser().getPermission()+ "  - " +teacher.getCourse().getName()  );
        }
        for(int i = 55; i < 56; i++){
            Teacher teacher = teacherDAO.find(i);
            System.out.println("Teacher N°" + teacher.getUser().getId()+ "  - Email " + teacher.getUser().getEmail()+ "  -  " +teacher.getUser().getFirstName()+ "  - " +teacher.getUser().getLastName()+ "  - " +teacher.getUser().getPassword()+ "  - " +teacher.getUser().getPermission()+ "  - " +teacher.getCourse().getName()  );
        }
        for(int i = 57; i < 60; i++){
            Teacher teacher = teacherDAO.find(i);
            System.out.println("Teacher N°" + teacher.getUser().getId()+ "  - Email " + teacher.getUser().getEmail()+ "  -  " +teacher.getUser().getFirstName()+ "  - " +teacher.getUser().getLastName()+ "  - " +teacher.getUser().getPassword()+ "  - " +teacher.getUser().getPermission()+ "  - " +teacher.getCourse().getName()  );
        }

        DAO<Site> siteDAO = new SiteDAO(cnx);
        for(int i = 1; i < 3; i++){
            Site site = siteDAO.find(i);
            System.out.println("Site N°" + site.getId()+ "  - " + site.getName() );
        }


        DAO<Room> roomDAO = new RoomDAO(cnx);
        for(int i = 1; i < 13; i++){
            Room room = roomDAO.find(i);
            System.out.println("Room N°" + room.getId()+ "  - " + room.getName()+ "  - Capacite : " +room.getCapacity()+ "  - " +room.getId_site().getName()  );
        }
        DAO<Session> sessionDAO = new SessionDAO(cnx);
        for(int i = 1; i < 13; i++){
            Session session = sessionDAO.find(i);
            System.out.println("Session N°" + session.getId()+ "  - Week : " + session.getWeek()+ "  - Date :  " +session.getDate()+ "  -StartTime :  " +session.getStart_Time()+ "  -EndTime :  " +session.getEnd_Time()+ "  -State :  " +session.getState()+ "  -Course :  " +session.getId_Course().getName()+ "  -Type :  " +session.getId_Type().getName()     );
        }

        DAO<GroupsSession> groupsSessionDAO = new GroupsSessionDAO(cnx);
        for(int i = 1; i < 13; i++){
            GroupsSession groupsSession = groupsSessionDAO.find(i);
            System.out.println("Group_session : Session N°" + groupsSession.getId_Session().getId() + "  - Week : " + groupsSession.getId_Session().getWeek()+ "  - Date :  " +groupsSession.getId_Session().getDate()+ "  -StartTime :  " +groupsSession.getId_Session().getStart_Time()+ "  -EndTime :  " +groupsSession.getId_Session().getEnd_Time()+ "  -State :  " +groupsSession.getId_Session().getState()+ "  -Course :  " +groupsSession.getId_Session().getId_Course().getName()+ "  -Type :  " +groupsSession.getId_Session().getId_Type().getName()+ "  -Group :  " +groupsSession.getId_Group().getName()+ "  -Promotion :  " +groupsSession.getId_Group().getId_promotion().getName() );
        }

        DAO<TeacherSession> teacherSessionDAO = new TeacherSessionDAO(cnx);
        for(int i = 1; i < 13; i++){
            TeacherSession teacherSession = teacherSessionDAO.find(i);
            System.out.println("Teacher_session : Session N°" + teacherSession.getId_Session().getId() + "  - Week : " + teacherSession.getId_Session().getWeek()+ "  - Date :  " +teacherSession.getId_Session().getDate()+ "  -StartTime :  " +teacherSession.getId_Session().getStart_Time()+ "  -EndTime :  " +teacherSession.getId_Session().getEnd_Time()+ "  -State :  " +teacherSession.getId_Session().getState()+ "  -Course :  " +teacherSession.getId_Session().getId_Course().getName()+ "  -Type :  " +teacherSession.getId_Session().getId_Type().getName()+ "  -Teacher N° :  " +teacherSession.getId_Teacher().getUser().getId()+ "  -  " +teacherSession.getId_Teacher().getUser().getEmail()+ "  -  " +teacherSession.getId_Teacher().getUser().getPassword()+ "  -  " +teacherSession.getId_Teacher().getUser().getLastName()+ "  -  " +teacherSession.getId_Teacher().getUser().getFirstName()+ "  -  " +teacherSession.getId_Teacher().getUser().getPermission()+ "  -Cours" +teacherSession.getId_Teacher().getCourse().getName());
        }

        DAO<RoomSession> roomSessionDAO = new RoomSessionDAO(cnx);
        for(int i = 1; i < 13; i++){
            RoomSession roomSession = roomSessionDAO.find(i);
            System.out.println("Room_session : Session N°" + roomSession.getId_Session().getId() + "  - Week : " + roomSession.getId_Session().getWeek()+ "  - Date :  " +roomSession.getId_Session().getDate()+ "  -StartTime :  " +roomSession.getId_Session().getStart_Time()+ "  -EndTime :  " +roomSession.getId_Session().getEnd_Time()+ "  -State :  " +roomSession.getId_Session().getState()+ "  -Course :  " +roomSession.getId_Session().getId_Course().getName()+ "  -Type :  " +roomSession.getId_Session().getId_Type().getName()+ "  -Room N° :  " +roomSession.getId_Room().getId()+ "  -  " +roomSession.getId_Room().getName()+ "  -Capacity  " +roomSession.getId_Room().getCapacity()+ "  - Site " +roomSession.getId_Room().getId_site().getName());
        }


    }
}

