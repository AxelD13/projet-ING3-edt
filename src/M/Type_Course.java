package M;

public class Type_Course {
    private int id;
    private Name name;

    public Type_Course(int id, Name name) {
        this.id = id;
        this.name = name;
    }

    public enum Name{LESSON, TD, TP, PROJECT, SUPPORT}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
