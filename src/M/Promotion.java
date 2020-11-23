package M;

import java.util.HashSet;
import java.util.Set;

public class Promotion {

    private int id;
    private String name;
    private Set<Group_Promotion> listGroups;

    public Promotion(int id, String name) {
        this.id = id;
        this.name = name;
        this.listGroups = new HashSet<>();
    }

    public Promotion(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGroup(Group_Promotion g) { listGroups.add(g); }

    public void removeGroup(Group_Promotion g) { listGroups.remove(g); }

}
