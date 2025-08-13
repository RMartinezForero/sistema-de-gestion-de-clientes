package model;
import java.util.Scanner;

public class User {
    private static User[] users = new User[100];
    private String name;
    private String password;
    private Role role;
    private static Integer id_generator = 0;
    private final Integer USER_ID;
    private final String USER_NAME;
    private static Integer userIndex = 0;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.USER_ID = id_generator;
        User.id_generator++;
        this.USER_NAME = name + USER_ID;
        users[userIndex] = this;
        userIndex++;
    }

    //solo admin
    public void addUser(String name, String password, Role role){
        if(this.role == Role.ADMINISTRATOR){
            users[userIndex] = new User(name,password,role);
            userIndex++;
        }else{
            System.out.println("Su rol de usuario es " + getRole() + ". No tiene permitido añadir usuarios");
        }
    }

    private Role getRole() {
        return role;
    }

    //TODO: cambiar getUSER_ID a privado
    public Integer getUSER_ID() {
        return USER_ID;
    }

    private String getUserName() {
        return USER_NAME;
    }

    public void setName(String name){
            this.name = name; 
    }

    //solo admin
    public User findUserById(Integer userId){
        if(this.role == Role.ADMINISTRATOR){

            for(int i=0; i<users.length ;i++){
                if(users[i] != null){
                    if(users[i].getUSER_ID() == userId){
                        return users[i];
                    }
                }
            }

            System.out.println("El usuario no se encuentra registrado");
            return null;
        }else{
            System.out.println("Su rol del usuario es " + getRole() + ". No tiene permitido consultar informacion de otros usuarios");
            return null;
        }
    }
    
    //solo admin
    public User findUserByUserName(String userName){

        if(this.role == Role.ADMINISTRATOR){
            for(int i=0; i<users.length ;i++){
                if(users[i] != null){
                    if(users[i].getUserName().equals(userName)){
                        return users[i];
                    }
                }
            }

        System.out.println("El usuario no se encuentra registrado");
        return null;

        }else{
            System.out.println("Su rol de usuario es " + getRole() + 
            ". No tiene permitido consultar información de otros usuarios");
            return null;
        }
    }

    public void setPassword() {
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
    
    //solo admin
    public void deleteUser(){
        
        if(this.role == Role.ADMINISTRATOR){
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
        }else{
            System.out.println("Su rol del usuario es " + getRole() + ". No tiene permitido eliminar usuarios");
        }
    }

    //TODO: eliminar getUsers
    public static User[] getUsers() {
        
        return users;
    }

    public String getName() {
        return name;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

}
