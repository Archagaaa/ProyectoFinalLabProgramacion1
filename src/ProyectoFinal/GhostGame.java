package ProyectoFinal;

import java.util.Random;

public class GhostGame {
    private Player[] players;
    private int contarjugador;
    private Player playeractual;
    private char[][] tablero;
    private String mododejuego;
    private int dificultad;
    
    public GhostGame(){
        players = new Player[100];
        contarjugador = 0;
        playeractual = null;
        tablero = new char[6][6];
        mododejuego = "aleatorio";
        dificultad = 8; //Modo normal por defecto
        creartablero();
    }
    
    public void registrar (String username, String password) {
        for (Player player : players) { //recorre los elementos del array
            if (player != null && player.getUsername().equals(username)) {
                System.out.println("Este usuario ya existe");
                return;
            }
        }
        if (password.length() < 8) {
            System.out.println("La contrasena debe ser de al menos de 8 caracteres");
            return;
        }
        players[contarjugador++] = new Player(username, password);
        System.out.println("Jugador registrado");
    }
    
    public boolean login(String username, String password) {
        for (Player player : players) {
            if (player != null && player.getUsername().equals(username) && player.verificarPassword(password)) {
                playeractual = player;
                System.out.println("Ha iniciado sesion");
                return true;
            }
        }
        System.out.println("Nombre de usuario o contrasena incorrecta");
        return false;
    }
    
    public void creartablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tablero[i][j] = '_';
            }
        }
    }
    
    public void mostrartablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    
     public void colocarFantasmas(Player jugador, boolean esManual) {
        char bueno = 'B', malo = 'M';
        int cantidad = dificultad / 2;
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            for (char tipo : new char[]{bueno, malo}) {
                if (esManual) {
                    // Modo manual: pedir coordenadas al usuario
                    System.out.println("Jugador " + jugador.getUsername() + ", coloque su fantasma (" + tipo + "):");
                    // Obtener coordenadas válidas de entrada (usar Scanner en Main)
                } else {
                    // Modo aleatorio
                    int fila, col;
                    do {
                        fila = random.nextInt(2);
                        col = random.nextInt(6);
                    } while (tablero[fila][col] != '_');
                    tablero[fila][col] = tipo;
                }
            }
        }
    }
     
    public boolean moverFantasma(int filaOrigen, int colOrigen, int filaDestino, int colDestino, Player jugador) {
        if (filaDestino < 0 || filaDestino >= 6 || colDestino < 0 || colDestino >= 6) {
            System.out.println("Movimiento fuera de rango.");
            return false;
        }
        if (Math.abs(filaDestino - filaOrigen) + Math.abs(colDestino - colOrigen) != 1) {
            System.out.println("Solo puedes mover una casilla.");
            return false;
        }
        if (tablero[filaDestino][colDestino] != '_') {
            System.out.println("La casilla está ocupada.");
            return false;
        }
        char fantasma = tablero[filaOrigen][colOrigen];
        tablero[filaOrigen][colOrigen] = '_';
        tablero[filaDestino][colDestino] = fantasma;
        return true;
    }
    
    public Player getPlayeractual(){
        return playeractual;
    }
    
    public Player[] getPlayers(){
        return players;
    }
    
    public int getContarJugador(){
        return contarjugador; 
    }
    
    public boolean existePlayer(String username) {
    for (int i = 0; i < contarjugador; i++) {
        if (players[i].getUsername().equals(username)) {
            return true;
        }
    }
    return false;
}
    
    public void cambiarDificultad(int niveldedificultad){
        
        switch(niveldedificultad){
            case 1:
                dificultad = 8;
                break;
            case 2:
                dificultad = 4;
                break;
            case 3:
                dificultad = 2;
                break;
        }
    }
    
    public void configurarModo(String nuevomodo){
        if (nuevomodo.equalsIgnoreCase("ALEATORIO") || nuevomodo.equalsIgnoreCase("MANUAL")) {
            mododejuego = nuevomodo;
        }
    }
    
    public String getmododejuego(){
        return mododejuego;
    }
    
    public void eliminarJugador(){
        for (int i = 0; i < contarjugador; i++) {
            if (players[i] == playeractual) {
                
                for (int j = 0; j < contarjugador -1; j++) {
                    players[j]=players[j+1];
                }
                players[contarjugador - 1 ]=null;
                contarjugador --;
                playeractual=null;
                break;
            }
        }
    }
    
}
