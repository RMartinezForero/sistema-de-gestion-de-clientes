package view;

import java.util.Scanner;

import controller_model.*;

public class App {
    public static void main(String[] args) {
        // TODO: cerrar input?
        // TODO: borrar prototypes
        Scanner input = new Scanner(System.in);
        UserSystem s1 = new UserSystem();
        User user;

        do {
            // LOG IN-------------------------------------------
            do {

                System.out.println(":::::::LOG IN::::::::::\n");
                System.out.println("por favor digite la información solicitada para iniciar sesión en el sistema:");
                System.out.print("\nnombre de usuario: ");
                String userName = input.nextLine();
                System.out.print("contraseña: ");
                String password = input.nextLine();
                user = s1.logIn(userName, password);

                if (user == null) {
                    System.err.println("\nError de inicio de sesión. Su nombre de usuario o contraseña es incorrecto.");
                }
            } while (user == null);
            System.out.println("\n:::::::::::inicio de sesión exitoso::::::::::::::");

            // MENU USUARIO UNA VEZ INICIA SESIÓN-------------------
            Integer answer = 0;
            Boolean exit = false;
            do {
                System.out.println("\n------------------------------------");
                System.out.println("----------------MENU----------------");
                System.out.println("------------------------------------\n");
                System.out.println("1. añadir usuario");
                System.out.println("2. eliminar usuario");
                System.out.println("3. asignar rol");
                System.out.println("4. consultar rol");
                System.out.println("5. asignar nombre completo");
                System.out.println("6. consultar nombre completo");
                System.out.println("7. asignar password");
                System.out.println("8. consultar password");
                System.out.println("9. asignar ID de usuario");
                System.out.println("10. consultar ID de usuario");
                System.out.println("11. asignar nombre de usuario");
                System.out.println("12. consultar nombre de usuario");
                System.out.println("13. buscar usuario por ID");
                System.out.println("14. buscar usuario por nombre de usuario");
                System.out.println("15. registrar evento en el historial de un usuario");
                System.out.println("16. consultar historial de usuario");
                System.out.println("17. cerrar sesión");
                System.out.println();
                System.out.print("Escriba una opción: ");

                try {
                    answer = Integer.parseInt(input.nextLine());
                    System.out.println();
                } catch (Exception e) {
                    System.err.println("Respuesta invalida. Intentelo nuevamente");
                    continue;
                }

                if (answer < 1 || answer > 17) {
                    System.err.println("Respuesta invalida. Intentelo nuevamente");
                } else {
                    User userToConsult;

                    switch (answer) {
                        case 1:
                            if (isAdmin(user, s1)) {
                                s1.addUser(user, askName(input), askPassword(input), askRole(input));
                            } else {
                                System.out.println("su rol de usuario no tiene permitida esta acción");
                            }
                            break;
                        case 2:
                            // si es el "superadministrador" , no puede borrarse
                            if (isAdmin(user, s1)) {
                                User userToDelete = s1.findUserByUserName(user, askUserName(input));

                                if (userToDelete == s1.getUsers(user)[0]) {
                                    System.out.println("No puede eliminarse al administrador principal");
                                    break;
                                }
                                // si se elimino a si mismo, cerrar sesion
                                if (userToDelete == user) {
                                    s1.deleteUser(user, userToDelete);
                                    System.out.println("\n------------------------------------------------------");
                                    System.out.println("\n-------------------sesión terminada-----------------\n");
                                    System.out.println("------------------------------------------------------\n");
                                    exit = true;
                                } else {
                                    s1.deleteUser(user, userToDelete);
                                }
                            } else {
                                System.out.println("su rol de usuario no tiene permitida esta accion");
                            }
                            break;
                        case 3:
                            if (isAdmin(user, s1)) {
                                User userToModify = s1.findUserByUserName(user, askUserName(input));

                                if (userToModify == s1.getUsers(user)[0]) {
                                    System.out.println("No puede cambiarse el rol del administrador principal");
                                    break;
                                } else {
                                    s1.setRole(user, userToModify, askRole(input));
                                }
                            } else {
                                System.out.println("su rol de usuario no tiene permitida esta acción");
                            }
                            break;
                        case 4:
                            userToConsult = s1.findUserByUserName(user, askUserName(input));
                            if (userToConsult == null) {
                                break;
                            }

                            Role role = s1.getRole(user, userToConsult);
                            System.out.println("el rol del usuario es: " + role);
                            break;
                        case 5:
                            System.out.print("nombre de usuario del usuario a quien desea asignar nombre completo: ");
                            String userName = input.nextLine();
                            User targetUser = s1.findUserByUserName(user, userName);

                            if (targetUser == null) {
                                break;
                            }

                            s1.setName(user, targetUser, askName(input));
                            break;
                        case 6:
                            userToConsult = s1.findUserByUserName(user, askUserName(input));
                            if (userToConsult == null) {
                                break;
                            }

                            String name = s1.getName(user, userToConsult);
                            System.out.println("el nombre completo del usuario es: " + name);
                            break;
                        case 7:
                            userToConsult = s1.findUserByUserName(user, askUserName(input));
                            if (userToConsult == null) {
                                break;
                            }

                            if (isAdmin(user, s1)) {
                                System.out.print("password nuevo: ");
                                String newPassword = input.nextLine();
                                s1.setPassword(user, userToConsult, null, newPassword);
                            } else {
                                System.out.print("password actual: ");
                                String oldPassword = input.nextLine();
                                System.out.print("password nuevo: ");
                                String newPassword = input.nextLine();
                                s1.setPassword(user, userToConsult, oldPassword, newPassword);
                            }
                            break;
                        case 8:
                            userToConsult = s1.findUserByUserName(user, askUserName(input));

                            if (userToConsult == null) {
                                break;
                            }

                            String password = s1.getPassword(user, userToConsult);
                            System.out.println("la contraseña del usuario es: " + password);
                            break;
                        case 9:
                            if (isAdmin(user, s1)) {
                                userToConsult = s1.findUserByUserName(user, askUserName(input));

                                if (userToConsult == null) {
                                    break;
                                }

                                System.out.print("nuevo ID de usuario: ");
                                Integer newUserId = Integer.parseInt(input.nextLine());
                                s1.setuserId(user, userToConsult, newUserId);
                            } else {
                                System.out.println("Su rol de usuario no tiene permitida esta acción");
                            }
                            break;
                        case 10:
                            if (isAdmin(user, s1)) {
                                userToConsult = s1.findUserByUserName(user, askUserName(input));

                                if (userToConsult == null) {
                                    break;
                                }

                                Integer userId = s1.getuserId(user, userToConsult);
                                System.out.print("el ID del usuario es: " + userId);
                            } else {
                                System.out.println("Su rol de usuario no tiene permitida esta acción");
                            }
                            break;
                        case 11:
                            userToConsult = s1.findUserByUserName(user, askUserName(input));

                            if (userToConsult == null) {
                                break;
                            }

                            System.out.print("nuevo nombre de usuario: ");
                            String newUserName = input.nextLine();
                            s1.setuserName(user, userToConsult, newUserName);
                            break;
                        case 12:
                            userToConsult = s1.findUserByUserName(user, askUserName(input));

                            if (userToConsult == null) {
                                break;
                            }

                            String userNameConsult = s1.getuserName(user, userToConsult);
                            System.out.print("el nombre de usuario del usuario solicitado es: " + userNameConsult);
                            break;
                        case 13:
                            System.out.print("ID del usuario a consultar: ");
                            Integer userId = Integer.parseInt(input.nextLine());
                            userToConsult = s1.findUserById(user, userId);

                            if (userToConsult == null) {
                                break;
                            }

                            s1.printUserData(user, userToConsult);
                            break;
                        case 14:
                            System.out.print("nombre de usuario del usuario a consultar: ");
                            String userNameToConsult = input.nextLine();
                            userToConsult = s1.findUserByUserName(user, userNameToConsult);

                            if (userToConsult == null) {
                                break;
                            }

                            s1.printUserData(user, userToConsult);
                            break;

                        case 15:
                            if (isAdmin(user, s1)) {
                                userToConsult = s1.findUserByUserName(user, askUserName(input));

                                if (userToConsult == null) {
                                    break;
                                }

                                System.out.print("descripción del evento realizado por el usuario: ");
                                String event = input.nextLine();
                                s1.updateUserHistory(userToConsult, event);
                            } else {
                                System.out.println(
                                        "Su rol de usuario no tiene permitido modificar historiales de usuario");
                            }
                            break;
                        case 16:
                            if (isAdmin(user, s1)) {
                                userToConsult = s1.findUserByUserName(user, askUserName(input));

                                if (userToConsult == null) {
                                    break;
                                }

                                System.out.println("\n:::::Historial de usuario:::::");
                                System.out.println(s1.getUserStory(user, userToConsult));
                            } else {
                                System.out.println(
                                        "Su rol de usuario no tiene permitido consultar historiales de usuario");
                            }
                            break;
                        case 17:
                            exit = true;
                            System.out.println("\n------------------------------------------------------");
                            System.out.println("\n-------------------sesión terminada-----------------\n");
                            System.out.println("------------------------------------------------------\n");
                            break;
                        default:
                            break;
                    }

                    pauseGenerator(input);
                }

            } while (exit == false);

        } while (true);

        // input.close();

    }

    public static boolean isAdmin(User user, UserSystem system) {
        if (system.getRole(user, user) != Role.ADMIN) {
            return false;
        }

        return true;
    }

    public static String askName(Scanner input) {
        System.out.print("nombre: ");
        String name = input.nextLine();
        return name;
    }

    public static String askPassword(Scanner input) {
        System.out.print("password: ");
        String password = input.nextLine();
        return password;
    }

    public static Role askRole(Scanner input) {
        System.out.print("rol (escribir ADMIN o STANDARD): ");
        Role role = Role.valueOf(input.nextLine().toUpperCase());
        return role;
    }

    public static String askUserName(Scanner input) {
        System.out.print("nombre de usuario del usuario objetivo: ");
        String userName = input.nextLine();
        return userName;
    }

    public static void pauseGenerator(Scanner input) {
        System.out.println("\nPresione ENTER para continuar: ");
        String string = input.nextLine();
    }

}
