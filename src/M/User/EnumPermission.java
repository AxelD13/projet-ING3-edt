package M.User;

import java.util.HashMap;
import java.util.Map;

public enum EnumPermission {
    ADMIN(1),
    REFERENT(2),
    TEACHER(3),
    STUDENT(4);

    private final int value;
    private static Map map = new HashMap<>();

    EnumPermission(int value) {
        this.value = value;
    }

    static {
        for (EnumPermission pageType : EnumPermission.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static EnumPermission valueOf(int n) {
        return (EnumPermission) map.get(n);
    }

    public int getValue() {
        return value;
    }
}
