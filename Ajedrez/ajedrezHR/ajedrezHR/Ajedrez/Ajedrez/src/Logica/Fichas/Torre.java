package Logica.Fichas;

import Logica.Tablero;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Torre extends Fichas{
    
    private Point posicion;
    private ImageIcon torreB;//torre Blanca
    private ImageIcon torreN; // Toirre negra
    private String TIPO;
    
    public Torre(){
        torreB = new ImageIcon(getClass().getResource("/Persistencia/Fichas/BlancoTorre.png"));
        torreN = new ImageIcon(getClass().getResource("/Persistencia/Fichas/NegroTorre.png"));

    }
    
    public ImageIcon getImageB(){
        if(TIPO.equals("NEGRAS")){
            return torreN;
        }
        return torreB;
    }
    public ImageIcon getImageN(){
        return torreN;
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
        
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        
        for(int i= 0; i <4 ; i ++){
            int newX = x+dx[i];   
            int newY = y + dy[i];

            while(RESTRICCION(newX,newY)){
            if(T.getCasillas()[newX][newY] instanceof Fichas){
                p.add(new Point(newX,newY)); //lo añadimos pero ya no miramos mas en esa direccion (lo añadimos para despues evaluar si es o no enemigo)
                break;
            } // aqui miramos si al mover en el tablero la siguiete es ficha y dmiramos otra direccion
                p.add(new Point(newX,newY));
                newX+=dx[i];
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
        return new Torre();
    }
    
}
