package prototypes2;
import controller_model.Role;
import controller_model.User;
import controller_model.UserSystem;

public class App {
    public static void main(String[] args) {
        //TODO: verificar UserSystem
        //TODO: verificar que en UserSystem no se pueda asignar datos repetidos de user_id o user_name
        //TODO: ver mas adelante con el login la coherencia de retornar password y probar escenarios
        User u1 = new User("anthony", "123", Role.STANDARD);
        User u2 = new User("rafael", "456", Role.ADMINISTRATOR);
        User u3 = new User("control", "999", Role.ADMINISTRATOR);
        UserSystem s1 = new UserSystem();
        s1.addUser(u2, u1);
        s1.addUser(u2, u2);
        //s1.addUser(u2, u3);

        s1.imprimir(u3);
        
        u2.setUSER_ID(u1, 99);
        //System.out.println(u1.getUSER_NAME());

        s1.imprimir(u3);

    }
}
