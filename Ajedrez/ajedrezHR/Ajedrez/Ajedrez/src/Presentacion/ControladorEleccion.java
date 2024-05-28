package Presentacion;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ControladorEleccion implements MouseListener {
    private VistaEleccion VE;
    private Color panelNegroE =  new Color(102,102,102); // panel negro entrar
    private Color panelBlancoE = new Color(210,210,210); // panel negro entrar
    
    public ControladorEleccion(VistaEleccion V){
        VE = V;
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) { // cuando el mouse fue presionado
        
        if(e.getSource().equals(VE.getPNB()) || e.getSource().equals(VE.getJLBLANCAS())){
        VE.getModelo().getJugador().setPosee("BLANCAS");
        VE.getModelo().InitTablero();
        }
        if(e.getSource().equals(VE.getPNN()) || e.getSource().equals(VE.getJLNEGRAS())){
        VE.getModelo().getJugador().setPosee("NEGRAS");
        VE.getModelo().InitTablero();

        }
        VE.getModelo().iniciarConexion(); // iniciamos tambien la conexion        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        // cuando el mouse entro a los paneles: 
        if(e.getSource().equals(VE.getPNN()) || e.getSource().equals(VE.getJLNEGRAS())){
            VE.getPNN().setBackground(panelNegroE);
        }
        else{
            VE.getPNB().setBackground(panelBlancoE);
        }
             
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //lo mismo
        if(VE.getPNN().getBackground().equals(panelNegroE) ){
            VE.getPNN().setBackground(VE.getColorPNegro());
        }
        if(VE.getPNB().getBackground().equals(panelBlancoE)){
            VE.getPNB().setBackground(VE.getColorPBlanco());
        }
        
        
    }
    
}
