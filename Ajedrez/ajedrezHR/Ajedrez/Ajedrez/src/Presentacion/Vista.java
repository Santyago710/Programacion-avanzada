package Presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vista extends javax.swing.JFrame {

    private Modelo modelo;
 
    public Vista(Modelo modelo) {
        super("AJEDREZ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.modelo = modelo;
    }

    public JPanel addVista(JPanel vista){
       if(vista == null)
       {
           vista = new VistaInicio(modelo);
       }     
       
       add(vista);
       repaint();
       pack();
    return vista;
   }   //añade un panel
   
    public void closeVista(JPanel vista){
       // Remueve el panel del contenedor principal
        remove(vista);
        // Invalida el contenedor principal para que se actualice
        invalidate();
        // Vuelve a validar el contenedor principal
        validate();
        // Empaqueta el JFrame nuevamente para ajustar el tamaño según sea necesario
        repaint();
    }    // quita un panel
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AJEDREZ");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(20, 20, 0, 0));
        setName("AJEDREZ"); // NOI18N
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
