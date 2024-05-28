package Presentacion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VistaEleccion extends javax.swing.JPanel {

    private Modelo modelo;
    private ControladorEleccion control;
    // los "inicializo" los colores para tenerlos ahi
    private Color colorPBlanco = new Color(255,255,255);
    private Color colorPNegro = new Color(50,50,50);
    
    
    public VistaEleccion(Modelo m) {
        modelo = m; // inicializamos el modelo
        initComponents();
        addListeners();
    }

    public Modelo getModelo(){
        return modelo;
    }
    public ControladorEleccion getControlador(){
        if(control == null){
            control =  new ControladorEleccion(this);
        }
        return control;
    }
    
    public JLabel getJLBLANCAS() {
        return JL_BLANCAS;
    }

    public JLabel getJLNEGRAS() {
        return JL_NEGRAS;
    }

    public JPanel getPNB() {
        return PNB;
    }

    public JPanel getPNN() {
        return PNN;
    }

    public Color getColorPBlanco() {
        return colorPBlanco;
    }

    public Color getColorPNegro() {
        return colorPNegro;
    }
    

    private void addListeners(){ // añado cada uno de los listeners
        JL_BLANCAS.addMouseListener(getControlador());
        JL_NEGRAS.addMouseListener(getControlador());
        PNN.addMouseListener(getControlador());
        PNB.addMouseListener(getControlador());
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PNB = new javax.swing.JPanel();
        JL_BLANCAS = new javax.swing.JLabel();
        PNN = new javax.swing.JPanel();
        JL_NEGRAS = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 645));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PNB.setBackground(new java.awt.Color(255, 255, 255));
        PNB.setPreferredSize(new java.awt.Dimension(400, 645));

        JL_BLANCAS.setFont(new java.awt.Font("DejaVu Serif Condensed", 1, 36)); // NOI18N
        JL_BLANCAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Persistencia/BOTONES/E_BLANCAS.png"))); // NOI18N
        JL_BLANCAS.setText("BLANCAS");
        JL_BLANCAS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JL_BLANCAS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout PNBLayout = new javax.swing.GroupLayout(PNB);
        PNB.setLayout(PNBLayout);
        PNBLayout.setHorizontalGroup(
            PNBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNBLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(JL_BLANCAS)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        PNBLayout.setVerticalGroup(
            PNBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNBLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(JL_BLANCAS)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        add(PNB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PNN.setBackground(new java.awt.Color(50, 50, 50));
        PNN.setPreferredSize(new java.awt.Dimension(400, 650));

        JL_NEGRAS.setFont(new java.awt.Font("DejaVu Serif Condensed", 1, 36)); // NOI18N
        JL_NEGRAS.setForeground(new java.awt.Color(255, 255, 255));
        JL_NEGRAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Persistencia/BOTONES/E_NEGRAS.png"))); // NOI18N
        JL_NEGRAS.setText("NEGRAS");
        JL_NEGRAS.setToolTipText("");
        JL_NEGRAS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JL_NEGRAS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout PNNLayout = new javax.swing.GroupLayout(PNN);
        PNN.setLayout(PNNLayout);
        PNNLayout.setHorizontalGroup(
            PNNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNNLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(JL_NEGRAS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PNNLayout.setVerticalGroup(
            PNNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNNLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(JL_NEGRAS)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        add(PNN, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 420, 650));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL_BLANCAS;
    private javax.swing.JLabel JL_NEGRAS;
    private javax.swing.JPanel PNB;
    private javax.swing.JPanel PNN;
    // End of variables declaration//GEN-END:variables
}
