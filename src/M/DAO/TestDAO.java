package M.DAO;

import Control.Database;
import M.Course;
import M.Group_Promotion;
import M.Promotion;
import M.Room;
import M.User.User;

import java.sql.Connection;


public class TestDAO {
    public static void main(String[] args) {
        Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
        Connection cnx = db.connectDB();

        DAO<User> userDao = new UserDAO(cnx);
        for(int i = 39; i < 47; i++){
            User user = userDao.find(i);
            System.out.println("User N°" + user.getId() + "  - " + user.getLastName() + " " + user.getFirstName() + "  -  Email : " + user.getEmail() + "  - Password : " +user.getPassword() + "  -  Permission" + user.getPermission());
        }

        DAO<Promotion> promotionDAO = new PromotionDAO(cnx);
        for(int i = 1; i < 4; i++){
            Promotion promotion = promotionDAO.find(i);
            System.out.println("Promotion N°" + promotion.getId() + "  - " + promotion.getName());
        }

        DAO<Group_Promotion> group_promotionDOA = new Group_PromotionDAO(cnx);
        for(int i = 1; i < 7; i++){
            Group_Promotion group_promotion = group_promotionDOA.find(i);
            System.out.println("Group promotion N°" + i + "  - " + group_promotion.getName() + "  - " + group_promotion.getIdPromotion());
        }

        DAO<Course> CourseDAO = new CourseDAO(cnx);
        for(int i =1; i < 8; i++){
            Course course = CourseDAO.find(i);
            System.out.println("Course N°" + course.getId() + " - " + course.getName()) ;
        }

        DAO<Room> RoomDAO = new RoomDAO(cnx);
        for(int i=1; i < 13 ; i++ ){
            Room room = RoomDAO.find(i);
            System.out.println("Room N°" + room.getId() + " - " + room.getName() + " - " + room.getCapacity() + " - " + room.getId_site());
        }






        /*
        DAO<Course> courseDAO = new CourseDAO(cnx);
        for(int i = 1; i <7; i++){
            Course course = courseDAO.find(i);
            System.out.println("Cours N°" + course.getId() + "  - " + course.getName() );
        }
        for(int i = 13; i <14; i++){
            Course course = courseDAO.find(i);
            System.out.println("Cours N°" + course.getId() + "  - " + course.getName() );
        }
            DAO<Type_Course> type_courseDAO = new Type_CourseDAO(cnx);
            for(int i = 1; i <7; i++){
                Type_Course type_course = type_courseDAO.find(i);
                System.out.println("Type de Cours N°" + type_course.getId() + "  - " + type_course.getName() );
            }
        */
    }
}
