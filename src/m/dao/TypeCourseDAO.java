package m.dao;
import m.GroupPromotion;
import m.Site;
import m.typecourse.EnumName;
import m.typecourse.TypeCourse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeCourseDAO extends DAO<TypeCourse> {

    public TypeCourseDAO(Connection conn) {
        super(conn);
    }

    public boolean create(TypeCourse obj) {
        return false;
    }

    public boolean delete(TypeCourse obj) {
        return false;
    }

    public boolean update(TypeCourse obj) {
        return false;
    }

    public TypeCourse find(int id) {
        TypeCourse typeCourse = new TypeCourse();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM type_course WHERE ID = " + id);

            if(result.first()) {
                typeCourse = new TypeCourse(id,
                        EnumName.valueOf(result.getString("NAME").toUpperCase()));
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeCourse;
    }

    public List<TypeCourse> getAll() {
        List<TypeCourse> listTypesCourse = new ArrayList<>();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM type_course");

            boolean next = result.next();

            while (next) {
                listTypesCourse.add(new TypeCourse(result.getInt("ID"),
                        EnumName.valueOf(result.getString("NAME").toUpperCase())));

                next = result.next();
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTypesCourse;
    }

}
