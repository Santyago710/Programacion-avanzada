package Presentacion;

import Logica.Conexion.CAjedrez;
import Logica.Ajedrez;
import Logica.Jugador.Jugador;
import Logica.Reloj.Reloj;
import java.awt.Point;
import java.io.IOException;

public class Modelo {
    
    private Vista PRINCIPAL;
    private VistaInicio INICIO;
    private VistaEleccion ELECCION;
    private VistaTablero TABLERO;
    private VistaEsperar ESPERA;
    private Render render; // el render del juego
    private Ajedrez juego; // aqui esta la logica
    //Reloj
    private Reloj RELOJ;
    //Conexion
    private Jugador player;
    private CAjedrez CONEXION;
    
    
    public Vista getVPrincipal(){
        if(PRINCIPAL == null) {
            PRINCIPAL = new Vista(this);
        }
        return PRINCIPAL;
    } // Tiene al Jframe principal
    
    public VistaInicio getVInicio(){
        if(INICIO == null){
            INICIO = new VistaInicio(this);
        }
        
        return INICIO;
    }// Tiene en si la Vista de Inicio
    
    public VistaEleccion getVEleccion(){
        if(ELECCION == null){
            ELECCION = new VistaEleccion(this);
        }
        
        return ELECCION;
    }// la vetnana de elejir si es blanco o negro
    
    public VistaEsperar getEspera(){
        if(ESPERA == null)
            ESPERA = new VistaEsperar(this);
        
        return ESPERA;
    }
    
    public VistaTablero getVTablero(){
        if(TABLERO == null)
        {
            TABLERO= new VistaTablero(this);
        }
        return TABLERO;
    } // Tiene al tablero principal
    
    public Render getRender(){
        if(render == null){
            render = new Render(this);
        }
        return render;
    } // creamos el render
    
    public Ajedrez getJuego(){
        if(juego == null){
            juego = new Ajedrez(getJugador());
        }
    return juego;
    } // Tenemos el tablero

    public Reloj getReloj(){
        if(RELOJ == null)
            RELOJ = new Reloj();
        return RELOJ;
    }
    
    public CAjedrez getConexion(){
        if(CONEXION == null)
            CONEXION = new CAjedrez();
        return CONEXION;
    }
    
    public Jugador getJugador(){
        if(player == null){
            player = new Jugador();
        }
        return player;
    }    
    //// Lectura de Movimientos
    
    public void PuntoFinal(int fila, int columna) {
        Point fin = new Point();
        fin.setLocation(fila,columna);
        juego.getTablero().getMovimiento().setFin(fin);
    }

    public void PuntoInicio(int fila, int columna) {
        Point inicio = new Point();
        inicio.setLocation(fila,columna);
        juego.getTablero().getMovimiento().setInicio(inicio); // metemos un punto de inicio y unio de final

    }
    ////////////////////////////////// Inicio del tablero
    public void InitEleccion(){
        PRINCIPAL.closeVista(getVInicio());
        PRINCIPAL.addVista(getVEleccion());
    }
    public void InitEspera(){
        PRINCIPAL.closeVista(getVEleccion());
        PRINCIPAL.repaint();
        PRINCIPAL.addVista(getEspera());
    }
    
    public void InitTablero(){ // aqui toca meter el largo y ancho, de mientras
        //Parte Visual
        PRINCIPAL.closeVista(getEspera());
        PRINCIPAL.closeVista(getVEleccion());
        PRINCIPAL.addVista(getVTablero());
        //Parte Logica
        getJuego(); // iniciamos el juego
        getReloj().start(); // iniciamos el reloj
        getRender().start(); // e iniciamos el render
    }
    
    /////////////////////////INICIO CONEXION
    public void iniciarConexion(){
        //iniciamos la conexion
        try {
            getConexion().Conectar(getJuego(),getVTablero().getChat().getTA_CHAT(),getReloj()); // conectamos el juego
            getConexion().start();//  y corremos un hilo
            InitEspera();
            while(!getConexion().Recibir().equals("TIME")){
             //aqui finjimos una funcion bloqueante  
                System.out.println("");
             }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            
           // Terminar();
        } 
    }
    /////////////////////////////// INICIO DEL PROGRAMA
    public void Iniciar(){
       getJugador(); // inicializamos el jugador
       getVPrincipal();
       PRINCIPAL.addVista(getVInicio());
       PRINCIPAL.setVisible(true);
     
    } // inicia el programa
    
    public void Terminar(){
        System.out.println("terminamos");
        
    } 

}
