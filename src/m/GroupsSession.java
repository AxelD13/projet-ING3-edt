package m;

import m.session.EnumState;
import m.session.Session;

import java.sql.Date;
import java.sql.Time;

public class GroupsSession extends Session {

    private int idGroupPromotion;

    public GroupsSession(){}

    public GroupsSession(int week, Date date, Time startTime,
                         Time endTime, EnumState state, int idCourse, int idType, int idGroupPromotion) {
        super(week, date, startTime, endTime, state, idCourse, idType);
        this.idGroupPromotion = idGroupPromotion;
    }

    public GroupsSession(int idSession, int week, Date date, Time startTime,
                         Time endTime, EnumState state, int idCourse, int idType, int idGroupPromotion) {
        super(idSession, week, date, startTime, endTime, state, idCourse, idType);
        this.idGroupPromotion = idGroupPromotion;
    }

    public int getIdGroupPromotion() {
        return idGroupPromotion;
    }

    public void setIdGroupPromotion(int idGroupPromotion) {
        this.idGroupPromotion = idGroupPromotion;
    }

}
