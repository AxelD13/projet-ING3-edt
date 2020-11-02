import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection cnx = connectDB();
    }

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver ok.");

            //Pour Windows :

            String url = "jdbc:mysql://localhost:3306/projet_edt";
            String user = "root";
            String password = "";

            //Pour Mac :
/*
            String url = "jdbc:mysql://localhost:8889/projet_edt";
            String user = "root";
            String password = "root";
*/

            Connection cnx = DriverManager.getConnection(url, user, password);

            System.out.println("Connexion établie.");
            return cnx;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
