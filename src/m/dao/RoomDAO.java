package m.dao;

import c.Database;
import m.Course;
import m.GroupPromotion;
import m.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM room WHERE ID = " + id);

            if(result.first()) {
                room = new Room(id, result.getString("NAME"),
                        result.getInt("CAPACITY"), result.getInt("ID_SITE"));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return room;
    }

    public List<Room> getAll() {
        List<Room> listRooms = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM room");

            boolean next = result.next();

            while (next) {
                listRooms.add(new Room(result.getInt("ID"), result.getString("NAME"),
                        result.getInt("CAPACITY"), result.getInt("ID_SITE")));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listRooms;
    }

}


