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
                    username = sc.next();
                    System.out.print("Nueva Contrasena: ");
                    password = sc.next();
                    juego.registrar(username, password);
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
                    juego.iniciarJuego(player2, sc);
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
                    System.out.println("1 - Normal - (8 fantasmas por jugador)"+
                                        "\n2 - Expert - (4 fantasmas por jugador)"+
                                        "\n3 - Genius - (2 fantasmas y 4 trampas)");
                    System.out.println("Selecione la dificultad del juego: ");
                    int dificultad = sc.nextInt();
                    juego.cambiarDificultad(dificultad);
                    System.out.println("Ha cambiado la dificultad" );
                    break;
                case 2:
                    System.out.println("====MODO DE JUEGO=====");
                    System.out.println("1 - Aleatorio"+
                                       "\n2 - Manual");
                    System.out.println("Seleccione un modoo de juego: ");
                    int modo = sc.nextInt();
                     if (modo == 1) {
                        juego.configurarModo("ALEATORIO");
                    }
                    else if(modo == 2){
                        juego.configurarModo("MANUAL");
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
        Scanner sc = new Scanner(System.in);
        
        while (true){
            System.out.println("=====REPORTES====="+
                                "\n1 - Ultimos 10 juegos"+
                                "\n2 - Ranking de Jugadores"+
                                "\n3 - Regresar al Menu Principal");
            System.out.println("Seleccione una opcion: ");
            int opcion = sc.nextInt();
            
            switch (opcion){
                case 1:
                    String[] gamelogs = juego.getPlayeractual().getGamelogs();
                    System.out.println("Registro de 10 juegos");
                    for (String log : gamelogs) {
                        if (log != null) {
                            System.out.println("- "+log);
                        }
                    }
                    break;
                case 2:
                    Player[]players = juego.getPlayers();
                    System.out.println("Ranking de Jugadores");
                    for (int i = 0; i < juego.getContarJugador(); i++) {
                        Player player = players[i];
                        System.out.println(player.getUsername()+" - "+player.getPuntos()+" puntos");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opcion invalida, vuelva a intentar");
            }
        }
    }
    
    public static void perfil(GhostGame juego){
        Scanner sc = new Scanner(System.in);
        Player playeractual =juego.getPlayeractual();
        
        while(true){
            System.out.println("=====PERFIL====="+
                               "\n1 - Ver Datos"+
                               "\n2 - Cambiar Password"+
                               "\n3 - Eliminar Cuenta"+
                               "\n4 - Regresar");
            System.out.println("Seleccione una opcion: ");
            int opcion = sc.nextInt();
            
            switch(opcion){
                case 1:
                    System.out.println("Perfil: "+playeractual.getUsername()+
                                       "\nPuntos: "+playeractual.getPuntos());
                    break;
                case 2:
                    System.out.println("Ingrese su nueva contrasena: ");
                    String nuevoPassword = sc.next();
                    playeractual.setPassword(nuevoPassword);
                    System.out.println("Contrasena Actualizada!");
                    break;
                case 3:
                    System.out.println("Desea eliminar su cuenta?");
                    char confirmar = sc.next().toLowerCase().charAt(0);
                    if (confirmar == 's') {
                        juego.eliminarJugador();
                        System.out.println("Cuenta elminada correctamente");
                        return;
                    }
                    else {
                        System.out.println("Ha cancelado el proceso");  
                    }
                    break;
                case 4:
                    return ;
                default:
                    System.out.println("Opcion invalida, vuelva a intentar");
            }
        }
    }
}
