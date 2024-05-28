package Logica.Fichas;

import Logica.Tablero;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;


public class Caballo extends Fichas{
        
    private Point posicion;
    private ImageIcon caballoB;//torre Blanca
    private ImageIcon caballoN; // Toirre negra
    private String TIPO;
    
    public Caballo(){
        caballoB = new ImageIcon(getClass().getResource("/Persistencia/Fichas/BlancoCaballo.png"));
        caballoN = new ImageIcon(getClass().getResource("/Persistencia/Fichas/NegroCaballo.png"));

    }
    
    @Override
    public ImageIcon getImageB(){
        if(TIPO.equals("NEGRAS")){
        return caballoN;
        }
        return caballoB;
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
        int[][] movimientos = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
         }; 
    // mirar sobre el Arryas Stream  : para crear un flujo de datos a partir de la matriz de movimientos ; los flujos son operaciones con for each, filter, map,
    Arrays.stream(movimientos)
        .map(movimiento -> new Point(x + movimiento[0], y + movimiento[1])) // aqui le digo que un mapa donde movimiento es el metodo, y le doy una funcion con -> que cree un punto
        .filter(point -> RESTRICCION(point.x, point.y)) // luego me filtre los tipos de punto
        .forEach(p::add);  // y mediante :: un metodo de referencia le decimos que añada al punto creado 

    return p;
    }
    
    @Override
    public void setTipo(String tipo){
    TIPO = tipo;
    }

 @Override
    public Fichas Cast()
    {
        return new Caballo();
    }


}
