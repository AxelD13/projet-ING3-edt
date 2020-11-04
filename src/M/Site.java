package M;

import java.util.ArrayList;
import java.util.List;

public class Site {
    private int id;
    private List<String> name;

    public Site(int id, List<String> name) {
        this.id = id;
        this.name = new ArrayList<>();
    }
public Site(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
