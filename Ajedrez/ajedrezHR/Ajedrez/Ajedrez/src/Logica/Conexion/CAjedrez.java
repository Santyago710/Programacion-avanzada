package Logica.Conexion;

import Logica.Ajedrez;
import Logica.Fichas.Fichas;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CAjedrez extends Thread{
    
    private Socket servidor; //tiene un servidor
    private DataOutputStream salida;
    private DataInputStream entrada;
    private String msg; // mensaje
    private Ajedrez LA;
    private javax.swing.JTextArea TA_CHAT;
    
    public void Conectar(Ajedrez L, javax.swing.JTextArea TA) throws IOException{
        TA_CHAT = TA;
        LA = L; // igualo las logicas
        String ruta = "localhost";
        int puerto = 1000; // puerto 5000
        msg = "";
        //Establezco la conexion:
        servidor = new Socket(ruta,puerto);
        // ahora los flujos
        salida = new DataOutputStream(servidor.getOutputStream());
        entrada = new DataInputStream(servidor.getInputStream());//metemos al servidor para leer y mandar
    }
    
    public String Recibir(){
        return msg;
    }   
    
    public void Enviar(String mensaje) throws IOException{
        salida.writeUTF(mensaje);
    }
    
    public void run(){
        while(true){
            String mensaje;
            try {
            mensaje = entrada.readUTF();
            if(mensaje.startsWith("CC")){  
                Chat(mensaje);  
            }
            else{
                msg = mensaje;
                leerCodigo(msg);    
            }
        } catch (IOException ex) {
            Logger.getLogger(CAjedrez.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
// me4todo para conversion: 
    private void leerCodigo(String mensaje){
        Map<Integer,Integer> cambio = new HashMap();
        for (int i = 0; i <= 7; i++) {
            int transformacion = (i <= 3) ? 7 - i : 7 - i; // Es i< =3 , si es asi, 7-i , si no 3-(i-4) , para asi generar todos
            cambio.put(i, transformacion);
        }        // metmos cada una
        
        int[] cod = new int[5];
        String[] Codigo = mensaje.split("#");
        int i = 0; // cointador para el for each
        for (String c : Codigo) {
                cod[i] = Integer.parseInt(c);
                System.out.println(cod[i]);
                i++;
        }
        for(int j = 0; j < 4; j ++){
            if(cambio.containsKey(cod[j])){
                cod[j] = cambio.get(cod[j]);
            }
        }     
                
         LA.MoverFichas(new Point(cod[0],cod[1]),new Point(cod[2],cod[3]),true); // muevo la ficha del mensaje enviado true porque, es un mensaje= si, entonces true
         LA.getPlayer().setTurno(cod[4]);
    }

    private void Chat(String mensaje){
                msg = mensaje.substring(2);
                this.TA_CHAT.append(LA.getPlayer().getTurno()+": "+msg+"\n");
                
    }
    public String ComunicacionServer(){
        String mensaje = "";
        Point[] pAux = new Point[2]; //puntoAuxuliar para leer los puntos
        pAux[0] =  new Point();
        pAux[1] = new Point(); // inicializamos los dos
// inicializamos los dos
        Map<Fichas,Point[]> Comunicacion = new HashMap();
        //me leera mejor la parte de los blancos o 
        Comunicacion = LA.Dibujo();
            if(Comunicacion != null){
            for(Fichas f : Comunicacion.keySet()){
                f.getTipo(); // aqui el tipo
                pAux = Comunicacion.get(f); // aqui metemos el pAux
                f.Cast();
            }
            mensaje = +pAux[0].x+"#"+pAux[0].y+"#"+pAux[1].x+"#"+pAux[1].y+"#0#"; // mandamos el tiupo, y posicion 
            }
            else{
                return null;
            }
       return mensaje;     
    }
}