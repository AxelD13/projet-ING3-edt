import Control.Database;
import V.Acceuil;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
        Connection cnx = db.connectDB();

        Acceuil ac = new Acceuil(db, cnx);
    }

}
