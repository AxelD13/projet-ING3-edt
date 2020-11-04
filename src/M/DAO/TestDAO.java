package M.DAO;

import Control.Database;
import M.Promotion;
import java.sql.Connection;


public class TestDAO {
        public static void main(String[] args) {
            Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
            Connection cnx = db.connectDB();
            DAO<Promotion> promotionDAO = new PromotionDOA(cnx);
            for(int i = 1; i < 5; i++){
                Promotion promotion = promotionDAO.find(i);
                System.out.println("Promotion°" + promotion.getId() + "  - " + promotion.getName() );
            }
        }
}
