package controller_model;

public class User {
    private String name;
    private String password;
    private Role role;
    private static Integer id_generator = 0;
    private Integer userId;
    private String userName;
    private String userHistory;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.userId = id_generator++;
        this.userName = name + userId;
        this.userHistory = "";
    }

    // el modificador de acceso de todos los getters y setters es default para que App (capa vista)
    // no pueda acceder a User y alterarlo. Como User y UserSystem estan en el mismo
    // paquete, UserSystem sí puede modificar datos de User.

    Role getRole() {
        return role;
    }

    void setRole(Role role) {
        this.role = role;
    }

    Integer getuserId() {
        return userId;
    }

    void setName(String name) {
        this.name = name;
    }

    String getPassword() {
        return password;
    }

    String getName() {
        return name;
    }

    String getuserName() {
        return userName;
    }

    void setUserId(Integer userId) {
        this.userId = userId;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String userData() {
        return "User [name=" + name + ", password=" + password + ", role=" + role + ", userId=" + userId + ", userName="
                + userName + "]";
    }

    String getUserHistory() {
        return userHistory;
    }

    void setUserHistory(String userHistory) {
        this.userHistory = this.userHistory + "\nevento: " + userHistory + "\n";
    }

    

}
