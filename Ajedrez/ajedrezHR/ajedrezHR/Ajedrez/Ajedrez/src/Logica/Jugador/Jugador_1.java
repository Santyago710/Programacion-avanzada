package Logica.Jugador;

public class Jugador {
    private String posee; // solo tienen el tipo, si son dueños de blancas o negras
    private int turno;
    private String nombre;
            
    public Jugador(){
        turno = 0; // 0 para turno y 1 para no turno
        nombre = "Player";
    }
    
    public String getPosee() {
        return posee;
    }

    public void setPosee(String posee) {
        this.posee = posee;
    }
    
    public void setTurno(int Turno){
        turno = Turno;
    }
    public int getTurno(){
        return turno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
