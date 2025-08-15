package prototypes2;

public class UserSystem {
    private static User[] users = new User[100];
    private static int usersIndex = 0;

    public void addUser(User creator, User newUser) {

        if (creator.getRole() == Role.ADMINISTRATOR) {
            users[usersIndex] = newUser;
            usersIndex++;
        } else {
            System.err.println("Su rol es " + creator.getRole()
                    + ". No puede agregar usuarios");
        }
    }

    public User findUserById(User userAsking, Integer userId) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {

            for (int i = 0; i < users.length; i++) {
                if (users[i] != null) {
                    if (users[i].getUSER_ID() == userId) {
                        return users[i];
                    }
                }
            }

            System.out.println("El usuario no se encuentra registrado");
            return null;
        } else {
            System.out.println("Su rol del usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar informacion de otros usuarios");
            return null;
        }
    }

    public User findUserByUserName(User userAsking, String userName) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {

            for (int i = 0; i < users.length; i++) {
                if (users[i] != null) {
                    if (users[i].getUSER_NAME().equals(userName)) {
                        return users[i];
                    }
                }
            }

            System.out.println("El usuario no se encuentra registrado");
            return null;
        } else {
            System.out.println("Su rol del usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar informacion de otros usuarios");
            return null;
        }
    }

    public void setName(User userAsking, User targetUser, String name) {
        if(userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser){
            targetUser.setName(name);
        }else{
            System.out.println("Su rol de usuario es " + userAsking.getRole() 
            + ". No tiene permitido modificar nombres de otros usuarios");
        }
    }

    public String getName(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getName();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permiso para ver el nombre de otros usuarios");
            return null;
        }
    }

    // no administrador
    public void setPassword(User userAsking, User targetUser, String oldPassword, String newPassword) {
        if(userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser){
            targetUser.setPassword(userAsking, oldPassword, newPassword);
        } else{
            System.out.println("Su usuario es " +  userAsking.getRole() 
            + ". No puede modificar la contraseña de otros usuarios");
        }
    }

    // administrador
    public void setPasswordIfAdmin(User userAsking, User targetUser, String newPassword) {
        targetUser.setPassword(userAsking, null, newPassword);
    }

    public String getPassword(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getPassword();
        } else {
            System.err.println(
                    "Su usuario es " + userAsking.getRole() + ". No puede acceder a las contraseñas de otros usuarios");
            return null;
        }
    }

    public void deleteUser(User userAsking, User targetUser) {

        if (userAsking.getRole() == Role.ADMINISTRATOR) {

            for (int i = 0; i < users.length; i++) {

                if (users[i] == targetUser) {

                    User[] newArray = new User[100];
                    for (int j = 0; j < i; j++) {
                        newArray[j] = users[j];
                    }
                    for (int j = i; j < users.length - 1; j++) {
                        newArray[j] = users[j + 1];
                    }

                    UserSystem.users = newArray;
                    UserSystem.usersIndex--;
                    System.out.println("\nusuario eliminado exitosamente");
                    return;
                }
            }

            System.out.println("\nel usuario no se encuentra registrado en el sistema");
        } else {
            System.out
                    .println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido eliminar usuarios");
        }
    }

    public void setRole(User userAsking, User targetUser, Role role) {
        targetUser.setRole(userAsking, role);
    }

    public Role getRole(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getRole();
        } else {
            System.out.println("Su rol de usario es " + userAsking.getRole()
                    + ". No tiene permitido consultar el rol de otros usuarios");
            return null;
        }
    }

    public Integer getUSER_ID(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getUSER_ID();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar el ID de otros usuarios");
            return null;
        }
    }

    public void setUSER_ID(User userAsking, User targetUser, Integer newUserId) {
        targetUser.setUSER_ID(userAsking, newUserId);
    }

    public String getUSER_NAME(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getUSER_NAME();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar el nombre de usuario de otros usuarios");
            return null;
        }
    }

    public void setUSER_NAME(User userAsking, User targetUser, String newUserName) {
        targetUser.setUSER_NAME(userAsking, newUserName);
    }

    public static User[] getUsers(User userAsking) {
        if(userAsking.getRole() == Role.ADMINISTRATOR){
            return users;
        } else {
            System.out.println("atun");
            return null;
        }
    }

    //TODO: eliminar metodo de imprimir
    public void imprimir(User userAsking){
        for(User u: users){
            if(u != null){
                System.out.println();
                System.out.println("name: " + getName(userAsking,u));
                System.out.println("user_id: " + getUSER_ID(userAsking, u));
                System.out.println("user_name: " + getUSER_NAME(userAsking, u));
                System.out.println("role: " + getRole(userAsking, u));
                System.out.println("password: "  + getPassword(userAsking, u));
                System.out.println();
            }
        }
    }
}
