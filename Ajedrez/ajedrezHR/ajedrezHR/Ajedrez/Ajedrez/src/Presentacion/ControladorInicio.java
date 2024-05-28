package Presentacion;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ControladorInicio implements MouseListener {
    private VistaInicio VI;
    private  Color resalte = new Color(151, 154, 154); // El color del resaltado
    
    // auxiliares
    private String JUGARG = "/Persistencia/BOTONES/JUGARG.png";
    private String JUGARJ = "/Persistencia/BOTONES/JUGARJ.png";
    private String MULTIG = "/Persistencia/BOTONES/MULTIG.png";
    private String MULTIJ = "/Persistencia/BOTONES/MULTIJ.png";
    private String SALIRG = "/Persistencia/BOTONES/SALIRG.png";
    private String SALIRJ = "/Persistencia/BOTONES/SALIR.png";
    //
    public ControladorInicio(VistaInicio VI){
        this.VI=VI;
    }
    
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

       if(e.getSource().equals(VI.getJLJugar())) // Si presionamos el boton de "jugar" no entrara a multijugador
        {
            VI.getModelo().InitEleccion(); // iniciamos la eleccion del usuario
        }
       if(e.getSource().equals(VI.getJLMULTIJUGADOR())) // Si presionamos el "boton" de multijugador
        {
            VI.getModelo().InitEleccion(); // iniciamos la eleccion del usuario
        }
       if(e.getSource().equals(VI.getJLJugar()))
        {
            VI.getModelo().Terminar(); // terminamos
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(VI.getJLJugar()))
        {
            VI.getJLJugar().setForeground(resalte);
            VI.getJLJugar().setIcon(new javax.swing.ImageIcon(getClass().getResource(JUGARG)));
        }
         if(e.getSource().equals(VI.getJLMULTIJUGADOR()))
        {
            VI.getJLMULTIJUGADOR().setForeground(resalte);
            VI.getJLMULTIJUGADOR().setIcon(new javax.swing.ImageIcon(getClass().getResource(MULTIG)));
        }
         if(e.getSource().equals(VI.getJLSALIR()))
        {
            VI.getJLSALIR().setForeground(resalte);
            VI.getJLSALIR().setIcon(new javax.swing.ImageIcon(getClass().getResource(SALIRG)));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        if(VI.getJLJugar().getForeground().equals(resalte))
        {
            VI.getJLJugar().setForeground(VI.getColorTexto());
            VI.getJLJugar().setIcon(new javax.swing.ImageIcon(getClass().getResource(JUGARJ)));

        }
         if(VI.getJLMULTIJUGADOR().getForeground().equals(resalte))
        {
            VI.getJLMULTIJUGADOR().setForeground(VI.getColorTexto());
            VI.getJLMULTIJUGADOR().setIcon(new javax.swing.ImageIcon(getClass().getResource(MULTIJ)));
        }
         if(VI.getJLSALIR().getForeground().equals(resalte))
        {
            VI.getJLSALIR().setForeground(VI.getColorTexto());
            VI.getJLSALIR().setIcon(new javax.swing.ImageIcon(getClass().getResource(SALIRJ)));
        }
        
        
        
        
    }
    
}
