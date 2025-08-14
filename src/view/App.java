package view;
import controller.UserSystem;
import model.Role;
import model.User;

public class App {
    public static void main(String[] args) {
        //TODO: verificar funcionamiento hasta parte 2 del ejercicio
        //TODO: verificar combinaciones con setUSER_ID
        //TODO: ver mas adelante con el login la coherencia de retornar password y probar escenarios
        User u1 = new User("anthony", "123", Role.STANDARD);
        User u2 = new User("rafael", "456", Role.ADMINISTRATOR);
        UserSystem s1 = new UserSystem();
        s1.addUser(u2, u1);
        s1.addUser(u2, u2);

        System.out.println(u1.getUSER_ID());
        System.out.println();
        u1.setPassword(u1, null, null);
        System.out.println();

        //s1.imprimir(u2);

    }
}
