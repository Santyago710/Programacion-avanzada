package Logica.Fichas;

import Logica.Tablero;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Fichas{
       
    public String getTipo(){return null;} // tienen un tipo , B/N
    public Point getPos(){return null;} // Tienen una posicion de tipo punto
    public ImageIcon getImageB(){return null;}    // tienen una imagen que lo referencian
    public void setTipo(String tipo){}; // podemos cambiar el tipo/ B/N
    public void setPos(Point a){};// Podemos cambiar su posicion
    
    public ArrayList<Point> mover(Tablero T){return null;} // Inidicamos por donde se puede mover
    public String Enemigo(){
        if(this.getTipo().equals("BLANCAS")){
            return "NEGRAS";
        }
        else{
            return "BLANCAS";
        }} // Todos tienen un enemigos
    public Fichas Cast(){return null;} // Todos tinen un tipo, peon = peon
    
    public boolean RESTRICCION(int fila, int columna){ // y estan restrigidos
        return (fila>=0 && fila<8) && (columna>=0 && columna<8); 
    }
    
    
    
    
}
