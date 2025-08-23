package view;

import java.util.Scanner;

import controller_model.*;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UserSystem s1 = new UserSystem();
        User user;

        System.out.println(":::::::LOG IN::::::::::\n");
        do {
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

        Integer answer = 0;
        do {
            System.out.println("\n----------------MENU------------------");
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
            System.out.println("15. salir");
            System.out.println();
            System.out.print("Escriba una opción: ");
            try{
                answer = Integer.parseInt(input.nextLine());
            } catch(Exception e){
                System.err.println("Respuesta invalida. Intentelo nuevamente");
                continue;
            }

            if(answer < 1 || answer > 15){
                System.err.println("Respuesta invalida. Intentelo nuevamente");
            }

        } while (answer < 1 || answer > 15);

        //TODO: voy aqui, lo de arriba parece estar bien. Estoy haciendo el sistema de login
        switch (answer) {
            case 1:
                if (user.getRole() != Role.ADMINISTRATOR) {
                    System.out.println("su rol de usuario no tiene permitido agregar usuarios al sistema");
                }

                System.out.print("nombre completo del usuario a agregar: ");
                String name = input.nextLine();
                System.out.print("password del usuario a agregar:");
                String targetUserpassword = input.nextLine();
                System.out.print("seleccione el rol del usuario a agregar: ");
                System.out.println("1. Administrador");
                System.out.println("2. Standard");
                int roleAnswer = Integer.parseInt(input.nextLine());
                Role newUserRole;
                if (roleAnswer == 1) {
                    newUserRole = Role.ADMINISTRATOR;
                } else {
                    newUserRole = Role.STANDARD;
                }
                // s1.addUser(user, userName, targetUserpassword, newUserRole);
                break;
            case 2:
                break;
            default:
                break;
        }
        input.close();

    }
}
