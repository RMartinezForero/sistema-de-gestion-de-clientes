package model;

import java.util.Scanner;

public class User {
    private static User[] users = new User[100];
    private String name;
    private static Integer id_generator = 0;
    private final Integer USER_ID;
    private final String USER_NAME;
    private String password;
    private static Integer userIndex = 0;

    public User(String name, String password) {
        users[userIndex] = this;
        userIndex++;
        this.name = name;
        id_generator++;
        this.USER_ID = id_generator;
        this.USER_NAME = name + USER_ID;
        this.password = password;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    private String getUserName() {
        return USER_NAME;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addUser(String name, String password){
        users[userIndex] = new User(name, password);
        userIndex++;
    }

    public User findUserById(Integer userId){

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

    public void setPassword(){
        Scanner input = new Scanner(System.in);
        Integer passwordCounter = 0;

        do{
            System.out.print("escriba la contraseña actual: ");         
            String oldPassword = input.nextLine();

            if(this.password.equals(oldPassword)){
                System.out.println();
                System.out.print("escriba la nueva contraseña: ");
                this.password = input.nextLine();
                System.out.println("\ncontraseña reestablecida");
                input.close();
                return;
            }else{
                passwordCounter++;
                System.out.println("contraseña invalida\n");
            }
        } while(passwordCounter<3);

        input.close();
        System.out.println("ha superado el numero de intentos permitidos para ingresar su password");
    }

    public void deleteUser(){
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

                this.users = newArray;
                this.userIndex--;
                System.out.println("\nusuario eliminado exitosamente");
                return;
            }
        }

        System.out.println("\nel usuario no se encuentra registrado en el sistema");
    }
    
    //TODO: eliminar getUsers
    public static User[] getUsers() {
        return users;
    }


}
