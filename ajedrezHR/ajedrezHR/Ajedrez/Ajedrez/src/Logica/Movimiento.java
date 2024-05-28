package Logica;

import java.awt.Point;



public class Movimiento{
    private Point inicio;
    private Point fin; 
    
    
    public Movimiento(){
        inicio = new Point();
        fin = new Point();
        
    }
    
    public Point getInicio() {
        return inicio;
    }

    public Point getFin() {
        return fin;
    }

    public void setInicio(Point inicio) {
        this.inicio = inicio;
    }

    public void setFin(Point fin) {
        if(inicio==null){ this.fin =null; return;}
        this.fin = fin;
    }

}