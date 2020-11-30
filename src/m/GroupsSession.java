package m;


import m.session.Session;

import java.util.Set;
import java.util.HashSet;

public class GroupsSession {
    private Session id_Session;
    private GroupPromotion id_Group;

    public GroupsSession(Session id_Session, GroupPromotion id_Group) {
        this.id_Session = id_Session;
        this.id_Group = id_Group;

    }

    public GroupsSession() {}

    public Session getId_Session() {
        return id_Session;
    }

    public void setId_Session(Session id_Session) {
        this.id_Session = id_Session;
    }

    public GroupPromotion getId_Group() {
        return id_Group;
    }

    public void setId_Group(GroupPromotion id_Group) {
        this.id_Group = id_Group;
    }
}

