package ProyectoFinal;

public class GhostGame {
    private Player[] players = new Player[100];
    private int contarjugador = 0;
    private Player playeractual;
    private char[][] tablero = new char[6][6]; //tablero 6x6
    private String mododejuego = "Aleatorio";
    private int dificultad;
    
    public GhostGame(){
        contarjugador = 0;
        playeractual = null;
        tablero = new char[6][6];
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
            System.out.println("La contrasena debe ser de al menos 8 caracteres");
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
                tablero[i][j] = '.';
            }
        }
    }
    
    public void mostrartablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(tablero[i][j] + " . ");
            }
            System.out.println();
        }
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
}
