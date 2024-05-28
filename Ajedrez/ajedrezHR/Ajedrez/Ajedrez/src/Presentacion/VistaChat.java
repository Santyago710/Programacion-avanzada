package Presentacion;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class VistaChat extends javax.swing.JPanel {

    private ControladorChat control;
    private Modelo modelo;
    
    public VistaChat(Modelo modelo) {
        this.modelo = modelo; // inicializamos el modelo
        initComponents();
        addListeners();
    }

    public JLabel getLB_ENVIAR() {
        return LB_ENVIAR;
    }

    public JTextArea getTA_CHAT() {
        return TA_CHAT;
    }

    public JTextPane getTP_ESCRITURA() {
        return TP_ESCRITURA;
    }

    public Modelo getModelo(){
        return modelo;
    }
    private ControladorChat getControl() {
        if(control == null){
            control = new ControladorChat(this); // lo inicialiamos
        }
        return control;
    }

    
    private void addListeners(){
        LB_ENVIAR.addMouseListener(getControl());
        TA_CHAT.addMouseListener(getControl());
        TP_ESCRITURA.addMouseListener(getControl());
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TP_ESCRITURA = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        TA_CHAT = new javax.swing.JTextArea();
        LB_ENVIAR = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TP_ESCRITURA.setBorder(null);
        TP_ESCRITURA.setFont(new java.awt.Font("DejaVu Serif", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(TP_ESCRITURA);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 582, 180, 30));

        TA_CHAT.setEditable(false);
        TA_CHAT.setBackground(new java.awt.Color(204, 204, 204));
        TA_CHAT.setColumns(20);
        TA_CHAT.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 14)); // NOI18N
        TA_CHAT.setForeground(new java.awt.Color(0, 0, 0));
        TA_CHAT.setRows(5);
        TA_CHAT.setBorder(null);
        jScrollPane2.setViewportView(TA_CHAT);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 550));

        LB_ENVIAR.setBackground(new java.awt.Color(255, 255, 255));
        LB_ENVIAR.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 14)); // NOI18N
        LB_ENVIAR.setText("ENVIAR");
        add(LB_ENVIAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, 63, 38));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LB_ENVIAR;
    private javax.swing.JTextArea TA_CHAT;
    private javax.swing.JTextPane TP_ESCRITURA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
