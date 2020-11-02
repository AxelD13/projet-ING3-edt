package M;

import java.security.Permission;

public class User {
    int id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private Permission permission;

    public User(int id, String email, String password, String lastName, String firstName, Permission permission){
        this.id = id;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.permission = permission;
    }
public enum Permission {ONE, TWO, THREE, FOUR}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
