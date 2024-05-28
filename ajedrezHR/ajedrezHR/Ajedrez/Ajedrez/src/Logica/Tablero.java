package Logica;

import Logica.Fichas.Fichas;
import java.awt.Point;
import java.util.ArrayList;


public class Tablero {
    
    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;
    private final Fichas[][] casillas; // aqui se crean las casillas
    private final Boolean[][] seleccionada; // aqui se mira si fue o no seleccionada la ficha
    private final Movimiento movimiento;
    private boolean MODO; //El tipo , si esta en modo selecicon es true, si no es false
    
    public Tablero()
    {
        movimiento = new Movimiento();
        MODO = false;
        casillas = new Fichas[FILAS][COLUMNAS];
        seleccionada = new Boolean[FILAS][COLUMNAS];
        crearTablero();
    
    }
    
    private void crearTablero(){ // inicializa el tablero, lo crea
        for(int i=0 ; i < FILAS ; i ++ ){
            for(int j = 0; j<COLUMNAS; j++){
                casillas[i][j] = null;  // ponemos la posicion en nulo
                seleccionada[i][j] = false; // todas seran falso porque no las he seleccionad
            }
        }
    }
    
    public ArrayList<Point> casillaAyuda(int fila, int columna){  //tambien deberia ser un array de puntos
        ArrayList<Point> aux = new ArrayList();
        String pTipo = getCasillas()[fila][columna].getTipo(); // guardamos el primer tipo que se nos da
        for(Point a : getCasillas()[fila][columna].mover(this) ){
            
                int x = a.x; // sacamos los puntos
                int y = a.y; // x,y
                if(getCasillas()[x][y]!= null && !pTipo.equals(getCasillas()[x][y].Enemigo()))continue; // si el siguiente es una ficha, entonces la omito, pero si es enemigo lo marco
                aux.add(a);   
           }
return aux;    
    }
// gettesr
    public Fichas[][] getCasillas() {
        return casillas;
    }
    
    public Boolean[][] getSeleccionada() {
        return seleccionada;
    }
    
    public Movimiento getMovimiento(){
        return movimiento;
    }
    
    public boolean getMODO(){
        return MODO;
    }

    public void setMODO(boolean modo){
        this.MODO = modo;
    }

    
}


