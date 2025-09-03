package controller_model;

import java.util.Scanner;

public class UserSystem {
    private static User[] users = new User[100];
    private static int usersIndex = 0;

    public UserSystem() {
        if (usersIndex == 0) {
            Scanner input = new Scanner(System.in);
            System.out.println(":::::::::Primer uso del sistema:::::::::\n");
            System.out.print("Nombre del administrador: ");
            String name = input.nextLine();
            System.out.print("Seleccione su contraseña inicial: ");
            String password = input.nextLine();
            users[usersIndex++] = new User(name, password, Role.ADMIN);
            System.out.println("\n:::::::::Usuario administrador establecido::::::::::\n");
            System.out.println("su información de inicio de sesión:\n");
            System.out.println("nombre de usuario: " + users[0].getuserName());
            System.out.println("contraseña: " + users[0].getPassword());
            System.out.println();
            input.close();
        }
    }

    public User logIn(String userName, String password) {
        for (User user : users) {
            if (user != null && user.getuserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @param creator instancia de tipo User que solicita la agregacion de un
     *                usuario
     * @param newUser instancia de tipo User que se solicita agregar
     */
    public void addUser(User creator, String name, String password, Role role) {
        if (creator.getRole() == Role.ADMIN) {
            users[usersIndex++] = new User(name, password, role);
            System.out.println("\nUsuario añadido exitosamente\n");
            System.out.println("nombre de usuario: " + users[usersIndex - 1].getuserName());
            System.out.println("contraseña: " + users[usersIndex - 1].getPassword());
        } else {
            System.err.println("Su rol es " + creator.getRole() + ". No puede agregar usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita la eliminacion de un
     *                   usuario
     * @param targetUser instancia de tipo User que se solicita eliminar
     */
    public void deleteUser(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMIN) {
            for (int i = 0; i < usersIndex; i++) {

                if (users[i] == targetUser) {
                    // se corre el arreglo hacia la izquierda para ocupar el espacio del eliminado
                    for (int j = i; j < usersIndex - 1; j++) {
                        users[j] = users[j + 1];
                    }

                    // se reduce el indice porque hay un elemento menos y se elimina el dato
                    // duplicado en la posicion actual
                    users[--usersIndex] = null;
                    System.out.println("Usuario eliminado exitosamente");
                    return;
                }
            }
            System.out.println("El usuario no se encuentra registrado en el sistema");
        } else {
            System.out
                    .println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido eliminar usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita asignar un rol a un
     *                   usuario
     * @param targetUser instancia de tipo User a la que se desea cambiar su rol de
     *                   usuario
     * @param role       el rol nuevo que se desea asignar a la instancia targetUser
     */
    public void setRole(User userAsking, User targetUser, Role role) {
        if (userAsking.getRole() == Role.ADMIN) {
            targetUser.setRole(role);
            System.out.println("Rol cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No puede cambiar roles");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita el rol de un usuario
     * @param targetUser instancia de tipo User cuyo rol desea consultarse
     * @return retorna el rol de targetUser si el solicitante es administrador o si
     *         es él mismo. De lo contrario retorna null
     */
    public Role getRole(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMIN || userAsking == targetUser) {
            return targetUser.getRole();
        } else {
            System.out.println("Su rol de usario es " + userAsking.getRole()
                    + ". No tiene permitido consultar el rol de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita asignar un nombre
     * @param targetUser instancia de tipo User cuyo nombre desea cambiarse
     * @param name       instancia de tipo String con una cadena con el nombre a
     *                   asignar a targetUser
     */
    public void setName(User userAsking, User targetUser, String name) {
        if (userAsking.getRole() == Role.ADMIN || userAsking == targetUser) {
            targetUser.setName(name);
            System.out.println("nombre asignado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido modificar nombres de otros usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un nombre
     * @param targetUser instancia de tipo User cuyo nombre desea consultarse
     * @return retorna el nombre de targetUser si el solicitante es administrador o
     *         si es él mismo. De lo contrario retorna null
     */
    public String getName(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMIN || userAsking == targetUser) {
            return targetUser.getName();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permiso para ver el nombre de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking  instancia de tipo User que solicita modificar el password
     * @param oldPassword password actual
     * @param newPassword password que reemplazara al actual
     */
    public void setPassword(User userAsking, User targetUser, String oldPassword, String newPassword) {
        if (userAsking.getRole() == Role.ADMIN) {
            targetUser.setPassword(newPassword);
            System.out.println("contraseña reestablecida");
        } else {
            if (targetUser.getPassword().equals(oldPassword)) {
                targetUser.setPassword(newPassword);
                System.out.println("\ncontraseña reestablecida");
            } else {
                System.out.println("contraseña invalida o su rol no es de administrador");
                System.out.println("la contraseña no fue modificada.");
            }
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un password
     * @param targetUser instancia de tipo User cuyo password desea consultarse
     * @return retorna el password de targetUser si el solicitante es administrador
     *         o si es él mismo. De lo contrario retorna null
     */
    public String getPassword(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMIN || userAsking == targetUser) {
            return targetUser.getPassword();
        } else {
            System.out.println(
                    "Su usuario es " + userAsking.getRole() + ". No puede acceder a las contraseñas de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita modificar el ID
     * @param newUserId  instancia de tipo User que va a recibir la modificacion de
     *                   su ID
     */
    public void setuserId(User userAsking, User targetUser, Integer newUserId) {
        if (userAsking.getRole() == Role.ADMIN) {

            // se verifica que el ID no lo tenga otro usuario
            for (User u : users) {
                if (u != null && u.getuserId().equals(newUserId)) {
                    System.out.println("userName: " + u.getuserName() + ", ya posee este ID.");
                    return;
                }
            }
            targetUser.setUserId(newUserId);
            System.out.println("ID de usuario cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido editar el ID de ningún usuario");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un ID
     * @param targetUser instancia de tipo User cuyo ID desea consultarse
     * @return retorna el ID de targetUser si el solicitante es administrador. De lo
     *         contrario retorna null
     */
    public Integer getuserId(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMIN) {
            return targetUser.getuserId();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar el ID de ningún usuario");
            return null;
        }
    }

    /**
     * 
     * @param userAsking  instancia de tipo User que solicita modificar userName
     * @param newUserName instancia de tipo String con el nombre nuevo de userName
     */
    public void setuserName(User userAsking, User targetUser, String newUserName) {
        if (userAsking.getRole() == Role.ADMIN || userAsking == targetUser) {

            // se verifica si el userName ya lo tiene otro usuario
            for (User u : users) {
                if (u != null && u.getuserName().equals(newUserName)) {
                    System.out.println("El usuario con id: " + u.getuserId() + " , ya posee este nombre de usuario.");
                    return;
                }
            }
            targetUser.setUserName(newUserName);
            System.out.println("nombre de usuario cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido editar el nombre de usuario de otros usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un nombre
     * @param targetUser instancia de tipo User cuyo nombre desea consultarse
     * @return retorna el nombre de targetUser si el solicitante es administrador o
     *         si es él mismo. De lo contrario retorna null
     */
    public String getuserName(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMIN || userAsking == targetUser) {
            return targetUser.getuserName();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar el nombre de usuario de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar la existencia
     *                   de un usuario a partir de su ID
     * @param userId     ID del usuario que desea consultarse si existe en el
     *                   sistema
     * @return retorna el usuario (User) si el solicitante es administrador. De lo
     *         contrario retorna null
     */
    public User findUserById(User userAsking, Integer userId) {
        if (userAsking.getRole() == Role.ADMIN || userAsking.getuserId() == userId) {
            for (int i = 0; i < usersIndex; i++) {
                if (users[i] != null && users[i].getuserId().equals(userId)) {
                    return users[i];
                }
            }
            System.out.println("No se encontró un usuario con el ID especificado.");
            return null;
        } else {
            System.out.println("Su rol del usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar o modificar la información de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar la existencia
     *                   de un usuario a partir de su userName
     * @param userName   nombreUsuario del usuario que se desea saber si está
     *                   registrado en el sistema
     * @return retorna el usuario (User) si el solicitante es administrador. De lo
     *         contrario retorna null
     */
    public User findUserByUserName(User userAsking, String userName) {
        if (userAsking.getRole() == Role.ADMIN || userAsking.getuserName().equals(userName)) {
            for (int i = 0; i < usersIndex; i++) {
                if (users[i] != null && users[i].getuserName().equals(userName)) {
                    return users[i];
                }
            }
            System.out.println("El usuario no se encuentra registrado");
            return null;
        } else {
            System.out.println("Su rol del usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar o modificar la información de otros usuarios");
            return null;
        }
    }

    public void printUserData(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMIN || userAsking == targetUser) {
            String userData = targetUser.userData();
            System.out.println(userData);
        } else {
            System.out.println("Su rol del usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar o modificar la información de otros usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar todos los
     *                   usuarios User[] users del arreglo del sistema
     * @return si userAsking posee rol administrador, entonces retorna el arreglo
     *         User[] users con
     *         todos los usuarios del sistema. De lo contrario retorna un arreglo
     *         que contiene solamente los datos de userAsking
     */
    public static User[] getUsers(User userAsking) {
        if (userAsking.getRole() == Role.ADMIN) {
            return users;
        } else {
            User[] self = new User[1];
            self[0] = userAsking;
            return self;
        }
    }

    public void updateUserHistory(User userToModify, String event) {
        event = event + "\nmarca de tiempo: " + System.currentTimeMillis();
        userToModify.setUserHistory(event);
    }

    public void updateUserHistoryCase15(User userAsking, User userToModify, String event){
        event = event + "\nmarca de tiempo: " + System.currentTimeMillis();
        userToModify.setUserHistory(event);
        updateUserHistory(userAsking,"el usuario actualizó el historial de " + userToModify.getuserName());
        System.out.println("Evento registrado exitosamente");
    }

    public String getUserStory(User userAsking, User targUser) {
        if (userAsking.getRole() == Role.ADMIN) {
            return targUser.getUserHistory();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole()
                    + ". No tiene permitido consultar el historial de los usuarios");
            return null;
        }
    }

}
