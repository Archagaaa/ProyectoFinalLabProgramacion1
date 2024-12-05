package ProyectoFinal;

public class Player {
    private String username;
    private String password;
    private int puntos;
    private String[] gamelogs; //Guardas las partidas
    private int contarlogs;
    
    public Player(String username, String password){
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
    
    public String[] getGamelogs(){
        return gamelogs;
    }
    
    public void addGamelogs(String log){
        gamelogs[contarlogs] = log;
        contarlogs = (contarlogs + 1) % 10;
    }
    
    public void sumarpuntos(int puntos){
        this.puntos += puntos;
    }
    
    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }
    
    public void setPassword(String newpassword){
        this.password = newpassword;
    }
}
