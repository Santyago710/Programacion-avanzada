package Logica.Fichas;

import Logica.Tablero;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Peon extends Fichas{
    
    private Point posicion;
    private ImageIcon peonB;
    private ImageIcon peonN;
    private String TIPO;
    
    public Peon(){
       peonB = new ImageIcon(getClass().getResource("/Persistencia/Fichas/BlancoPeon.png"));
       peonN = new ImageIcon(getClass().getResource("/Persistencia/Fichas/NegroPeon.png"));
    }
    
    @Override
    public ImageIcon getImageB(){
        if(TIPO.equals("NEGRAS")){
            return peonN;
        }
        return peonB;
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
        int x = posicion.x;
        int y = posicion.y;
        
        
        if(RESTRICCION(x-1,y)){
            
            if(y-1>0 && T.getCasillas()[x-1][y-1] instanceof Fichas) // muerte uno
                p.add(new Point(x-1,y-1));
        
            if(y+1<8 && T.getCasillas()[x-1][y+1] instanceof Fichas) //muerte dos
                p.add(new Point(x-1,y+1));
        
            if(T.getCasillas()[x-1][y] == null){
                Point mov = new Point(x-1,y); // Movimiento
                p.add(mov); 
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
        return new Peon();
    }
    
}
