package Logica.Fichas;

import Logica.Tablero;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Obispo extends Fichas{
    
        private Point posicion;
        private ImageIcon obispoB;
        private ImageIcon obispoN;
        private String TIPO;
    
    public Obispo(){
        obispoB = new ImageIcon(getClass().getResource("/Persistencia/Fichas/BlancoObispo.png"));
        obispoN = new ImageIcon(getClass().getResource("/Persistencia/Fichas/NegroObispo.png"));

    }
    
    public ImageIcon getImageB(){
        if(TIPO.equals("NEGRAS")){
            return obispoN;
        }
        return obispoB;
    }
    
    public ImageIcon getImageN(){
        return obispoN;
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
        int[]dx = {1,-1,1,-1};
        int[]dy = {1,1,-1,-1};
        
        for(int i = 0; i < 4 ; i ++){
            int newX = x+dx[i];
            int newY = y+dy[i];
        
            while(RESTRICCION(newX,newY)){
            if(T.getCasillas()[newX][newY] instanceof Fichas){
                p.add(new Point(newX,newY));
                break;
            } // aqui miramos si al mover en el tablero la siguiete es ficha y dmiramos otra direccion
                p.add(new Point(newX,newY));
                newX += dx[i];
                newY += dy[i];
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
        return new Obispo();
    }
    
}
