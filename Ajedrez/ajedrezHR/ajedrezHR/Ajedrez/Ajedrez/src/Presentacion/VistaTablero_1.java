package Presentacion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VistaTablero extends javax.swing.JPanel {

    private Modelo modelo;
    private ControladorTablero control;
    ///////////////////////////////////////////////////////////
    private VistaChat Chat;
    private JPanel[][] paneles; // una matriz de paneles que ya vamos a inicializar
    private JLabel[][] labels; // los labels en paneles que controlan
    private final int ancho,largo,Achat,Lemo; // ancho y largo del tablero , Ancho chat y Largo emogis
    private final Color uno = new Color(247, 249, 249); // no se deberia cambiar 175, 96, 26 
    private final Color dos = new Color(192, 57, 43); // 235, 152, 78
    private JLabel fMuertas[]; // las figuras, son 6 en total: Rey,Reina,Peon,Obispo,Caballo,Torre
    private JLabel reloj ;  // este label nos mostrara el reloj
    
    public VistaTablero(Modelo modelo) {
     this.Achat = 290;
     this.Lemo = 130;
     this.largo = 680+Lemo;
     this.ancho = 680+Achat;
     this.modelo = modelo;
     this.paneles =  new JPanel[8][8]; // 8 filas, 8 columnas
     this.labels = new JLabel[8][8]; // 8 filas, 8 columnas
     this.fMuertas = new JLabel[6]; // creamos un arreglo de 6 xd, para las figuras
     Chat = new VistaChat(modelo); // iniciamos el chat
     this.reloj = new JLabel();
            reloj.setSize(100,100); // aqui ponemos el tamaño que queremos
            reloj.setLocation(720,705); // aqui ponemos el lugar que queremos
            reloj.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER); // los acomodamos
            reloj.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            reloj.setForeground(Color.WHITE);
            reloj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Persistencia/BOTONES/1RELOJ.png")));
            add(reloj); // lo añadimos
     setLayout(null); // aqui lo hacemos null para poder dibujar
     setBackground(new Color(146, 43, 33));
     setPreferredSize(new java.awt.Dimension(ancho,largo)); // inicializamos el tablero en 900,900
     iniciarTablero(); // iniciamos el tablero
     initChat(); // iniciamos el chat
     apartadoFichas(); // ponemos el apartado de las fichas (donde dice si muere o no)
     addListeners();// añadimos los listeners
    }

    // Parte del tablero
    private void iniciarTablero(){
        int auxL = 20; // auxiliar largo
        int auxA = 20; // auxiliar Ancho
        for(int fila = 0 ; fila < 8 ; ++fila){
            for(int columna = 0; columna < 8 ; ++columna){
                paneles[fila][columna] = new JPanel(); // aqui creamos un panel
                labels[fila][columna] = new JLabel();
                paneles[fila][columna].add(labels[fila][columna]); // añadimos un label a un jpanel
                paneles[fila][columna].setSize((ancho-Achat-35)/8,(largo-Lemo-35)/8); // el tamaño
                paneles[fila][columna].setLocation(auxL,auxA);  // el lugar
                auxL+=(ancho-35-Achat)/8;
                this.add(paneles[fila][columna]); // aqui lo añadimos
            }
            auxA+= (largo-Lemo-35)/8; auxL = 20;

        }
        pintarTablero();
        
    }
    
    private void pintarTablero() {
    for (int fila = 0; fila < 8; ++fila) {
        for (int columna = 0; columna < 8; ++columna) {
            paneles[fila][columna].setBackground((fila + columna) % 2 == 0 ? uno : dos);
        }
    }
    }// operador ternario para pintar mas rapido
    // Parte del "chat"
    private void initChat(){
        Chat.setLocation((largo-Lemo+5),0); // iniciamos el chat en el largo y ancho
        Chat.setSize(new java.awt.Dimension(Achat,700)); // creamos una nueva dimension y la metemos
         //agregamos
        this.add(Chat);
        
    }
        
    private void apartadoFichas(){
       int x = 15;
       int y = 670;
        for(int i = 0; i < fMuertas.length ; i++){
           fMuertas[i] = new JLabel(); // creamos el Jlabel
           fMuertas[i].setSize(120, 120); // ponemos el tamaño
           fMuertas[i].setLocation(x, y); // le colocamos donde queremos que este 
           fMuertas[i].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER); // los acomodamos
           fMuertas[i].setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
           fMuertas[i].setForeground(Color.WHITE);
           x+=110;// y luego le damos al siguiente
          add(fMuertas[i]);
       }
        
    }
    
    private void addListeners()
    {
        for(int fila = 0; fila <8 ; fila++){
            for(int columna = 0; columna < 8; columna ++){
                paneles[fila][columna].addMouseListener(getControl()); // añadimos los listeners
            }
        }
    }
    // getters
    public Color getUno(){
        return uno;
    }
    public VistaChat getChat(){
        return Chat;
    }
    public Color getDos(){
        return dos;
    }

    public JPanel[][] getPaneles() {
        return paneles;
    }
    
    public JLabel[][] getLabels(){
        return labels;
    }
    
    public JLabel[] getFMuertas() {
        return fMuertas;
    }
    
    private ControladorTablero getControl(){
        if(control == null){
            control = new ControladorTablero(this);
        }
        return control;
    }
    
    public JLabel getReloj(){
        return reloj; 
    }// getter del reloj
    
    public Modelo getModelo(){
        return modelo;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(146, 43, 33));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
