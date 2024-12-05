package ProyectoFinal;

import java.util.Random;
import java.util.Scanner;

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
    
    public void colocarFantasmas(Player jugador, boolean esManual, Scanner sc) {
        char bueno = 'B', malo = 'M';
        int cantidad = dificultad / 2;
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            for (char tipo : new char[]{bueno, malo}) {
                if (esManual) {
                    System.out.println("Jugador " + jugador.getUsername() + ", coloque su fantasma (" + tipo + ")");
                    while (true) {
                        System.out.print("Fila (0-1): ");
                        int fila = sc.nextInt();
                        System.out.print("Columna (0-5): ");
                        int col = sc.nextInt();
                        if (fila >= 0 && fila < 2 && col >= 0 && col < 6 && tablero[fila][col] == '_') {
                            tablero[fila][col] = tipo;
                            break;
                        } else {
                            System.out.println("Coordenadas inválidas, intente de nuevo.");
                        }
                    }
                } else {
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
     
    public boolean moverFantasma(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
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
    
    public void iniciarJuego(String jugador2Username, Scanner sc) {
        Player jugador1 = playeractual;
        Player jugador2 = null;

        for (Player p : players) {
            if (p != null && p.getUsername().equals(jugador2Username)) {
                jugador2 = p;
                break;
            }
        }

        if (jugador2 == null) {
            System.out.println("Jugador 2 no encontrado.");
            return;
        }

        creartablero();
        boolean esManual = mododejuego.equalsIgnoreCase("manual");
        System.out.println("Colocando fantasmas para " + jugador1.getUsername());
        colocarFantasmas(jugador1, esManual, sc);
        System.out.println("Colocando fantasmas para " + jugador2.getUsername());
        colocarFantasmas(jugador2, esManual, sc);

        boolean juegoTerminado = false;
        Player turnoActual = jugador1;

        while (!juegoTerminado) {
            mostrartablero();
            System.out.println("Turno de " + turnoActual.getUsername());
            System.out.print("Ingrese fila y columna de origen (separadas por espacio): ");
            int filaOrigen = sc.nextInt();
            int colOrigen = sc.nextInt();
            System.out.print("Ingrese fila y columna de destino (separadas por espacio): ");
            int filaDestino = sc.nextInt();
            int colDestino = sc.nextInt();

            if (moverFantasma(filaOrigen, colOrigen, filaDestino, colDestino)) {
                System.out.println("Movimiento exitoso.");
            } else {
                System.out.println("Movimiento inválido, intente de nuevo.");
                continue;
            }

            // Verificar condiciones de victoria
            juegoTerminado = verificarVictoria();
            turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1;
        }

        System.out.println("¡El juego ha terminado!");
    }
     
     public boolean verificarVictoria() {
    int buenosJugador1 = 0, malosJugador1 = 0;
    int buenosJugador2 = 0, malosJugador2 = 0;

    // Contar fantasmas en el tablero
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            char celda = tablero[i][j];
            if (celda == 'B') {
                // Fantasma bueno del jugador 1 (parte superior del tablero)
                if (i < 3) buenosJugador1++;
                else buenosJugador2++;
            } else if (celda == 'M') {
                // Fantasma malo
                if (i < 3) malosJugador1++;
                else malosJugador2++;
            }
        }
    }

    // Condición 1: Todos los fantasmas buenos del oponente capturados
    if (buenosJugador2 == 0) {
        System.out.println("¡" + playeractual.getUsername() + " ha ganado! Todos los fantasmas buenos del oponente fueron capturados.");
        return true;
    } else if (buenosJugador1 == 0) {
        System.out.println("¡El oponente ha ganado! Todos los fantasmas buenos de " + playeractual.getUsername() + " fueron capturados.");
        return true;
    }

    // Condición 2: Todos los fantasmas malos del jugador actual capturados
    if (malosJugador1 == 0) {
        System.out.println("¡El oponente ha ganado! Todos los fantasmas malos de " + playeractual.getUsername() + " fueron capturados.");
        return true;
    } else if (malosJugador2 == 0) {
        System.out.println("¡" + playeractual.getUsername() + " ha ganado! Todos los fantasmas malos del oponente fueron capturados.");
        return true;
    }

    // Condición 3: Fantasma bueno llega a una celda de salida
    for (int j = 0; j < 6; j++) {
        if (tablero[5][j] == 'B') { // Celda de salida para el jugador 1
            System.out.println("¡" + playeractual.getUsername() + " ha ganado! Un fantasma bueno llegó a la salida.");
            return true;
        } else if (tablero[0][j] == 'B') { // Celda de salida para el jugador 2
            System.out.println("¡El oponente ha ganado! Un fantasma bueno llegó a la salida.");
            return true;
        }
    }

    // Si no se cumple ninguna condición, el juego continúa
    return false;
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
        System.out.println("Modo de juego configurado a: " + mododejuego);
        } 
        else {
        System.out.println("Modo inválido. Se mantiene el modo actual: " + mododejuego);
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
