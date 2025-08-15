package prototypes2;

public class User {
    //private static User[] users;
    private String name;
    private String password;
    private Role role;
    private static Integer id_generator = 0;
    private Integer USER_ID;
    private String USER_NAME;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.USER_ID = id_generator;
        User.id_generator++;
        this.USER_NAME = name + USER_ID;
        //users = UserSystem.getUsers(this);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(User userAsking, Role role) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            this.role = role;
        } else {
            System.out.println(
                    "Su rol de usuario es " + userAsking.getRole() + ". No puede cambiar el rol de ningún usuario");
        }
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(User userAsking, Integer newUserId) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {

            for (User u : users) {

                if (u != null) {
                    if (u.getUSER_ID() == newUserId) {
                        System.out.println("El usuario " + u.getUSER_NAME()
                                + " ya posee este ID.");
                        return;
                    }
                }

            }

            this.USER_ID = newUserId;
            System.out.println("ID de usuario cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido editar el ID de ningún usuario");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(User userAsking, String newUserName) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || this == userAsking) {

            for (User u : users) {

                if (u != null) {
                    if (u.getUSER_NAME().equals(newUserName)) {
                        System.out.println("El usuario con id: " + u.getUSER_ID()
                                + " , ya posee este nombre de usuario. Debe seleccionar un nombre de usuario unico para cada usuario");
                        return;
                    }
                }
            }

            this.USER_NAME = newUserName;
            System.out.println("nombre de usuario cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido editar el nombre de usuario de otros usuarios");
        }
    }
}
