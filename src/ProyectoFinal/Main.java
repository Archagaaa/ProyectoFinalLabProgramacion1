package ProyectoFinal;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        GhostGame juego = new GhostGame();
        
        int opcion;
        
        while(true) {
            System.out.println("=====MENU DE INICIO=====");
            System.out.println("1 - Login"+
                               "\n2 - Registrar"+
                               "\n3 - Salir");
            System.out.println("Seleccione una opcion: ");
            opcion = sc.nextInt();
            
            switch (opcion){
                case 1:
                    System.out.print("Usuario: ");
                    String username = sc.next();
                    System.out.print("Contrasena: ");
                    String password = sc.next();
                    if (juego.login(username, password)) {
                        System.out.println("Bienvenido al Juego: "+username);
                        MenuMain(juego);
                    }
                    else{
                        System.out.println("Datos incorrectos");
                    }
                    break;
                case 2:
                    System.out.print("Nuevo Usuario: ");
                    String newUsername = sc.next();
                    System.out.print("Nueva Contrasena: ");
                    String newPassword = sc.next();
                    juego.registrar(newUsername, newPassword);
                    break;
                case 3:
                    System.out.println("Ha salido del juego");
                    break;
                default:
                    System.out.println("Opcion no valida, vuelva a intentar");
            }
        }
    }
    
    public static void MenuMain(GhostGame juego){
        Scanner sc = new Scanner(System.in);
        
        while (true){
            System.out.println("=====MENU PRINCIPAL=====");
            System.out.println("1 - Jugar"+
                               "\n2 - Configuracion"+
                               "\n3 - Reportes"+
                               "\n4 - Mi Perfil"+
                               "\n5 - Cerrar Sesion");
            System.out.println("Seleccione una opcion: ");
            int opcion = sc.nextInt();
            
            switch (opcion){
                case 1:
                    System.out.println("Ingrese el username del 2ndo Jugador: ");
                    String player2 = sc.next();
                    if (!juego.existePlayer(player2)) {
                        System.out.println("Jugador no registrado");
                        break;
                    }
                    juego.creartablero();
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                    
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Ha cerrado sesion");
                    break;
                default:
                    System.out.println("Opcion no valida, vuelva a intentar");
                    break;
            }
        }
    }
}
