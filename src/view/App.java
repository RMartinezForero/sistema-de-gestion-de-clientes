package view;

import java.util.Scanner;

import controller_model.*;

public class App {
    public static void main(String[] args) {
        //TODO: cerrar input?
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
                System.out.println("15. cerrar sesión");
                System.out.println();
                System.out.print("Escriba una opción: ");

                try {
                    answer = Integer.parseInt(input.nextLine());
                    System.out.println();
                } catch (Exception e) {
                    System.err.println("Respuesta invalida. Intentelo nuevamente");
                    continue;
                }

                if (answer < 1 || answer > 15) {
                    System.err.println("Respuesta invalida. Intentelo nuevamente");
                } else {

                    switch (answer) {
                        case 1:
                            if (isAdmin(user,s1)) {
                                s1.addUser(user, askName(input), askPassword(input), askRole(input));
                            }
                            break;
                        case 2:
                            // si es el "superadministrador" , no puede borrarse
                            if (isAdmin(user,s1)) {
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
                            }
                            break;
                        case 3:
                            //TODO: voy aqui
                            break;                            
                        case 15:
                            exit = true;
                            System.out.println("\n------------------------------------------------------");
                            System.out.println("\n-------------------sesión terminada-----------------\n");
                            System.out.println("------------------------------------------------------\n");
                            break;
                        default:
                            break;
                    }

                }

            } while (exit == false);

        } while (true);

        // input.close();

    }

    public static boolean isAdmin(User user, UserSystem system) {
        if(system.getRole(user,user) != Role.ADMIN){
            System.out.println("su rol de usuario no tiene permitida esta accion");
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
        System.out.print("nombre de usuario: ");
        String userName = input.nextLine();
        return userName;
    }
}
