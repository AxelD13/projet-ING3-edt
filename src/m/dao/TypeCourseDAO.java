package m.dao;
import m.typecourse.EnumName;
import m.typecourse.TypeCourse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        TypeCourse type_course = new TypeCourse();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM type_course WHERE ID = " + id);
            if(result.first())
                type_course = new TypeCourse(id,
                        EnumName.valueOf(result.getString("NAME").toUpperCase()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type_course;
    }
}
