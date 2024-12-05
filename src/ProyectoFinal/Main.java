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
                        MenuPrincipal(juego);
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
    
    public static void MenuPrincipal(GhostGame juego){
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
                    
                    //Iniciar el tablero
                    juego.creartablero();
                    juego.mostrartablero();
                    
                    
                    break;
                case 2:
                    configuracion(juego);
                    break;
                case 3:
                    reportes(juego);
                    break;
                    
                case 4:
                    perfil(juego);
                    break;
                case 5:
                    System.out.println("Ha cerrado sesion");
                    return;
                default:
                    System.out.println("Opcion no valida, vuelva a intentar");
                    break;
            }
        }
    }
    
    public static void configuracion(GhostGame juego){
        Scanner sc = new Scanner(System.in);
        
        while (true){
            System.out.println("=====CONFIGURACION=====");
            System.out.println("1 - Dificultad"+
                                "\n2 - Modo de juego"+
                                "\n3 - Regresar");
            System.out.println("Seleccione una opcion: ");
            int opcion = sc.nextInt();
            
            switch (opcion){
                case 1:
                    System.out.println("=====DIFICULTAD=====");
                    System.out.println("Selecione la dificultad del juego:"+
                                        "\n 1. Normal - (8 fantasmas por jugador)"+
                                        "\n 2. Expert - (4 fantasmas por jugador)"+
                                        "\n 3. Genius - (2 fantasmas y 4 trampas)");
                    int dificultad = sc.nextInt();
                    juego.cambiarDificultad(dificultad);
                    System.out.println("Ha cambiado la dificultad" );
                    break;
                    
                case 2:
                    System.out.println("====MODO DE JUEGO=====");
                    System.out.println("Selecicone el modo de juego:"+
                                        "\n1 - Aleatorio"+
                                        "\n2 - Manual");
                    int modo = sc.nextInt();
                     if (modo == 1) {
                        juego.configurarModo("aleatorio");
                    }
                    else if(modo == 2){
                        juego.configurarModo("manual");
                    }
                    else{
                        System.out.println("Opcion no valida");
                    }
                    System.out.println("Modo Actualizado");
                    break;
                    
                case 3:
                    return;
                default:
                    System.out.println("Opcion no valida, vuelva a intentar");
                    break;
            }
        }
    }
    
    public static void reportes(GhostGame juego){
        Scanner lea=new Scanner(System.in);
        
        while(true){
            System.out.println("---REPORTES---"
            +"\n 1. Ultimos 10 juegos"
            +"\n 2. Ranking de Jugadores"
            +"\n 3. Regresar al Menu Principal"
            +"\n Ingrese una opcion");
            int opcion=lea.nextInt();
            
            switch(opcion){
                case 1:
                    String[] logs=juego.getPlayeractual().getGamelogs();
                    System.out.println("Ultimos 10 juegos");
                    for (String log : logs) {
                        if (log !=null) {
                            System.out.println("- "+log);
                        }
                    }
                    break;
                case 2:
                    Player[]players=juego.getPlayers();
                    System.out.println("Ranking de Jugadores");
                    for (int i = 0; i < juego.getContarJugador(); i++) {
                        Player player=players[i];
                        System.out.println(player.getUsername()+" - "+player.getPuntos()+" puntos");
                    }
                    break;
                    
                case 3:
                    return;
                    
                default:
                    System.out.println("OPCION INVALIDA");
            }
        }
    }
    
    public static void perfil(GhostGame juego){
        Scanner lea=new Scanner(System.in);
        Player actualUser=juego.getPlayeractual();
        
        while(true){
            System.out.println("--MI PERFIL--"
                                        +"\n 1. Ver Datos"
                                        +"\n 2. Cambiar Password"
                                        +"\n 3. Eliminar Cuenta"
                                        +"\n 4. Salir"
                                        +"\n Ingrese una opcion");
            int opcion=lea.nextInt();
            
            switch(opcion){
                case 1:
                    System.out.println("Perfil: "+actualUser.getUsername()
                                               +"\n Puntos: "+actualUser.getPuntos());
                    break;
                case 2:
                    System.out.println("Ingrese su nueva password: ");
                    String nuevoPassword=lea.next();
                    actualUser.setPassword(nuevoPassword);
                    System.out.println("Password Actualizada!");
                    break;
                    
                case 3:
                    System.out.println("Seguro que desea eliminar su cuenta? (s | n)");
                    char confirmar=lea.next().toLowerCase().charAt(0);
                    if (confirmar == 's') {
                        juego.eliminarJugador();
                        System.out.println("CUENTA ELIMINADA");
                        return;
                    }else{
                          System.out.println("OPERACON CANCELADA");  
                    }
                    break;
                case 4:
                    return ;
                default:
                    System.out.println("OPCION INVALIDA");
            }
        }
    }
}
