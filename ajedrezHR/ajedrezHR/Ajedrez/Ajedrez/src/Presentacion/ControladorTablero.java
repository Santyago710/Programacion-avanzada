package Presentacion;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;


class ControladorTablero implements MouseListener{
    
    private VistaTablero VT;
    private final Color unoResalte = new Color(192, 112, 42 ); // no se deberia cambiar
    private final Color dosResalte = new Color(248, 164, 92 ); // 
    private final Color PERMITIDO = new Color(200,200,200); //que me indique a donde puedo mover
    private Map<Point,Color> pModificados = new HashMap(); // La llave key -> punto, y el Color es su valor (get.Key) // tambien pudimos guardar a los objetos que hacen parte de,. pero no se como xd
    
    public ControladorTablero(VistaTablero VT){
        this.VT =  VT;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
          
        
    }    
    @Override
    public void mousePressed(MouseEvent e) {          // cuando presionamos
        
        
        
         
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        for(int fila = 0; fila < 8; fila++) { //recorremos los arrays
            for(int columna = 0; columna < 8; columna++) { 
                
                if(e.getSource().equals(VT.getPaneles()[fila][columna])) {// miramos si fue clicekado en alguno 
                    switch (e.getButton()) {
                    case MouseEvent.BUTTON1 -> HanlderClickIzquierdo(fila, columna);
                    case MouseEvent.BUTTON3 -> HandlerClickDerecho(fila, columna);
                    }
            return; // Salimos del bucle una vez que se ha manejado el evento
                }
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {    
        Sombrear(e,VT.getUno(),unoResalte,VT.getDos(),dosResalte);       
    }
    @Override
    public void mouseExited(MouseEvent e) {
        Sombrear(e,unoResalte,VT.getUno(),dosResalte,VT.getDos());            
    }
    
     //////////////////////////////funciones privadas
    private void Sombrear(MouseEvent e, Color evaluar1, Color cambiar1, Color evaluar2 , Color cambiar2){
        for(int fila = 0; fila <8 ; fila++){
            for(int columna = 0; columna < 8; columna ++){
        
                 if(e.getSource().equals(VT.getPaneles()[fila][columna]) && VT.getPaneles()[fila][columna].getBackground().equals(evaluar1) ){
                     VT.getPaneles()[fila][columna].setBackground(cambiar1);
                     
                 }
                 else if(e.getSource().equals(VT.getPaneles()[fila][columna]) &&VT.getPaneles()[fila][columna].getBackground().equals(evaluar2) ){
                     VT.getPaneles()[fila][columna].setBackground(cambiar2);
                 }
            }
        }
    }
    
    private void HandlerPaint(int fila, int columna){
        if(VT.getModelo().getJuego().getTablero().getCasillas()[fila][columna] == null ) return; // primero verificamos si la casilla es nula
        if(VT.getModelo().getJuego().getTablero().casillaAyuda(fila, columna) == null)return; // luego verificamos si a donde apunta es nula
           
        for(Point a : VT.getModelo().getJuego().getTablero().casillaAyuda(fila, columna) ){           
            pModificados.put(a,VT.getModelo().getVTablero().getPaneles()[a.x][a.y].getBackground()); // primero guardaremos el lugar
            VT.getModelo().getVTablero().getPaneles()[a.x][a.y].setBackground(PERMITIDO); // y pintamos en los puntos adjuntos al for
        }
    }
    private void ReponerPanel(){
        pModificados.keySet().forEach( p -> { // expresion lambda 
            // porque son puntos xd
            VT.getModelo().getVTablero().getPaneles()[p.x][p.y].setBackground(pModificados.get(p)); // y pintamos en los puntos adjuntos al for
        });        
        pModificados.clear(); // lo limpiamos para el siguiente
    } // Para reponer los paneles
    
    private void HanlderClickIzquierdo(int fila, int columna){
        if (VT.getModelo().getJuego().getTablero().getMODO()) { // y miramos el modo, si es modo sleccion o normal (si no esta en modo seleccion, o sea, si es normal)
                    ReponerPanel();
                    VT.getModelo().getJuego().getTablero().setMODO(false);
                    return; // finalizamos el if para que no pinte el mismo
                }
                HandlerPaint(fila, columna); // pintamos de todas maneras
                if (VT.getModelo().getJuego().getTablero().getCasillas()[fila][columna] != null) { // si el seleccionado no es nulo
                    VT.getModelo().getJuego().getTablero().setMODO(true); // cambio el modo de juego a modo seleccion
                    VT.getModelo().PuntoInicio(fila, columna); // y paso el punto de inicio
                }
    }
    
    private void HandlerClickDerecho(int fila, int columna){
         if (VT.getPaneles()[fila][columna].getBackground().equals(PERMITIDO)) {
                    ReponerPanel(); // reponemos los paneles de ayuda
                    VT.getModelo().PuntoFinal(fila, columna); // mandamos el punto final
                    VT.getModelo().getJuego().getTablero().setMODO(false); //quitamos el modo seleccion
                  //  VT.getModelo().getRender().ActualizarFichasMuertas(); // cada que hagamos click derecho actualizaremos
                } else {
                    System.out.println("No se puede");
                }
    }

  
}