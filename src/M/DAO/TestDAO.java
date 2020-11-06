package M.DAO;

import Control.Database;
import M.Promotion;
import M.User.User;

import java.sql.Connection;


public class TestDAO {
        public static void main(String[] args) {
            Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
            Connection cnx = db.connectDB();

            DAO<Promotion> promotionDAO = new PromotionDOA(cnx);
            for(int i = 1; i < 4; i++){
                Promotion promotion = promotionDAO.find(i);
                System.out.println("Promotion N°" + promotion.getId() + "  - " + promotion.getName() );
            }

            DAO<User> userDao = new UserDAO(cnx);
            for(int i = 39; i < 47; i++){
                User user = userDao.find(i);
                System.out.println("User N°" + user.getId() + "  - " + user.getLastName() + " " + user.getFirstName());
            }
        }
}
