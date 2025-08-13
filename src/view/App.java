package view;
import model.User;

public class App {
    public static void main(String[] args) {
        User u1 = new User("carlo", "1234567");
        User u2 = new User("ramiro", "7654321");
        u2.addUser("josefino", "micontrasena");

        u1.deleteUser();
        
        for(User u: User.getUsers()){
            if(u != null){
                System.out.println(u.getUSER_ID());
            }
        }
    }
}
