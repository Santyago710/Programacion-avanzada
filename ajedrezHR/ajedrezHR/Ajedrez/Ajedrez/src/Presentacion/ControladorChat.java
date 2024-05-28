package Presentacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 *
 * @author enamo
 */
class ControladorChat implements MouseListener {

    private VistaChat c;
    
    public ControladorChat(VistaChat c){
        this.c = c;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource().equals(c.getLB_ENVIAR())){
            String texto = c.getTP_ESCRITURA().getText(); // lo pasamos a un texto
            c.getTP_ESCRITURA().setText("");
            try {
                c.getModelo().getConexion().Enviar("CC"+texto); // El CC es el comando para que lo devuelva de otra forma
            } catch (IOException ex) {
                System.out.println("Error: "+ex.getMessage());
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
    
}
