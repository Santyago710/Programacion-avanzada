package Logica.Fichas;

import Logica.Tablero;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Rey extends Fichas{

    private Point posicion;
    private final ImageIcon reyB;
    private final ImageIcon reyN;
    private String TIPO;
    
    public Rey(){
        reyB = new ImageIcon(getClass().getResource("/Persistencia/Fichas/BlancoRey.png"));
        reyN = new ImageIcon(getClass().getResource("/Persistencia/Fichas/NegroRey.png"));

    }
    
    @Override
    public ImageIcon getImageB(){
        if(TIPO.equals("NEGRAS")){
            return reyN;
        }
        return reyB;
    }
    
    public ImageIcon getImageN(){
        return reyN;
    }
    
    @Override
    public Point getPos(){
        return posicion;
    }
    
    @Override
    public void setPos(Point newPos){
        posicion = newPos;
    }
    
    @Override
    public String getTipo() {
    return TIPO;
    }

    @Override
     public ArrayList<Point> mover(Tablero T) {
        ArrayList<Point> p = new ArrayList();
        
        int[] dx = {-1,0, 0,1, 1 };
        int[] dy = { 0,-1, 1, 0, 1 };
        
        int x = posicion.x;
        int y = posicion.y;
        for(int i = 0; i < 4 ; i++){
            int Xnew = x+dx[i];
            int Ynew = y+dy[i];
            if(RESTRICCION(Ynew,Xnew)){ // aqui miramos si al mover en el tablero puede moverse o no
            p.add(new Point(Xnew,Ynew));
            }
        }
    return p;
    }
    
    @Override
    public void setTipo(String tipo){
    TIPO = tipo;
    }

    
     @Override
    public Fichas Cast()
    {
        return new Rey();
    }
    
}
