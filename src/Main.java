import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connection cnx = connectDB();
        //queryDB(cnx);
        identification(cnx);
    }

    public static Connection connectDB() { //permet de se connecter à la bdd
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void queryDB(Connection cnx) { //permet de récuperer des données dans la bdd
        ResultSet results = null;
        String requete = "SELECT * FROM user";

        try {
            Statement stmt = cnx.createStatement();
            results = stmt.executeQuery(requete); //utiliser .executeUpdate() pour une maj de la bdd
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSetMetaData resultsMD = results.getMetaData();
            int nbCols = resultsMD.getColumnCount();
            boolean next = results.next();

            while (next) {
               for (int i = 1; i <= nbCols; i++) {
                    System.out.print(results.getString(i) + " ");
               }
               System.out.println();
               next = results.next();
            }

            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void identification(Connection cnx) { //permet de s'identifier pr accéder à l'edt
        int id = 0;
        boolean emailOK = false;

        ResultSet results = null;
        String requete = null;
        Scanner sc = new Scanner(System.in);

        //VERIFICATION EMAIL//
        System.out.println("Email :");
        String emailIn = sc.next();

        try {
            requete = "SELECT * FROM user";
            Statement stmt = cnx.createStatement();
            results = stmt.executeQuery(requete);

            ResultSetMetaData resultsMD = results.getMetaData();
            boolean next = results.next();

            while (next) {
                if (results.getString("EMAIL").equals(emailIn)) {
                    emailOK = true;
                    id = results.getInt("ID");
                }
                next = results.next();
            }

            if(!emailOK) {
                System.out.println("Email introuvable.");
                System.exit(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //VERIFICATION PASSWORD//
        System.out.println("Password :");
        String passwordIn = sc.next();

        try {
            requete = "SELECT PASSWORD FROM user WHERE ID = " + id;
            Statement stmt = cnx.createStatement();
            results = stmt.executeQuery(requete);

            if(results.next()) {
                if (!passwordIn.equals(results.getString("PASSWORD"))) {
                    System.out.println("Mot de passe incorrect.");
                    System.exit(0);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
