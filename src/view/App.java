package view;

import controller_model.*;

public class App {
    public static void main(String[] args) {
        //TODO: modificar los paquetes e importaciones cuando ya no esten en prototypes
        //TODO: terminar de comentar el codigo de UserSystem
        User u1 = new User("anthony", "123", Role.STANDARD);
        User u2 = new User("rafael", "456", Role.ADMINISTRATOR);
        User u3 = new User("control", "999", Role.ADMINISTRATOR);
        UserSystem s1 = new UserSystem();
        
        s1.addUser(u2, u1);
        s1.addUser(u2, u2);
        
        //s1.setuserName(u2, u2, "anthony90");
        System.out.println(s1.getUsers(u1)[0].getPassword());

        s1.imprimir(u3);

    }
}
