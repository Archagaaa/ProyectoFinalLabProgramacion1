package ProyectoFinal;

public class Player {
    private String username;
    private String password;
    private int puntos;
    //Guardar el historial las partidas
    private String[] gamelogs;
    private int contarlogs;
    
    public Player(String username, String password){
        
        if (username == null || username.isEmpty()) {
        throw new IllegalArgumentException("El nombre de usuario no puede estar vacio");
        }
        if (password == null || password.length() < 8) {
        throw new IllegalArgumentException("La contrasena debe tener al menos 8 caracteres.");
        }
        
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.gamelogs = new String[10];
        this.contarlogs = 0;    
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public void sumarpuntos(int puntos){
        this.puntos += puntos;
    }
    
    public String[] getGamelogs(){
        return gamelogs;
    }
    
    public void addGamelogs(String log){
        gamelogs[contarlogs] = log;
        contarlogs = (contarlogs + 1) % 10; //al ser 10 logs reinicia a 0 ya que el limite es 10
    }
    
    public void reiniciarLogs() {
        for (int i = 0; i < gamelogs.length; i++) {
            gamelogs[i] = null;
        }
        contarlogs = 0;
    }
    
    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }
    
    public void setPassword(String newpassword){
        if (newpassword == null || newpassword.length() < 8) {
        System.out.println("La nueva contrasena debe tener al menos 8 caracteres");
        return;
    }
        this.password = newpassword;
    }
    
    public void mostrarDatos() {
        System.out.println("Usuario: " + username);
        System.out.println("Puntos: " + puntos);
        System.out.println("Historial de juegos: ");
        for (String log : gamelogs) {
            if (log != null) {
                System.out.println("- " + log);
            }
        }
    }
    
    // comparar dos jugadores por puntos
    public int compararPorPuntos(Player otro) {
        return Integer.compare(this.puntos, otro.puntos);
    }
}
