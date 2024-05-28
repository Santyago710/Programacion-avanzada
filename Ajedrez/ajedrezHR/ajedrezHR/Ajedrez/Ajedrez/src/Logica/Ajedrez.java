package Logica;

import Logica.Fichas.*;
import Logica.Jugador.Jugador;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class Ajedrez {
    
    private Point aux;
    // creo las fichas aqui
    private Fichas fichaRey; 
    private Fichas fichaDama; 
    private Fichas[] fichaPeon; 
    private Fichas[] fichaObispo;
    private Fichas[] fichaCaballo;
    private Fichas[] fichaTorre;    
    // El ajedrez tiene un Tablero 
    private final Tablero tablero;
    //Y de hecho tiene un Jugador
    private final Jugador player;
    // el numero de fichas como static
    private static final int NUM_PEON = 8;
    private static final int NUM_REY = 1;
    private static final int NUM_DAMA = 1;
    private static final int NUM_TORRE = 2;
    private static final int NUM_OBISPO = 2;
    private static final int NUM_CABALLO = 2;
    
    public Ajedrez(Jugador p){
        tablero = new Tablero();// creamos un tablero
        player = p; // creamos al jugador, lo metemos
        inicializarFichas();
    }
    public Jugador getPlayer(){
        return player;
    }
    public Tablero getTablero(){
        return tablero;
    }
    
    public Fichas PonerFicha(Fichas ficha ,Point p, String tipo){
        ficha.setPos(p); //seteamos la posicion de la ficha
        tablero.getCasillas()[ficha.getPos().x][ficha.getPos().y] = ficha.Cast(); //poniendo todo en terminos de la ficha  
        tablero.getCasillas()[ficha.getPos().x][ficha.getPos().y].setPos(ficha.getPos());
        tablero.getCasillas()[ficha.getPos().x][ficha.getPos().y].setTipo(tipo);     
        return tablero.getCasillas()[ficha.getPos().x][ficha.getPos().y];
    } // aqui se coloca una

    public Fichas MoverFichas(Point inicio, Point fin , boolean mensaje){ // necesitamos un punto de inicio y uno de fin , y mira si es o no mensaje
       if(inicio.equals(fin) || tablero.getCasillas()[inicio.x][inicio.y] == null) return null;
       Fichas aux =  tablero.getCasillas()[inicio.x][inicio.y].Cast(); // aquie inicializamos el tipo de ficha que teniamos y vamos a poner
       aux.setTipo(tablero.getCasillas()[inicio.x][inicio.y].getTipo()); // Meto su tipo
       //analisamos si es del muchacho o no
       if((!player.getPosee().equals(aux.getTipo())|| player.getTurno() == 1 )&& mensaje == false ){ // si no es su ficha o turno
           System.out.println("No es tu ficha o no es tu turno");
           return null;
       }// si no es del tipo, F, no puede mover
       String newTipo = tablero.getCasillas()[inicio.x][inicio.y].getTipo(); // reservamos el Tipo para la proxima
       tablero.getCasillas()[inicio.x][inicio.y] = null; // aqui la quitamos
       player.setTurno(1); // cambiamos el turno para que ya no pueda hacer nada
       return PonerFicha(aux,fin,newTipo); // retorno la ficha puesta
    } // estructura para mover una
    
    public Map<Fichas,Point[]> Dibujo(){ // un booleano ya que necesito que este sea el motor del mensaje que trasmita al server
        if(tablero.getMovimiento().getInicio() == null || tablero.getMovimiento().getFin() == null) return null; // Si alguno de los dos es nullo que no dibuje nada
        Fichas fichaMover = MoverFichas(tablero.getMovimiento().getInicio(),tablero.getMovimiento().getFin(),false);   // creo la instancia fichaMover y la guardo    
        Point[] punto = new Point[2];  // aquie los creamos para retoarnar
                                    punto[0] = new Point(tablero.getMovimiento().getInicio()); 
                                    punto[1] = new Point(tablero.getMovimiento().getFin()); 
        //metemos los puntos null
        tablero.getMovimiento().setInicio(null);
        tablero.getMovimiento().setFin(null);
        // aqui enviamos los puntos el true que nos genera 
        Map m = new HashMap(); m.put(fichaMover, punto); // aqui metemos en el mapa los dos cosso la posicion y la ficha

        return m;
    }    

    public Map<Fichas,Integer> FichaPerdida(){
        Map<Fichas,Integer> contador =  new HashMap();
        int contPeon,contRey,contDama,contObispo,contTorre,contCaballo = 0;
        contPeon = contRey = contDama = contObispo = contTorre = contCaballo; // inicializo todos
        
        for(int filas =  0 ; filas < 8 ; ++filas){
            for(int columnas = 0; columnas<8 ; columnas++){   
                if(tablero.getCasillas()[filas][columnas] == null) continue; // si es nulo retorne nulo
                Fichas ficha =  tablero.getCasillas()[filas][columnas].Cast();
                ficha.setTipo(tablero.getCasillas()[filas][columnas].getTipo()); // tenemos el tipo
                if(ficha.getTipo().equals(player.getPosee())){ //tiene que ser del mismo tipo para contarlas
                    if(ficha instanceof Peon)contPeon++;
                    if(ficha instanceof Rey) contRey++;
                    if(ficha instanceof Dama)contDama++;
                    if(ficha instanceof Obispo)contObispo++; 
                    if(ficha instanceof Torre) contTorre++;
                    if(ficha instanceof Caballo)contCaballo++;
            }
            }
        }
if (contPeon != NUM_PEON) 
    contador.put(new Peon(), NUM_PEON - contPeon);
if (contRey != NUM_REY) 
    contador.put(new Rey(), NUM_REY - contRey);
if (contDama != NUM_DAMA) 
    contador.put(new Dama(), NUM_DAMA - contDama);
if (contTorre != NUM_TORRE) 
    contador.put(new Torre(), NUM_TORRE - contTorre);
if (contObispo != NUM_OBISPO) 
    contador.put(new Obispo(), NUM_OBISPO - contObispo);
if (contCaballo != NUM_CABALLO) 
    contador.put(new Caballo(), NUM_CABALLO - contCaballo);

        return contador;
    }

    public boolean Ganaron(){
        Map<Fichas,Integer> aux = new HashMap();
        aux = FichaPerdida();
        
        for(Fichas f: aux.keySet()){
            if(f instanceof Rey){
                return true; // retorne que si perdio
            }
        }
        
        return false;
    }
    
// METODOS PRIVADOS    
    private void inicializarFichas() {
        fichaRey = new Rey();
        fichaDama = new Dama();
        fichaPeon = new Fichas[8];
        fichaObispo = new Fichas[2];
        fichaCaballo = new Fichas[2];
        fichaTorre = new Fichas[2];
        
        if(player.getPosee().equals("NEGRAS")){
            player.setTurno(1); // si es negro que tenga el urno bloqueado -- > 1 para bloqeado
            ColocarFichas(0,1,4,3,"BLANCAS"); // colocamos las fichas
            ColocarFichas(7,6,4,3,"NEGRAS");  // colocamos las Blancas 
        }
        else{
            ColocarFichas(0,1,3,4,"NEGRAS"); // colocamos las fichas
            ColocarFichas(7,6,3,4,"BLANCAS");  // colocamos las Blancas 
        }
        
    }
    
    private void ColocarFichas(int atras, int adelante,int reina, int rey, String tipo){  
       Point Rey = new Point(atras,rey); // no entiend porque no se pued emover el rey, juepucha
       
       Point Dama = new Point(atras,reina);
       PonerFicha(fichaRey,Rey,tipo);
       PonerFicha(fichaDama,Dama,tipo);

            //Obispos
        for(int i = 0; i < 2; i++){
            aux = new Point(atras,2+(3*i));
            fichaObispo[i] = new Obispo();
            PonerFicha(fichaObispo[i],aux,tipo);
        }
            // Caballos
        for(int i = 0; i < 2; i++){
            aux = new Point(atras,1+(5*i));
            fichaCaballo[i] = new Caballo();
            PonerFicha(fichaCaballo[i],aux,tipo);
        }
            //Torres
        for(int i = 0; i < 2; i++){
            aux = new Point(atras,0+(7*i));
            fichaTorre[i] = new Torre();
            PonerFicha(fichaTorre[i],aux,tipo);

        }
         // Peones
        for(int i = 0; i <8 ; i ++){
            aux = new Point(adelante,i); // el auxiliar para el punto
            fichaPeon[i] = new Peon(); // inicializo cada peon
            PonerFicha(fichaPeon[i],aux,tipo);
        }
 } // aqui se colocan

    public Fichas getFicha(String a) {
        Fichas f = null;

        switch(a){
            case "Rey" -> f = new Rey();
            case "Dama" -> f = new Dama();
            case "Obispo" -> f = new Obispo();
            case "Torre" -> f = new Torre();
            case "Caballo" -> f = new Caballo();
            case "Peon" -> f = new Peon();
        }
        f.setTipo(player.getPosee());
        
    return f;
    }
}
