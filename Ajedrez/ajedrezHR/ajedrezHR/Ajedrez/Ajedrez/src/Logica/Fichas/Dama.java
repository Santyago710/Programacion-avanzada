package Logica.Fichas;

import Logica.Tablero;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Dama extends Fichas {
        
    private Point posicion;
    private ImageIcon damaB;//torre Blanca
    private ImageIcon damaN; // Toirre negra
    private String TIPO;
    
    public Dama(){
        damaB = new ImageIcon(getClass().getResource("/Persistencia/Fichas/BlancoDama.png"));
        damaN = new ImageIcon(getClass().getResource("/Persistencia/Fichas/NegroDama.png"));

    }
    
    public ImageIcon getImageB(){
        if(TIPO.equals("NEGRAS")){
            return damaN;
        }
        return damaB;
    }
    public ImageIcon getImageN(){
        return damaN;
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
    ArrayList<Point> p = new ArrayList<>();
    int x = posicion.x;
    int y = posicion.y;

    int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    for (int i = 0; i < 8; i++) {
        int newX = x + dx[i];
        int newY = y + dy[i];

        while (RESTRICCION(newX,newY)) {
            if(T.getCasillas()[newX][newY] instanceof Fichas){
                 p.add(new Point(newX, newY));   
                break;
            } // aqui miramos si al mover en el tablero la siguiete es ficha y dmiramos otra direccion
            p.add(new Point(newX, newY));   
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
        return new Dama();
    }
    
}
