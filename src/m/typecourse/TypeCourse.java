package m.typecourse;

public class TypeCourse {
    private int id;
    private EnumName name;

    public TypeCourse(int id, EnumName name) {
        this.id = id;
        this.name = name;
    }

    public TypeCourse(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumName getName() {
        return name;
    }

    public void setName(EnumName name) {
        this.name = name;
    }
}
