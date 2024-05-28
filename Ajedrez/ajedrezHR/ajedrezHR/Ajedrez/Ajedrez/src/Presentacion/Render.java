package Presentacion;

import Logica.Fichas.*;
import java.io.IOException;
import javax.swing.JLabel;

public class Render extends Thread {
    
    private Modelo modelo;
    //dibujo
    private JLabel[][] labels;
    private JLabel[] fMuertas;
    private JLabel reloj; // aqui dibujaremos el reloj
    private boolean dibujar;



    public Render(Modelo modelo){
        dibujar = false;
        this.modelo = modelo;
        labels =  modelo.getVTablero().getLabels(); // igualamos el label
        fMuertas =  modelo.getVTablero().getFMuertas();
        reloj = modelo.getVTablero().getReloj(); // ponemos el reloj para configurarlo
        // inicializo las fichas muertas del tablero:
        initFichasMuertas();
    }

    private void ActualizarTablero(){
        for (int i = 0; i < 8; i++) { // y aqui borramos.
              for (int j = 0; j < 8; j++) {
                 if( modelo.getJuego().getTablero().getCasillas()[i][j] == null) {
                    labels[i][j].setIcon(null); 
                 }
                 else{
                    labels[i][j].setIcon(modelo.getJuego().getTablero().getCasillas()[i][j].getImageB()); // pongo imagen donde hay ficha
                 }            
    }
} 
    }
    
    public void ActualizarFichasMuertas(){
        if(modelo.getJuego().FichaPerdida() == null) return; // si es nulo que no haga nada
        
        modelo.getJuego().FichaPerdida().forEach((k,v)->{
            if(k instanceof Peon)
                fMuertas[5].setText("Peon: "+v);
            if(k instanceof Rey)
                fMuertas[0].setText("Rey: "+v);
            if(k instanceof Dama)
                fMuertas[1].setText("Dama: "+v);
            if(k instanceof Obispo)
                fMuertas[2].setText("Obispo: "+v);
            if(k instanceof Torre)
                fMuertas[4].setText("Torre: "+v);
            if(k instanceof Caballo)
                fMuertas[3].setText("Caballo: "+v);
        });

        
        
    }
    
    private void ponerficha(JLabel j ,String tipo){
        j.setText(tipo);
        j.setIcon(modelo.getJuego().getFicha(tipo).getImageB()); // modelo.getJugador().getPosee()
    }
    
    private void initFichasMuertas(){ //hay que iniciar las fichas muertas con sus respectivos logos
        ponerficha(fMuertas[0],"Rey");
        ponerficha(fMuertas[1],"Dama");
        ponerficha(fMuertas[2],"Obispo");
        ponerficha(fMuertas[3],"Caballo");
        ponerficha(fMuertas[4],"Torre");
        ponerficha(fMuertas[5],"Peon");
        
        
    }
    
    private void controlReloj(){ // este control de tiempo debe comnezar cuando el juego este completo, es decir, apenas se conecten los dos debe de haber una señal que se envie y de esta manera comnezar el reloj
        reloj.setText("Tiempo: "+modelo.getReloj().getTiempo()); // ponemos el tiempo a cada rato
                if(modelo.getReloj().getTiempo() == modelo.getReloj().getFINAL()){
                    modelo.getReloj().setTiempo(0);
                    modelo.getJugador().setTurno((modelo.getJugador().getTurno()==1)? 0:1); // aca debemos enviar tambien
                }
    }
    
    @Override
    public void run()
    {
        dibujar = true; 
        while(dibujar)
        {
         try {
             // y consntantemente estaremos reviando el reloj
                controlReloj();
                ActualizarFichasMuertas();
                ActualizarTablero();
                String mensaje = modelo.getConexion().ComunicacionServer();
                if(modelo.getJuego().Ganaron()){
                    modelo.Terminar();
                    break;
                }
                if(mensaje != null){
                modelo.getConexion().Enviar(mensaje);
                
                }
            } catch (   IOException | java.lang.NullPointerException ex) {
                //Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}