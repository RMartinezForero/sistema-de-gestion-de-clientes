package controller;
import java.util.Scanner;

import model.User;

public class UserController {

    public void addUser(String name, String password){
        User[] users = User.getUsers();
        users[User.getUserIndex()] = new User(name, password);
        User.increaseUserIndex();
    }

    public User findUserById(Integer userId){
        User[] users = User.getUsers();

        for(int i=0; i<users.length ;i++){
            if(users[i] != null){
                if(users[i].getUSER_ID() == userId){
                    return users[i];
                }
            }
        }

        System.out.println("El usuario no se encuentra registrado");
        return null;
    }

    public User findUserByUserName(String userName){
        User[] users = User.getUsers();

        for(int i=0; i<users.length ;i++){
            if(users[i] != null){
                if(users[i].getUserName().equals(userName)){
                    return users[i];
                }
            }

        }

        System.out.println("El usuario no se encuentra registrado");
        return null;
    }

    public void setName(String userName, String name){
        User user = findUserByUserName(userName);
        if(user != null){
            user.setName(name);
        }
    }

    public void setPassword(){
        Scanner input = new Scanner(System.in);
        System.out.print("nombre de usuario: ");
        String userName = input.nextLine();
        User user = findUserByUserName(userName);

        if(user != null){
            int wrongPasswordCounter = 0;

            do{
                System.out.print("Por favor escriba la contraseña actual: ");
                String oldPassword = input.nextLine();

                if(oldPassword.equals(user.getPassword())){
                    System.out.println();
                    System.out.print("Por favor escriba la contraseña nueva: ");
                    String newPassword = input.nextLine();
                    user.setPassword(newPassword);
                    System.out.println();
                    System.out.println("contraseña cambiada exitosamente\n");
                    input.close();
                    return;
                }else{
                    System.out.println("contraseña invalida\n");
                    wrongPasswordCounter++;
                }
            } while(wrongPasswordCounter < 3);

            System.out.println("ha superado el numero de intentos para ingresar la contraseña\n");
            input.close();
        }else{
            input.close();
        }
    }

    public void removeUser(){
        Scanner input = new Scanner(System.in);
        System.out.print("ingrese el nombre de usuario a eliminar: ");
        String userName = input.nextLine();
        input.close();
        User[] users = User.getUsers();

        for(int i=0; i<users.length ;i++){
            if(users[i] != null && users[i].getUserName().equals(userName)){

                User[] newArray = new User[100];
                for(int j=0; j<i ;j++) {
                    newArray[j] = users[j];
                }
                for(int j=i; j<users.length-1 ;j++) {
                    newArray[j] = users[j+1];
                }

                User.setUsers(newArray);
                User.decreaseUserIndex();
                System.out.println("usuario eliminado exitosamente");
                return;
            }
        }

        System.out.println("el usuario no se encuentra registrado en el sistema");
    }


}
