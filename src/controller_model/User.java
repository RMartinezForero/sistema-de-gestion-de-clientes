package controller_model;

public class User {
    private static User[] users;
    private String name;
    private String password;
    private Role role;
    private static Integer id_generator = 0;
    private Integer userId;
    private String userName;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.userId = id_generator++;
        this.userName = name + userId;
        users = UserSystem.getUsers(this);
    }

    public Role getRole() {
        return role;
    }

    // su paquete es default para que puede accederse solo por UserSystem (o el mismo paquete)
    void setRole(Role role) {
        this.role = role;
    }

    // su paquete es default para que puede accederse solo por UserSystem (o el mismo paquete)
    Integer getuserId() {
        return userId;
    }

    /**
     * @param userAsking instancia de tipo User que solicita modificar el ID
     * @param newUserId instancia de tipo User que va a recibir la modificacion de su ID
     */
    public void setuserId(User userAsking, Integer newUserId) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {

            //se verifica que el ID no lo tenga otro usuario
            for (User u : users) {
                if (u != null && u.getuserId().equals(newUserId)) {
                    System.out.println("userName: " + u.getuserName() + ", ya posee este ID.");
                    return;
                }
            }
            this.userId = newUserId;
            System.out.println("ID de usuario cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido editar el ID de ningún usuario");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param userAsking instancia de tipo User que solicita modificar el password
     * @param oldPassword password actual
     * @param newPassword password que reemplazara al actual
     */
    public void setPassword(User userAsking, String oldPassword, String newPassword) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            this.password = newPassword;
            System.out.println("contraseña reestablecida");
        } else {
            if (this.password.equals(oldPassword)) {
                this.password = newPassword;
                System.out.println("\ncontraseña reestablecida");
            } else {
                System.out.println("contraseña invalida o su rol no es de administrador");
                System.out.println("la contraseña no fue modificada.");
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getuserName() {
        return userName;
    }

    /**
     * 
     * @param userAsking instancia de tipo User que solicita modificar userName
     * @param newUserName instancia de tipo String con el nombre nuevo de userName
     */
    public void setuserName(User userAsking, String newUserName) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || this == userAsking) {

            //se verifica si el userName ya lo tiene otro usuario
            for (User u : users) {
                if (u != null && u.getuserName().equals(newUserName)) {
                    System.out.println("El usuario con id: " + u.getuserId() + " , ya posee este nombre de usuario.");
                    return;
                }
            }
            this.userName = newUserName;
            System.out.println("nombre de usuario cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido editar el nombre de usuario de otros usuarios");
        }
    }
}
