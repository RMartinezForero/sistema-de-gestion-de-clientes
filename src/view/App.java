package view;
import model.Role;
import model.User;

public class App {
    public static void main(String[] args) {
        User u1 = new User("anthony", "123", Role.ADMINISTRATOR);
        u1.addUser("ra", "123", Role.STANDARD);
        u1.setName("josefo");
        System.out.println(u1.getName());
    }
}
