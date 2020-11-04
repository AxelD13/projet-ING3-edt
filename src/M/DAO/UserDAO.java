package M.DAO;

import M.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/*public class UserDAO extends DAO<User> {

        public UserDAO(Connection conn) {
            super(conn);
        }

        public boolean create(User obj) {
            return false;
        }

        public boolean delete(User obj) {
            return false;
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
                ).executeQuery("SELECT * FROM user WHERE user_id = " + id);
                if(result.first())
                    user = new User(id, result.getString("user_email"), result.getString("user_last_name"), result.getString("user_first_name"), result.User.getPermission("user_permission"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }
    }

*/