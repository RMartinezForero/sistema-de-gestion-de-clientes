package controller_model;

public class UserSystem {
    private static User[] users = new User[100];
    private static int usersIndex = 0;

    /**
     * @param creator instancia de tipo User que solicita la agregacion de un usuario
     * @param newUser instancia de tipo User que se solicita agregar
     */
    public void addUser(User creator, User newUser) {
        if (creator.getRole() == Role.ADMINISTRATOR) {
            //asigna a posicion actual del arreglo e incrementa su indice en 1
            users[usersIndex++] = newUser;
        } else {
            System.err.println("Su rol es " + creator.getRole() + ". No puede agregar usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita la eliminacion de un usuario
     * @param targetUser instancia de tipo User que se solicita eliminar
     */
    public void deleteUser(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            for (int i = 0; i < usersIndex; i++) {

                if (users[i] == targetUser) {

                    //se corre el arreglo hacia la izquierda para ocupar el espacio del eliminado
                    for (int j = i; j < usersIndex - 1; j++) {
                        users[j] = users[j + 1];
                    }

                    //se reduce el indice porque hay un elemento menos y se elimina el dato duplicado en la posicion actual
                    users[--usersIndex] = null;
                    System.out.println("Usuario eliminado exitosamente");
                    return;
                }
            }
            System.out.println("El usuario no se encuentra registrado en el sistema");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido eliminar usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita asignar un rol a un usuario
     * @param targetUser instancia de tipo User a la que se desea cambiar su rol de usuario
     * @param role el rol nuevo que se desea asignar a la instancia targetUser
     */
    public void setRole(User userAsking, User targetUser, Role role) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            targetUser.setRole(role);
            System.out.println("Rol cambiado exitosamente");
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No puede cambiar roles");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita el rol de un usuario
     * @param targetUser instancia de tipo User cuyo rol desea consultarse
     * @return retorna el rol de targetUser si el solicitante es administrador o si es él mismo. De lo contrario retorna null
     */
    public Role getRole(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getRole();
        } else {
            System.out.println("Su rol de usario es " + userAsking.getRole() + ". No tiene permitido consultar el rol de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita asignar un nombre
     * @param targetUser instancia de tipo User cuyo nombre desea cambiarse
     * @param name instancia de tipo String con una cadena con el nombre a asignar a targetUser
     */
    public void setName(User userAsking, User targetUser, String name) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            targetUser.setName(name);
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido modificar nombres de otros usuarios");
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un nombre
     * @param targetUser instancia de tipo User cuyo nombre desea consultarse
     * @return retorna el nombre de targetUser si el solicitante es administrador o si es él mismo. De lo contrario retorna null
     */
    public String getName(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getName();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permiso para ver el nombre de otros usuarios");
            return null;
        }
    }

    /**
     * metodo para usuario de rol administrador o estandard
     * @param userAsking instancia de tipo User que solicita modificar un password 
     * @param targetUser instancia de tipo User cuyo password desea modificarse
     * @param oldPassword cadena con password actual
     * @param newPassword cadena con password que reemplazará al actual
     */
    public void setPassword(User userAsking, User targetUser, String oldPassword, String newPassword) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            //delega a User
            targetUser.setPassword(userAsking, oldPassword, newPassword);
        } else {
            System.out.println("Su usuario es " + userAsking.getRole() + ". No puede modificar la contraseña de otros usuarios");
        }
    }

    /**
     * sobrecarga destinada solo a usuario de rol administrador. Administrador no requiere utilizar el password de ningun usuario para poder modificarlo
     * @param userAsking instancia de tipo User que solicita modificar un password 
     * @param targetUser instancia de tipo User cuyo password desea modificarse
     * @param newPassword cadena con password que reemplazará al actual
     */
    public void setPasswordIfAdmin(User userAsking, User targetUser, String newPassword) {
        //delega a User
        targetUser.setPassword(userAsking, null, newPassword);
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un password 
     * @param targetUser instancia de tipo User cuyo password desea consultarse
     * @return retorna el password de targetUser si el solicitante es administrador o si es él mismo. De lo contrario retorna null
     */
    public String getPassword(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getPassword();
        } else {
            System.out.println("Su usuario es " + userAsking.getRole() + ". No puede acceder a las contraseñas de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita modificar un ID 
     * @param targetUser instancia de tipo User cuyo ID desea modificarse
     * @param newUserId ID que desea asignarse a targetUser
     */
    public void setuserId(User userAsking, User targetUser, Integer newUserId) {
        //delega a User
        targetUser.setuserId(userAsking, newUserId);
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un ID 
     * @param targetUser instancia de tipo User cuyo ID desea consultarse
     * @return retorna el ID de targetUser si el solicitante es administrador. De lo contrario retorna null
     */
    public Integer getuserId(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            return targetUser.getuserId();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido consultar el ID de ningún usuario");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita modificar un nombre 
     * @param targetUser instancia de tipo User cuyo nombre desea modificarse
     * @param newUserName cadena con el nuevo nombre asignado a targetUser
     */
    public void setuserName(User userAsking, User targetUser, String newUserName) {
        //delega a User
        targetUser.setuserName(userAsking, newUserName);
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar un nombre
     * @param targetUser instancia de tipo User cuyo nombre desea consultarse
     * @return retorna el nombre de targetUser si el solicitante es administrador o si es él mismo. De lo contrario retorna null
     */
    public String getuserName(User userAsking, User targetUser) {
        if (userAsking.getRole() == Role.ADMINISTRATOR || userAsking == targetUser) {
            return targetUser.getuserName();
        } else {
            System.out.println("Su rol de usuario es " + userAsking.getRole() + ". No tiene permitido consultar el nombre de usuario de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar la existencia de un usuario a partir de su ID
     * @param userId ID del usuario que desea consultarse si existe en el sistema
     * @return retorna el usuario (User) si el solicitante es administrador. De lo contrario retorna null
     */
    public User findUserById(User userAsking, Integer userId) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            for (int i = 0; i < usersIndex; i++) {
                if (users[i] != null && users[i].getuserId().equals(userId)) {
                    return users[i];
                }
            }
            System.out.println("El usuario no se encuentra registrado");
            return null;
        } else {
            System.out.println("Su rol del usuario es " + userAsking.getRole() + ". No tiene permitido consultar información de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar la existencia de un usuario a partir de su userName
     * @param userName nombreUsuario del usuario que se desea saber si está registrado en el sistema
     * @return retorna el usuario (User) si el solicitante es administrador. De lo contrario retorna null
     */
    public User findUserByUserName(User userAsking, String userName) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            for (int i = 0; i < usersIndex; i++) {
                if (users[i] != null && users[i].getuserName().equals(userName)) {
                    return users[i];
                }
            }
            System.out.println("El usuario no se encuentra registrado");
            return null;
        } else {
            System.out.println("Su rol del usuario es " + userAsking.getRole() + ". No tiene permitido consultar información de otros usuarios");
            return null;
        }
    }

    /**
     * @param userAsking instancia de tipo User que solicita consultar todos los usuarios User[] users del arreglo del sistema
     * @return si userAsking posee rol administrador, entonces retorna el arreglo User[] users con 
     * todos los usuarios del sistema. De lo contrario retorna un arreglo que contiene solamente los datos de userAsking
     */
    public static User[] getUsers(User userAsking) {
        if (userAsking.getRole() == Role.ADMINISTRATOR) {
            return users;
        } else {
            User[] self = new User[1];
            self[0] = userAsking;
            return self;
        }
    }

    //TODO: borrar el metodo imprimir
    public void imprimir(User userAsking) {
        for (User u : users) {
            if (u != null) {
                System.out.println();
                System.out.println("name: " + getName(userAsking, u));
                System.out.println("userId: " + getuserId(userAsking, u));
                System.out.println("userName: " + getuserName(userAsking, u));
                System.out.println("role: " + getRole(userAsking, u));
                System.out.println("password: " + getPassword(userAsking, u));
                System.out.println();
            }
        }
    }
}

