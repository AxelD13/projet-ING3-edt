package m.dao;

import c.Database;
import m.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO extends DAO<Room> {
    Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
    Connection cnx = db.connectDB();

    public RoomDAO(Connection conn) {
        super(conn);
    }

    public boolean create(Room obj) {
        return false;
    }

    public boolean delete(Room obj) {
        return false;
    }

    public boolean update(Room obj) {
        return false;
    }

    public Room find(int id) {
        Room room = new Room();
        SiteDAO siteDAO = new SiteDAO(cnx);

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM room WHERE ID = " + id);
            if (result.first())
                room = new Room(id, result.getString("NAME"),
                        result.getInt("CAPACITY"),
                        siteDAO.find(result.getInt("ID_SITE")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }
}


