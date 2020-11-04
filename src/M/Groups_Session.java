package M;


import java.util.Set;
import java.util.HashSet;

public class Groups_Session {
    private Session id_Session;
    private Set <Group_Promotion> id_Group = new HashSet<Group_Promotion>();

    public Groups_Session(int id, int week, String date, String start_Time, String end_Time, Session.State state, int idCourse, String name, int idType, Type_Course.Name nameType) {
        this.id_Session = new Session(id, week, date, start_Time, end_Time, state, idCourse, name, idType, nameType);

    }

    public Groups_Session() {}

    public Session getId_Session() {
        return id_Session;
    }

    public void setId_Session(Session id_Session) {
        this.id_Session = id_Session;
    }


    public Set<Group_Promotion> getId_Group() {
        return id_Group;
    }

    public void setId_Group(Set<Group_Promotion> id_Group) {
        this.id_Group = id_Group;
    }

    public void addGroup_Session(Group_Promotion group_promotion){
        this.id_Group.add(group_promotion);
    }
public void removeGroup_Session(Group_Promotion group_promotion){ this.id_Group.remove(group_promotion);
}
}
