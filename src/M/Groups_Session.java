package M;

public class Groups_Session {
    private Session id_Session;
    private Group_Promotion id_Group;

    public Groups_Session(int id, int week, String date, String start_Time, String end_Time, Session.State state, int idCourse, String name, int idType, Type_Course.Name nameType, int idGroup, String nameGroup, int id_promotion) {
        this.id_Session = new Session(id, week, date, start_Time, end_Time, state, idCourse, name, idType, nameType);
        this.id_Group = new Group_Promotion(idGroup, name, id_promotion);


    }

    public Session getId_Session() {
        return id_Session;
    }

    public void setId_Session(Session id_Session) {
        this.id_Session = id_Session;
    }

    public Group_Promotion getId_Group() {
        return id_Group;
    }

    public void setId_Group(Group_Promotion id_Group) {
        this.id_Group = id_Group;
    }
}
