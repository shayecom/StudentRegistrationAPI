package studentsRegistrationAPI.studentRegistrationAPI.model;


public class UserDetails {
    private Long id;
    private String username;
    private String password;
    private String roles;
    private String permissions;
    private int active;


    public UserDetails() {
    }

    public UserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDetails(Long id, String username, String password, String roles, String permissions, int active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
