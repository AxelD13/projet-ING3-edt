package m;

import java.util.HashSet;
import java.util.Set;

public class Promotion {

    private int id;
    private String name;
    private Set<GroupPromotion> listGroups;

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

    public void addGroup(GroupPromotion g) { listGroups.add(g); }

    public void removeGroup(GroupPromotion g) { listGroups.remove(g); }

}
