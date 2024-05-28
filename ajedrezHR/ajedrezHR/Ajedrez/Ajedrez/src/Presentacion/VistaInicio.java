package Presentacion;

import java.awt.Color;
import javax.swing.JLabel;

public class VistaInicio extends javax.swing.JPanel {

   // recoerdar el  setPreferredSize(new java.awt.Dimension(pH*panelSize, pV*panelSize)); 
    private Modelo modelo;
    private ControladorInicio control;
    private Color texto;
    
    public VistaInicio(Modelo modelo) {
        this.modelo = modelo;
        texto = new Color(255,255,255);
        initComponents();
        JL_JUGAR.setForeground(texto);
        addListeners();
    }

    
    
    public Modelo getModelo(){
        return modelo;
    }
    public ControladorInicio getControl(){
        if(control == null){
            control = new ControladorInicio(this);
        }
        return control;
    }
    public javax.swing.JLabel getJLJugar(){
        return JL_JUGAR;
    }
    public Color getColorTexto(){
        return texto;
    }

    public JLabel getJLMULTIJUGADOR() {
        return JL_MULTIJUGADOR;
    }

    public JLabel getJLSALIR() {
        return jLabel4;
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        PN_izq = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        JL_JUGAR = new javax.swing.JLabel();
        JL_MULTIJUGADOR = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(217, 136, 128));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Persistencia/Fichas/1Chess.png"))); // NOI18N
        jLabel2.setToolTipText("");

        PN_izq.setBackground(new java.awt.Color(36, 113, 163));
        PN_izq.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PN_izq.setEnabled(false);
        PN_izq.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        PN_izq.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        JL_JUGAR.setFont(new java.awt.Font("DejaVu Serif Condensed", 1, 24)); // NOI18N
        JL_JUGAR.setForeground(new java.awt.Color(255, 255, 255));
        JL_JUGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Persistencia/BOTONES/JUGARJ.png"))); // NOI18N
        JL_JUGAR.setText("JUGAR");
        JL_JUGAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JL_JUGAR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JL_JUGAR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PN_izq.add(JL_JUGAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        JL_MULTIJUGADOR.setFont(new java.awt.Font("DejaVu Serif Condensed", 1, 24)); // NOI18N
        JL_MULTIJUGADOR.setForeground(new java.awt.Color(255, 255, 255));
        JL_MULTIJUGADOR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Persistencia/BOTONES/MULTIJ.png"))); // NOI18N
        JL_MULTIJUGADOR.setText("MULTIJUGADOR");
        JL_MULTIJUGADOR.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JL_MULTIJUGADOR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JL_MULTIJUGADOR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JL_MULTIJUGADOR.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PN_izq.add(JL_MULTIJUGADOR, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("DejaVu Serif Condensed", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Persistencia/BOTONES/SALIR.png"))); // NOI18N
        jLabel4.setText("SALIR");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PN_izq.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PN_izq, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(PN_izq, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL_JUGAR;
    private javax.swing.JLabel JL_MULTIJUGADOR;
    private javax.swing.JPanel PN_izq;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables


    private void addListeners()
    {
     JL_JUGAR.addMouseListener(getControl());
     JL_MULTIJUGADOR.addMouseListener(getControl());
     jLabel4.addMouseListener(getControl());
    }

}

