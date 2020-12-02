package m;

import m.session.Session;

public class GroupsSession {

    private Session session;

    private GroupPromotion groupPromotion;

    public GroupsSession(){}

    public GroupsSession(Session session, GroupPromotion groupPromotion) {
        this.session = session;
        this.groupPromotion = groupPromotion;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session id_Session) {
        this.session = id_Session;
    }

    public GroupPromotion getGroupPromotion() {
        return groupPromotion;
    }

    public void setGroupPromotion(GroupPromotion groupPromotion) {
        this.groupPromotion = groupPromotion;
    }

}
