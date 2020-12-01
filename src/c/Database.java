package c;

import java.sql.*;
import java.util.Scanner;

public class Database {

    private String url, user, password;

    public Database(String url, String user, String password) {
        this.url = url;
        this.user= user;
        this.password = password;
    }

    public Connection connectDB() { //permet de se connecter à la bdd
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver ok.");

            Connection cnx = DriverManager.getConnection(url, user, password);

            System.out.println("Connexion établie.");
            return cnx;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void queryDB(Connection cnx) { //permet de récuperer des données dans la bdd
        ResultSet results = null;
        String query = "SELECT * FROM user"; //modifier la requête en fonction des besoins

        try {
            Statement stmt = cnx.createStatement();
            results = stmt.executeQuery(query); //utiliser .executeUpdate() pour une maj de la bdd
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

    public String identification(Connection cnx, String emailIn, String passwordIn) { //permet de s'identifier pr accéder à l'edt
        int id = 0;
        boolean emailOK = false;

        ResultSet results = null;
        String query = null;
        Scanner sc = new Scanner(System.in);

        //VERIFICATION EMAIL//
        try {
            query = "SELECT * FROM user";
            Statement stmt = cnx.createStatement();
            results = stmt.executeQuery(query);

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
                return "Email introuvable.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //VERIFICATION PASSWORD//
        try {
            query = "SELECT PASSWORD FROM user WHERE ID = " + id;
            Statement stmt = cnx.createStatement();
            results = stmt.executeQuery(query);

            if(results.next()) {
                if (!passwordIn.equals(results.getString("PASSWORD"))) {
                    return "Mot de passe incorrect.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "OK"; //si email + mdp valides, on retourne OK
    }
}
