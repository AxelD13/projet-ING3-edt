package m;

import java.util.HashSet;
import java.util.Set;

public class Promotion {

    private int id;

    private String name;

    private Set<GroupPromotion> hashSetGroups;

    public Promotion(){}

    public Promotion(int id, String name) {
        this.id = id;
        this.name = name;
        this.hashSetGroups = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GroupPromotion> getHashSetGroups() {
        return hashSetGroups;
    }

    public void addGroup(GroupPromotion g) { hashSetGroups.add(g); }

    public void removeGroup(GroupPromotion g) { hashSetGroups.remove(g); }

}
