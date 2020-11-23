package M.DAO;

import M.User.*;

import java.security.Permission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO<User> {

    public UserDAO(Connection conn) {
        super(conn);
    }

    public boolean create(User obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO user(EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, PERMISSION)" +
                    "values('" + obj.getEmail() + "', '" + obj.getPassword() + "', '" + obj.getLastName() + "', '"
                    + obj.getFirstName() + "', '" + obj.getPermission().getValue()
                    + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(User obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM user WHERE ID =" + obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User obj) {
        return false;
    }

    public User find(int id) {
        User user = new User();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM user WHERE ID = " + id);

            if(result.first())
                user = new User(id, result.getString("EMAIL"),
                        result.getString("PASSWORD"),
                        result.getString("LAST_NAME"),
                        result.getString("FIRST_NAME"),
                        EnumPermission.valueOf(result.getInt("PERMISSION")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}