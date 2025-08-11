package model;

public class User {
    private String name;
    private static Integer id_generator = 0;
    private final Integer USER_ID;
    private final String userName;
    private String password;
    private static User[] users = new User[100];
    private static Integer userIndex = 0;

    public User(String name, String password) {
        this.name = name;
        id_generator++;
        this.USER_ID = id_generator;
        this.userName = name + USER_ID;
        this.password = password;
    }

    public static User[] getUsers() {
        return users;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public static Integer getUserIndex() {
        return userIndex;
    }

    public static void increaseUserIndex() {
        userIndex++;
    }

    public static void decreaseUserIndex(){
        userIndex--;
    }

    public static void setUsers(User[] users) {
        User.users = users;
    }
}
