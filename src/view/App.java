package view;
import controller.UserController;
//TODO: no permitir a App acceder a User ???
//TODO: encontrar usuario solo mostrar datos y no retornar User?
import model.User;

public class App {
    public static void main(String[] args) {
        UserController u = new UserController();
        u.addUser("ramon","1234567");
        u.addUser("josefino", "7654321");
        u.addUser("josefino3", "7654321");

        u.removeUser();
        User[] us = User.getUsers();

        for(User i: us){
            if(i != null){
                System.out.println(i.getName() + " " + i.getUserName());
            }else{
                System.out.println(i);
            }
            
        }
    }
}
